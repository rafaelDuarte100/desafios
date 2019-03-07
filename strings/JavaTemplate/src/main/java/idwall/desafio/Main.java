package idwall.desafio;

import java.io.IOException;

import idwall.desafio.arquivo.FonteTextoEmArquivo;
import idwall.desafio.exception.PalavraMaiorQueTamanhoLimiteException;
import idwall.desafio.string.RafaelDuarteFormatter;
import idwall.desafio.string.StringFormatter;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class Main {

    public static void main(String[] args) throws IOException, PalavraMaiorQueTamanhoLimiteException {
       
    	int limiteCaracteresLinha = 40;
    	FonteTexto fonteTexto = new FonteTextoEmArquivo();
    	StringFormatter stringFormatter = new RafaelDuarteFormatter(limiteCaracteresLinha);
    	System.out.println(stringFormatter.format(fonteTexto.getTexto()));
    }
}
