package idwall.desafio.string;

import idwall.desafio.exception.PalavraMaiorQueTamanhoLimiteException;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public abstract class StringFormatter {

    protected Integer limit;

    public StringFormatter(int limite) {
        this.limit = limite;
    }

    /**
     * It receives a text and should return it formatted
     *
     * @param text
     * @return
     * @throws PalavraMaiorQueTamanhoLimiteException 
     */
    public abstract String format(String text) throws PalavraMaiorQueTamanhoLimiteException;
}
