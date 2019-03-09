package idwall.desafio.string.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import idwall.desafio.exception.PalavraExcedeTamanhoLinhaException;
import idwall.desafio.string.LinhaTexto;

public class LinhaTextoTest {

	@Test
	public void seTentarAdicionarPalavraMaiorQueTamanhoLimiteEntaoEmiteExcecaoComMensagemEspecifica() throws RuntimeException, PalavraExcedeTamanhoLinhaException {
		int tamanhoLimiteLinha = 5;
		LinhaTexto linhaTexto = new LinhaTexto(tamanhoLimiteLinha);
		Throwable exception = assertThrows(RuntimeException.class, () -> linhaTexto.adicionarPalavra("PAPIBAQUIGRAFO"));
		assertEquals(exception.getMessage(), "A palavra \"PAPIBAQUIGRAFO\" excede o tamanho máximo de uma linha, que é 5 caracteres.");
	}
	
	@Test
	public void seTentarAdicionarPalavraQueExcedaOTamanhoLimiteDaLinhaEntaoEmiteExcecaoEspecifica() throws RuntimeException, PalavraExcedeTamanhoLinhaException {
		int tamanhoLimiteLinha = 15;
		LinhaTexto linhaTexto = new LinhaTexto(tamanhoLimiteLinha);
		linhaTexto.adicionarPalavra("ORNINTORRINCO");
		assertThrows(PalavraExcedeTamanhoLinhaException.class, () -> linhaTexto.adicionarPalavra("RAFAEL"));
	}
	
	@Test
	public void seAdicionaPalavraEmLinhaVaziaEntaoTamanhoDaLinhaEIgualAoTamanhoDaPalavra() throws RuntimeException, PalavraExcedeTamanhoLinhaException {
		int tamanhoLimiteLinha = 40;
		LinhaTexto linhaTexto = new LinhaTexto(tamanhoLimiteLinha);
		String palavra = "RAFAEL";
		linhaTexto.adicionarPalavra(palavra);
		assertEquals(palavra.length(), linhaTexto.getTamanhoAtualDaLinha());
	}
	
	@Test
	public void justificarLinhaVaziaNaoDeveAlterarLinha() {
		int tamanhoLimiteLinha = 40;
		LinhaTexto linhaTexto = new LinhaTexto(tamanhoLimiteLinha);
		String linhaAntes = linhaTexto.toString();
		linhaTexto.justificar();
		assertEquals(linhaAntes, linhaTexto.toString());
	}
	
	@Test
	public void testarJustificarLinha() throws RuntimeException, PalavraExcedeTamanhoLinhaException {
		int tamanhoLimiteLinha = 25;
		LinhaTexto linhaTexto = new LinhaTexto(tamanhoLimiteLinha);
		linhaTexto.adicionarPalavra("RAFAEL");
		linhaTexto.adicionarPalavra("DUARTE");
		linhaTexto.adicionarPalavra("OLIVEIRA");
		linhaTexto.justificar();
		assertEquals("RAFAEL   DUARTE  OLIVEIRA", linhaTexto.toString());
	}
}
