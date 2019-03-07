package idwall.desafio.string;

import java.util.ArrayList;
import java.util.List;

import idwall.desafio.exception.PalavraExcedeTamanhoLinhaException;
import idwall.desafio.exception.PalavraMaiorQueTamanhoLimiteException;

public class RafaelDuarteFormatter extends StringFormatter {

	private List<LinhaTexto> linhas;
	private LinhaTexto linhaAtual;
	
	public RafaelDuarteFormatter(int limite) {
		super(limite);
		linhas = new ArrayList<>();
		linhaAtual = new LinhaTexto(limite);
	}
	
	public String format(String texto) throws PalavraMaiorQueTamanhoLimiteException {
		adicionarPalavrasAoTexto(texto);
		return getTextoFormatado();
	}

	private void adicionarPalavrasAoTexto(String texto) throws PalavraMaiorQueTamanhoLimiteException {
		if (texto != null && !texto.isEmpty()) {
			for (String linha : texto.split("\n")) {
				for (String palavra : linha.split(" ")) {
					adicionaPalavra(palavra);
				}
				adicionarLinha();
			}
		}
	}
	
	public void adicionaPalavra(String palavra) throws PalavraMaiorQueTamanhoLimiteException {
		try {
			linhaAtual.adicionarPalavra(palavra);
		} catch (PalavraExcedeTamanhoLinhaException e) {
			adicionarLinha();
			adicionaPalavra(palavra);
		}
	}

	private void adicionarLinha() {
		linhas.add(linhaAtual);
		linhaAtual = new LinhaTexto(limit);
	}
	
	private String getTextoFormatado() {
		StringBuilder sb = new StringBuilder();
		for (LinhaTexto linha : linhas) {
			sb.append(linha.toString()).append("\n");
		}
		return sb.toString();
	}
}
