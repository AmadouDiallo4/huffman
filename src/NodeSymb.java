/**
 * 
 */

/**
 * Noeud contenant un symbole, pour l ecriture et la lecture du dictionnaire
 * 
 * @author Matt
 * 
 */
public class NodeSymb {

	/** Symbole contenu dans le noeud */
	protected Symbole symbole;
	/** variable servant au chainage */
	protected NodeSymb next;

	/**
	 * constructeur sans parametre, cree un noeud null
	 */
	public NodeSymb() {
		this.next = null;
		this.symbole = null;
	}

	/**
	 * constructeur avec parametre
	 * 
	 * @param s
	 *            le symbole que va contenir le noeud
	 */
	public NodeSymb(Symbole s) {
		this.symbole = s;
		this.next = null;
	}

	/**
	 * @return the symbole
	 */
	public Symbole getSymbole() {
		return symbole;
	}

	/**
	 * @param symbole
	 *            the symbole to set
	 */
	public void setSymbole(Symbole symbole) {
		this.symbole = symbole;
	}

	/**
	 * @return the next
	 */
	public NodeSymb getNext() {
		return next;
	}

	/**
	 * @param next
	 *            the next to set
	 */
	public void setNext(NodeSymb next) {
		this.next = next;
	}

	/**
	 * methode toString
	 */
	public String toString() {
		return "NodeSymb [" + symbole + "]";
	}

	/**
	 * tests unitaire, creation de symbole, de noeud et affichage
	 */
	public static void main(String[] args) {
		NodeSymb n = new NodeSymb();
		Symbole s = new Symbole('a');
		NodeSymb nn = new NodeSymb(s);
		System.out.println(n);
		System.out.println(nn);

	}
}
