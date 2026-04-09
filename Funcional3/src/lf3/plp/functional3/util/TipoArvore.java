package lf3.plp.functional3.util;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.functional1.util.TipoPolimorfico;

public class TipoArvore implements Tipo {

	/**
	 * Subtipo - tipo dos elementos da arvore
	 */
	private Tipo subTipo;

	/**
	 * cria uma instncia de TipoArvore sem subTipo
	 */
	public TipoArvore() {
		this.subTipo = new TipoPolimorfico();
	}

	/**
	 * cria uma instncia de TipoArvore com o seu subtipo
	 * 
	 * @param subTipo
	 */
	public TipoArvore(Tipo subTipo) {
		this.subTipo = subTipo;
	}

	/**
	 * Retorna o tipo dos elementos de uma arvore
	 * 
	 * @return tipo dos elementos da arvore
	 */
	public Tipo getSubTipo() {
		return subTipo;
	}

	public boolean eBooleano() {
		return false;
	}

	public boolean eIgual(Tipo tipo) {
		if (tipo instanceof TipoArvore) {
			TipoArvore arvore = (TipoArvore) tipo;
			return arvore.subTipo.eIgual(this.subTipo);
		}
		return tipo.eIgual(this);
	}

	public boolean eInteiro() {
		return false;
	}

	public boolean eString() {
		return false;
	}

	public boolean eValido() {
		return subTipo != null && subTipo.eValido();
	}

	public String getNome() {
		return "avbin [" + subTipo.getNome() + "]";
	}

	public Tipo intersecao(Tipo outroTipo) {
		if (outroTipo instanceof TipoArvore) {
			TipoArvore outraArvore = (TipoArvore) outroTipo;
			return this.subTipo.intersecao(outraArvore.subTipo);
		}
		return outroTipo.intersecao(this);
	}

	@Override
	public String toString() {
		return "avbin [" + subTipo.getNome() + "]";
	}
}
