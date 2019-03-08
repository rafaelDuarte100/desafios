package idwall.desafio.string;

import idwall.desafio.exception.PalavraExcedeTamanhoLinhaException;

public class LinhaTexto {

	private String conteudo = "";
	private int tamanhoLimiteLinha;
	
	public LinhaTexto(int tamanhoLimiteLinhas) {
		this.tamanhoLimiteLinha = tamanhoLimiteLinhas;
	}

	public void adicionarPalavra(String palavra) throws RuntimeException, PalavraExcedeTamanhoLinhaException {
		if (palavra.length() > tamanhoLimiteLinha)
			throw new RuntimeException("A palavra \"" + palavra + "\" excede o tamanho máximo de uma linha " + tamanhoLimiteLinha + " caracteres.");
		
		if (!palavraCabeNaLinha(palavra))
			throw new PalavraExcedeTamanhoLinhaException();
			
		conteudo += (conteudo.isEmpty() ? "" : " ") + palavra;
	}
	
	private boolean palavraCabeNaLinha(String palavra) {
		int tamanhoPalavra = palavra.length();
		int tamanhoEspaco = conteudo.isEmpty() ? 0 : 1;
		return (getTamanhoAtualDaLinha() + tamanhoPalavra + tamanhoEspaco) <= tamanhoLimiteLinha;
	}
	
	public int getTamanhoAtualDaLinha() {
		return conteudo.length();
	}
	
	@Override
	public String toString() {
		return conteudo;
	}
}
