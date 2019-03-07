package idwall.desafio.exception;

public class PalavraMaiorQueTamanhoLimiteException extends Exception {

	private static final long serialVersionUID = 1L;

	public PalavraMaiorQueTamanhoLimiteException(String mensagem) {
		super(mensagem);
	}
}
