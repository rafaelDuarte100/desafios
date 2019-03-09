package idwall.desafio.string.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import idwall.desafio.string.IdwallFormatter;
import idwall.desafio.string.StringFormatter;

public class RafaelDuarteFormatterTest {

	@Test
	public void testarFormatarTextoSemJustificar() {
		String texto = "In the beginning God created the heavens and the earth.";
		String textoEsperado = "In the"
							 + "\nbeginning"
							 + "\nGod"
							 + "\ncreated"
							 + "\nthe"
							 + "\nheavens"
							 + "\nand the"
							 + "\nearth.";
		
		int limiteTamanhoLinha = 10;
		boolean justificar = false;
		StringFormatter sf = new IdwallFormatter(limiteTamanhoLinha, justificar);
		assertEquals(textoEsperado, sf.format(texto));		
	}
	
	@Test
	public void testarFormatarTextoJustificando() {
		String texto = "In the beginning God created the heavens and the earth.";
		String textoEsperado = "In the beginning"
							 + "\nGod  created the"
							 + "\nheavens  and the"
							 + "\nearth.";
		
		int limiteTamanhoLinha = 16;
		boolean justificar = true;
		StringFormatter sf = new IdwallFormatter(limiteTamanhoLinha, justificar);
		assertEquals(textoEsperado, sf.format(texto));		
	}
	
	@Test
	public void seTentarFormatarTextoComPalavraMaiorQueTamanhoLimiteEntaoEmiteExceptionComMensagemEspecifica() {
		String texto = "In the beginning God created the heavens pneumoultramicroscopicossilicovulcanoconiótico and the earth.";
		int limiteTamanhoLinha = 45;
		boolean justificar = true;
		StringFormatter sf = new IdwallFormatter(limiteTamanhoLinha, justificar);
		Throwable exception = assertThrows(RuntimeException.class, () -> sf.format(texto));
		assertEquals(exception.getMessage(), "A palavra \"pneumoultramicroscopicossilicovulcanoconiótico\" excede o tamanho máximo de uma linha, que é 45 caracteres.");
	}
}
