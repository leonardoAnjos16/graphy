package lf3.plp.functional3.expression;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.ExpUnaria;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.util.ArvoreVaziaException;
import lf3.plp.functional3.util.TipoArvore;

public class ExpRoot extends ExpUnaria {

	public ExpRoot(Expressao exp) {
		super(exp, "root");
	}

	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		ValorArvore arvore = (ValorArvore) this.getExp().avaliar(amb);
		if (arvore.isEmpty()) {
			throw new ArvoreVaziaException();
		}
		return (Valor) arvore.getRoot();
	}

    @Override
	protected boolean checaTipoElementoTerminal(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		Tipo tipoExp = this.getExp().getTipo(amb);
		return tipoExp instanceof TipoArvore;
	}

	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		Tipo tipoExp = this.getExp().getTipo(amb);
		if (tipoExp instanceof TipoArvore) {
			return ((TipoArvore) tipoExp).getSubTipo();
		}
		return null;
	}

	public ExpRoot clone() {
		return new ExpRoot(this.getExp().clone());
	}
}
