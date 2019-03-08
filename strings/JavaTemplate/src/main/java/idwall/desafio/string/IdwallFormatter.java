package idwall.desafio.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.processing.SupportedSourceVersion;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 * Updated by Rafael Duarte on 07/03/2019
 */
public class IdwallFormatter extends StringFormatter {

	protected List<String> linhas;
	
    public IdwallFormatter(int limite, boolean justify) {
		super(limite, justify);
		linhas = new ArrayList<>();
	}

	@Override
    public String format(String text) {
		formatarTexto(text);
		justificarTexto();
		return getTextoFormatado();
    }

	private void formatarTexto(String text) {
		Stream.of(text.split("\n"))
			  .forEach(linha -> formatarLinha(linha));
	}
	
	private void formatarLinha(String linha) {
		linhas.addAll(quebrarLinha(linha));
	}
	
	private List<String> quebrarLinha(String linha) {
		List<String> saida = new ArrayList<>();
		String linhaAtual = "";
		
		for (String palavra : linha.split(" ")) {
			linhaAtual = adicionarPalavraNaLinhaAtual(saida, linhaAtual, palavra);
		}
		saida.add(linhaAtual);		
		return saida;
	}

	private String adicionarPalavraNaLinhaAtual(List<String> saida, String linhaAtual, String palavra) {
		if (palavraExcedeTamanhoLimiteLinha(linhaAtual, palavra)) {
			saida.add(linhaAtual);
			linhaAtual = palavra;
		}
		else {
			linhaAtual += (linhaAtual.isEmpty() ? "" : " ") + palavra;
		}
		return linhaAtual;
	}
	
	private String getTextoFormatado() {
		return linhas.stream()
					 .collect(Collectors.joining("\n"));
	}
	
	private boolean palavraExcedeTamanhoLimiteLinha(String linha, String palavra) {
		int tamanhoLinha = linha.length();
		int tamanhoEspaco = linha.isEmpty() ? 0 : 1;
		int tamanhoPalavra = palavra.length();
		return (tamanhoLinha + tamanhoEspaco + tamanhoPalavra) > limit;
	}
	
	public void justificarTexto() {
		if (justify) {
			linhas = linhas.stream()
						   .map(linha -> linha = justificarLinha(linha))
						   .collect(Collectors.toList());
		}
	}
	
	public String justificarLinha(String linha) {
		linha = linha.trim();

		if (linhaDeveSerJustificada(linha)) {
			StringBuffer sb = new StringBuffer(linha);
			List<Integer> posicoesEspacos = getPosicoesEspacos(linha);
			
			int indicePosicao = 0;
			while(sb.toString().length() < limit) {
				sb.insert(posicoesEspacos.get(indicePosicao), " ");
				posicoesEspacos = shiftRigthPosicoes(posicoesEspacos, indicePosicao);
				indicePosicao++;

				if (indicePosicao == posicoesEspacos.size())
					indicePosicao = 0;
			}
			return sb.toString();
		}
		return linha;
	}
	
	protected List<Integer> getPosicoesEspacos(String linha) {
		List<Integer> posicoes = new ArrayList<>();
		
		for (int i = 0; i < linha.length(); i++) {
			if (linha.charAt(i) == ' ')
				posicoes.add(i);
		}
		return posicoes;
	}
	
	protected List<Integer> shiftRigthPosicoes(List<Integer> posicoes, int indice) {
		for (int i = 0; i < posicoes.size(); i++) {
			if (i > indice)
				posicoes.set(i, posicoes.get(i) + 1);
		}
		return posicoes;
	}
	
	protected boolean linhaDeveSerJustificada(String linha) {
		return linha.length() < limit && linha.chars().filter(c -> c == ' ').count() > 0;
	}
}