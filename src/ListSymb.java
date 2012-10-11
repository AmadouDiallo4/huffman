/**
 * 
 */

/**
 * Liste chainee qui sert de dictionnaire, elle contient des symboles
 * 
 * @author Matt
 */
public class ListSymb {

	private NodeSymb first;

	/**
	 * constructeur, initialise first a null
	 */
	public ListSymb() {
		this.first = null;
	}

	/**
	 * @return the first
	 */
	public NodeSymb getFirst() {
		return first;
	}

	/**
	 * @param first
	 *            the first to set
	 */
	public void setFirst(NodeSymb first) {
		this.first = first;
	}

	/**
	 * teste si la liste est vide
	 * 
	 * @return true si la liste est vide
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * vide la liste
	 */
	public void empty() {
		first = null;
	}

	/**
	 * permet d obtenir la taille de la liste. nest plus utilisee
	 * 
	 * @return un entier, la taille de la liste.
	 */
	public int length() {
		int c = 0;
		NodeSymb elem = first;
		while (elem != null) {
			c++;
			elem = elem.getNext();
		}
		return c;
	}

	/**
	 * methode toString : affiche tous les noeuds. Se base sur toString de la
	 * classe Node. A servi lors des differents tests
	 * 
	 * @return chaine de caractere representant la liste
	 */
	public String toString() {
		// permet de constuire le chaine de caractere a retourner
		StringBuilder sb = new StringBuilder();
		if (sb.length() > 0)
			sb.delete(0, sb.length());
		NodeSymb elem = first;
		while (elem != null) {
			sb.append(elem.toString() + " - ");
			elem = elem.getNext();
		}
		return sb.toString();
	}

	/**
	 * insere un noeud en debut de liste
	 * 
	 * @param val
	 *            noeud a inserer
	 */
	public void insertFirst(NodeSymb val) {
		if (isEmpty())
			this.first = val;
		else {
			val.setNext(first);
			first = val;
		}
	}

	/**
	 * Methode qui cherche le code associe a un caractere. Sert lors de la
	 * compression.
	 * 
	 * @param cara
	 *            le caractere rechercher
	 * @return retourne le noeud de la liste ou se trouve le caractere
	 */
	public NodeSymb search(int cara) {
		if (isEmpty())
			return null;
		else {
			NodeSymb n = first;
			while (n != null && n.getSymbole().getCara() != cara)
				n = n.getNext();
			if (n != null)
				return n;
			else
				return null;
		}
	}

	/**
	 * Methode qui cherche le caractere associe a un code. Sert lors de la
	 * decompression.
	 * 
	 * @param code
	 *            le code que l on connait
	 * @return le caractere associe au code
	 */
	public NodeSymb searchcode(String code) {
		if (isEmpty())
			return null;
		else {
			NodeSymb n = first;
			while (n != null && n.getSymbole().getCode().compareTo(code) != 0)
				n = n.getNext();
			if (n != null)
				return n;
			else
				return null;
		}
	}

	/**
	 * tests, creation de noeud, insertion, affichage
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ListSymb li = new ListSymb();
		Symbole s = new Symbole('e');
		NodeSymb n = new NodeSymb(s);
		li.setFirst(n);
		System.out.println(li);
		Symbole sy = new Symbole('r');
		NodeSymb nn = new NodeSymb(sy);
		li.insertFirst(nn);
		System.out.println(li);
	}
}
