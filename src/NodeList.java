/**
 * Noeud de la liste
 * 
 * @author Matt
 * 
 */
public class NodeList {

	/**
	 * valeur du noeud
	 */
	private Arbre val;

	/**
	 * variable servant au chainage
	 */
	protected NodeList next;

	/**
	 * Constructeur avec parametre : initialise la valeur du noeud. Chaque noeud
	 * est un arbre. Le noeud suivant est initialise a null
	 */
	public NodeList(Arbre val) {
		this.val = val;
		this.next = null;
	}

	/**
	 * Constucteur sans parametre, cree un arbre null.
	 */
	public NodeList() {
		this(null);
	}

	/**
	 * @return the val
	 */
	public Arbre getVal() {
		return val;
	}

	/**
	 * @param val
	 *            the val to set
	 */
	public void setVal(Arbre val) {
		this.val = val;
	}

	/**
	 * @return the next
	 */
	public NodeList getNext() {
		return next;
	}

	/**
	 * @param next
	 *            the next to set
	 */
	public void setNext(NodeList next) {
		this.next = next;
	}

	/**
	 * @return retourne le noeud sous forme de caracteres
	 */
	public String toString() {
		return "NodeList [" + val + "]";
	}

	/**
	 * compare deux noeud de la liste par rapport a leur poids
	 * 
	 * @param n
	 *            noeud a comparer au noeud courant
	 * @return un entier positif si le poids du noeud courant est superieur au
	 *         noeud entre en param, un entier negatif sinon
	 */
	public int compareTo(NodeList n) {
		return this.getVal().getRacine().getPoids()
				- n.getVal().getRacine().getPoids();
	}

	/**
	 * simple test creation de deux noeuds et affichage
	 */
	public static void main(String[] args) {
		Symbole s = new Symbole('r');
		NodeFeuille no = new NodeFeuille(s, 1);
		Arbre a = new Arbre();
		a.setRacine(no);
		NodeList n = new NodeList(a);
		NodeList m = new NodeList();
		System.out.println(n);
		System.out.println(m);

	}

}
