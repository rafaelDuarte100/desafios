package idwall.desafio.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import idwall.desafio.exception.PalavraExcedeTamanhoLinhaException;

public class IdwallFormatter extends StringFormatter {

	protected List<LinhaTexto> linhas;
	protected int indiceLinhaAtual;
	protected boolean deveJustificarTexto;
	
	public IdwallFormatter(int limite, boolean deveJustificarTexto) {
		super(limite);
		linhas = new ArrayList<>();
		indiceLinhaAtual = -1;
		this.deveJustificarTexto = deveJustificarTexto;
	}
	
	public String format(String text) {
		formatarTexto(text);
		return getTextoFormatado();
	}
	
	private void formatarTexto(String texto) {
		Arrays.stream(texto.split("\n")).forEach(linha -> formatarLinha(linha));
		
		if (deveJustificarTexto)
			justificarTexto();
	}

	private void formatarLinha(String linha) {
		criarNovaLinhaNoTexto();
		Arrays.stream(linha.split(" ")).forEach(palavra -> adicionarPalavraNoTexto(palavra));
	}
	
	private void criarNovaLinhaNoTexto() {
		linhas.add(new LinhaTexto(limit));
		indiceLinhaAtual++;
	}
	
	private void adicionarPalavraNoTexto(String palavra) {
		try {
			tentaAdicionarPalavraNaLinhaAtual(palavra);
		} catch (PalavraExcedeTamanhoLinhaException e) {
			criarNovaLinhaNoTexto();
			adicionarPalavraNoTexto(palavra);
		}
	}

	private void tentaAdicionarPalavraNaLinhaAtual(String palavra) throws PalavraExcedeTamanhoLinhaException {
		linhas.get(indiceLinhaAtual).adicionarPalavra(palavra);
	}
	
	private void justificarTexto() {
		linhas.forEach(linha -> linha.justificar());
	}
	
	private String getTextoFormatado() {
		return linhas.stream().map(linha -> linha.toString()).collect(Collectors.joining("\n"));
	}
}
