package idwall.desafio.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import idwall.desafio.exception.PalavraExcedeTamanhoLinhaException;

public class RafaelDuarteFormatter extends StringFormatter {

	private List<LinhaTexto> linhas;
	private LinhaTexto linhaAtual;
	
	public RafaelDuarteFormatter(int limite) {
		super(limite, false);
		linhas = new ArrayList<>();
		linhaAtual = new LinhaTexto(limite);
	}
	
	public String format(String texto) {
		adicionarPalavrasAoTexto(texto);
		return getTextoFormatado();
	}

	private void adicionarPalavrasAoTexto(String texto) {
		Arrays.stream(texto.split("\n"))
			  .forEach(linha -> quebrarLinha(linha));
	}
	
	private void quebrarLinha(String linha) {
		Arrays.stream(linha.split(" "))
			  .forEach(palavra -> adicionaPalavra(palavra));
		
		adicionarLinha();
	}
	
	public void adicionaPalavra(String palavra) throws RuntimeException {
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
		return linhas.stream()
					 .map(l -> l.toString())
					 .collect(Collectors.joining("\n"));
	}
}
