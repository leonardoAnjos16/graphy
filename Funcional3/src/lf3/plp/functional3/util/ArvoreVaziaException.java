package lf3.plp.functional3.util;

public class ArvoreVaziaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Cria um exception informando que a operacao está sendo realizada com a
	 * arvore vazia e eh invalido.
	 */
	public ArvoreVaziaException() {
		super("Não é possível realizar a operação com árvore vazia!");
	}
}
