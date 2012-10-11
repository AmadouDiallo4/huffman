/**
 * Ces noeuds sont ceux de l arbre, ils contiennent uniquement un entier qui
 * correspond a son poids
 * 
 * @author Matt
 * 
 */
public class NodeArbre {

	/** poids du noeuds */
	protected int poids;

	/** variables servant a la construction de l arbre */
	protected NodeArbre right;
	protected NodeArbre left;

	/**
	 * constructeur avec parametre, les deux fils sont initialises null.
	 * 
	 * @param poids
	 *            poids du noeud
	 */
	public NodeArbre(int poids) {
		this.poids = poids;
		this.left = null;
		this.right = null;
	}

	/**
	 * constructeur sans parametre, cree un noeud de poids nul. les deux fils
	 * sont initialises null.
	 */
	public NodeArbre() {
		this(0);
	}

	/**
	 * @return the poids
	 */
	public int getPoids() {
		return poids;
	}

	/**
	 * @param poids
	 *            the poids to set
	 */
	public void setPoids(int poids) {
		this.poids = poids;
	}

	/**
	 * @return the right
	 */
	public NodeArbre getRight() {
		return right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public void setRight(NodeArbre right) {
		this.right = right;
	}

	/**
	 * @return the left
	 */
	public NodeArbre getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            the left to set
	 */
	public void setLeft(NodeArbre left) {
		this.left = left;
	}

	/**
	 * methode toString
	 * 
	 * @return indique que le noeud ne possede pas de caractere ainis que son
	 *         poids
	 */
	public String toString() {
		return "Noeud sans caractere poids : " + poids;
	}

	/**
	 * petit main, cree deux noeuds : un sans parametre, l'autre avec ; les
	 * affiche ; change le poids du premier noeud et l'affiche
	 * 
	 * @param args
	 *            n'a pas d'utilitee
	 */
	public static void main(String args[]) {
		NodeArbre n = new NodeArbre();
		NodeArbre m = new NodeArbre(9);
		System.out.println(n);
		System.out.println(m);
		n.setPoids(8);
		System.out.println(n);
	}
}
