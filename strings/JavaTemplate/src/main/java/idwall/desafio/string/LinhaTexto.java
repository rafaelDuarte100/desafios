package idwall.desafio.string;

import java.util.ArrayList;
import java.util.List;

import idwall.desafio.exception.PalavraExcedeTamanhoLinhaException;

public class LinhaTexto {

	private String conteudo = "";
	private int tamanhoLimiteLinha;
	
	public LinhaTexto(int tamanhoLimiteLinhas) {
		this.tamanhoLimiteLinha = tamanhoLimiteLinhas;
	}

	public void adicionarPalavra(String palavra) throws RuntimeException, PalavraExcedeTamanhoLinhaException {
		if (palavra.length() > tamanhoLimiteLinha)
			throw new RuntimeException("A palavra \"" + palavra + "\" excede o tamanho máximo de uma linha, que é " + tamanhoLimiteLinha + " caracteres.");
		
		if (!palavraCabeNaLinha(palavra))
			throw new PalavraExcedeTamanhoLinhaException();
			
		conteudo += (conteudo.isEmpty() ? "" : " ") + palavra;
	}
	
	private boolean palavraCabeNaLinha(String palavra) {
		int tamanhoPalavra = palavra.length();
		int tamanhoEspaco = conteudo.isEmpty() ? 0 : 1;
		return (getTamanhoAtualDaLinha() + tamanhoPalavra + tamanhoEspaco) <= tamanhoLimiteLinha;
	}
	
	public void justificar() {
		StringBuffer sb = new StringBuffer(conteudo);
		List<Integer> posicoesEspacos = getPosicoesEspacos();

		int indicePosicao = 0;
		while(sb.toString().length() < tamanhoLimiteLinha && posicoesEspacos.size() > 0) {
			sb.insert(posicoesEspacos.get(indicePosicao), " ");
			posicoesEspacos = deslocarPosicoesParaDireira(posicoesEspacos, indicePosicao);
			indicePosicao++;

			if (indicePosicao == posicoesEspacos.size())
				indicePosicao = 0;
		}
		conteudo = sb.toString();
	}
	
	protected List<Integer> getPosicoesEspacos() {
		List<Integer> posicoes = new ArrayList<>();
		
		for (int i = 0; i < conteudo.length(); i++) {
			if (conteudo.charAt(i) == ' ')
				posicoes.add(i);
		}
		return posicoes;
	}
	
	protected List<Integer> deslocarPosicoesParaDireira(List<Integer> posicoes, int indiceBase) {
		for (int i = 0; i < posicoes.size(); i++) {
			if (i > indiceBase)
				posicoes.set(i, posicoes.get(i) + 1);
		}
		return posicoes;
	}
	
	public int getTamanhoAtualDaLinha() {
		return conteudo.length();
	}
	
	@Override
	public String toString() {
		return conteudo;
	}
}
