package lf3.plp.functional3.expression;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional2.expression.ValorAbstrato;
import lf3.plp.functional3.util.TipoArvore;

public class ValorArvore implements ValorAbstrato {

	private Expressao root;
	private Expressao left;
	private Expressao right;
	private boolean isEmpty;

	private ValorArvore() {
		this.isEmpty = true;
	}

	private ValorArvore(Expressao root, Expressao left, Expressao right) {
		this.root = root;
		this.left = left;
		this.right = right;
		this.isEmpty = false;
	}

	public static ValorArvore getInstanciaVazia() {
		return new ValorArvore(); // Sempre retornar uma nova pra evitar referências cruzadas na avaliação, ou não precisa.
	}

	public static ValorArvore getInstancia(Expressao root, Expressao left, Expressao right) {
		if (root == null) {
			return getInstanciaVazia();
		}
		return new ValorArvore(root, left, right);
	}

	public Expressao getRoot() {
		return root;
	}

	public Expressao getLeft() {
		return left;
	}

	public Expressao getRight() {
		return right;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		if (isEmpty()) return this;
		
		Expressao leftAvaliado = (left != null) ? left.avaliar(amb) : getInstanciaVazia();
		Expressao rightAvaliado = (right != null) ? right.avaliar(amb) : getInstanciaVazia();
		
		return getInstancia(root.avaliar(amb), leftAvaliado, rightAvaliado);
	}

	@Override
	public boolean checaTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		if (isEmpty()) return true;

		if (!root.checaTipo(amb)) return false;
		
		Tipo tipoRoot = root.getTipo(amb);
		
		if (left != null && !left.checaTipo(amb)) return false;
		if (right != null && !right.checaTipo(amb)) return false;

        Tipo tipoLeft = (left != null) ? left.getTipo(amb) : null;        
        if (tipoLeft != null && tipoLeft instanceof TipoArvore) {
            TipoArvore taLeft = (TipoArvore) tipoLeft;
            boolean leftValido = taLeft.getSubTipo() != null && taLeft.getSubTipo().eValido();
            if (leftValido && !tipoRoot.eIgual(taLeft.getSubTipo())) {
                return false;
            }
        }

        
        Tipo tipoRight = (right != null) ? right.getTipo(amb) : null;
        if (tipoRight != null && tipoRight instanceof TipoArvore) {
            TipoArvore taRight = (TipoArvore) tipoRight;
            boolean rightValido = taRight.getSubTipo() != null && taRight.getSubTipo().eValido();
            if (rightValido && !tipoRoot.eIgual(taRight.getSubTipo())) {
                return false;
            }
        }

		return true;
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		if (isEmpty()) return new TipoArvore();
		return new TipoArvore(root.getTipo(amb));
	}

	@Override
	public Expressao reduzir(AmbienteExecucao amb) {
		if (isEmpty()) return this;
		return getInstancia(root.reduzir(amb), left != null ? left.reduzir(amb) : getInstanciaVazia(), right != null ? right.reduzir(amb) : getInstanciaVazia());
	}

	@Override
	public ValorArvore clone() {
		if (isEmpty()) return getInstanciaVazia();
		return getInstancia(root.clone(), left != null ? left.clone() : getInstanciaVazia(), right != null ? right.clone() : getInstanciaVazia());
	}

	@Override
	public String toString() {
		if (isEmpty()) return "null";
		return "avbin[" + root + ", " + left + ", " + right + "]";
	}
}
