package idwall.desafio.fonteTexto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FonteTextoEmArquivo implements FonteTexto {

	protected static final String NOME_ARQUIVO = "arquivos_entrada/input_parte1.txt";
	
	@Override
	public String getTexto() throws IOException {
		return lerDoArquivo();
	}

	private String lerDoArquivo() throws IOException {
		Stream<String> linhas = null;
		try {
			linhas = Files.lines(getCaminhoArquivo());
			return linhas.map( l -> l).collect(Collectors.joining("\n"));
		} finally {
			linhas.close();
		}
	}

	private Path getCaminhoArquivo() {
		return Paths.get(getCaminhoProjeto(), NOME_ARQUIVO);
	}
	
	private String getCaminhoProjeto() {
		return new File(".").getAbsolutePath();
	}
}
