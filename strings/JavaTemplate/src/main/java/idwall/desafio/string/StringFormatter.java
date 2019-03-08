package idwall.desafio.string;


/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public abstract class StringFormatter {

    protected Integer limit;
    protected boolean justify;

    public StringFormatter(int limite, boolean justify) {
        this.limit = limite;
        this.justify = justify;
    }

    /**
     * It receives a text and should return it formatted
     *
     * @param text
     * @return
     * @throws PalavraMaiorQueTamanhoLimiteException 
     */
    public abstract String format(String text);
}
