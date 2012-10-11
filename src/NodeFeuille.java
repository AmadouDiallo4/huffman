/**
 * 
 */

/**
 * Noeud de l arbre contenant a la fois un caractere et un poids. Herite de l
 * objet NodeArbre
 * 
 * @author Matt
 * 
 */
public class NodeFeuille extends NodeArbre {

	/**
	 * Le caractere associe au noeud
	 */
	protected Symbole symbole;

	/**
	 * constructeur
	 * 
	 * @param cara
	 *            le caractere du noeud
	 * @param poids
	 *            le poids du noeud
	 */
	public NodeFeuille(Symbole s, int poids) {
		super.poids = poids;
		this.symbole = s;

	}

	/**
	 * constructeur sans parametre cree un noeud de poids nul avec le caractere
	 * '*'
	 */
	public NodeFeuille() {
		super();
		this.symbole = null;
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
	 * methode toString
	 * 
	 * @return
	 */
	public String toString() {
		return "symbole : [" + this.symbole + " poids :" + this.poids + "]";
	}

	/**
	 * Test, creation de noeud et affichage
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		NodeFeuille n = new NodeFeuille();
		Symbole s = new Symbole('a');
		NodeFeuille m = new NodeFeuille(s, 3);
		System.out.println(n);
		System.out.println(m);
		n.getSymbole().setCara('4');
		System.out.println(n);
	}
}
