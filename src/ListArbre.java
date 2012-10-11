/**
 * Liste simplement chainee. Renvoie a l objet NodeList qui sont les noeuds de
 * la liste.
 * 
 * @author Matt
 * 
 */
public class ListArbre {

	/**
	 * detrermine le premier noeud de la liste sachant que chaque noeud contient
	 * un arbre.
	 */
	private NodeList first;

	/**
	 * Constructeur sans parametre, il n y a pas besoin de constructeur avec
	 * parametre
	 */
	public ListArbre() {
		this.first = null;
	}

	/**
	 * @return the first
	 */
	public NodeList getFirst() {
		return first;
	}

	/**
	 * @param first
	 *            the first to set
	 */
	public void setFirst(NodeList first) {
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
	 * permet d obtenir la taille de la liste. N est plus utilisee
	 * 
	 * @return un entier, la taille de la liste.
	 */
	public int length() {
		int c = 0;
		NodeList elem = first;
		while (elem != null) {
			c++;
			elem = elem.getNext();
		}
		return c;
	}

	/**
	 * methode toString : affiche tous les noeuds. Se base sur toString de la
	 * classe Node. A servi lors des differents tests.
	 * 
	 * @return chaine de caractere representant la liste
	 */
	public String toString() {
		// permet de constuire le chaine de caractere a retourner
		StringBuilder sb = new StringBuilder();
		if (sb.length() > 0)
			sb.delete(0, sb.length());
		NodeList elem = first;
		while (elem != null) {
			sb.append(elem.toString() + " - ");
			elem = elem.getNext();
		}
		return sb.toString();
	}

	/**
	 * insere un noeud trie par ordre de poids croissant dans la liste.
	 * 
	 * @param val
	 *            noeud a inserer
	 */
	public void insertSort(NodeList val) {
		NodeList tmp = new NodeList();
		tmp.setNext(first);
		NodeList n = tmp;
		while (n.getNext() != null && n.getNext().compareTo(val) < 0)
			n = n.getNext();
		val.setNext(n.getNext());
		n.setNext(val);
		first = tmp.getNext();
	}

	/**
	 * methode qui recherche si un caractere est deja dans la liste on ne peut
	 * pas chercher comme dans une liste triee car cette liste est triee par
	 * rapport au poids de chaque caractere
	 * 
	 * @param cara
	 *            le caractere rechercher
	 * @return retourne le noeud de la liste ou se trouve le caractere
	 */
	public NodeList search(int cara) {
		if (isEmpty())
			return null;
		else {
			NodeList n = first;
			while (n != null
					&& ((NodeFeuille) n.getVal().getRacine()).getSymbole()
							.getCara() != cara)
				n = n.getNext();
			if (n != null)
				return n;
			else
				return null;
		}
	}

	/**
	 * methode de suppression d n noeud de la liste
	 * 
	 * @param elem
	 *            noeud a supprimer
	 */
	public void erase(NodeList elem) {
		if (elem == first)
			first = first.getNext();
		else {
			NodeList tmp = first;
			while (tmp.getNext() != null && tmp.getNext() != elem)
				tmp = tmp.getNext();
			if (tmp.getNext() == null) {
			} else
				tmp.setNext(tmp.getNext().getNext());
		}
	}

	/**
	 * methode de tri de la liste, tri par poids croissant. tri sous forme de
	 * tri a bulle. Tres mauvaise complexite. TODO ameliorer le tri
	 */
	public void tri() {
		if (isEmpty()) {
		} else {
			NodeList elem = first;
			while (elem != null) {
				if (elem.getNext() != null
						&& elem.getVal().getRacine().getPoids() > elem
								.getNext().getVal().getRacine().getPoids()) {
					NodeList tmp = elem.getNext();
					erase(elem.getNext());
					insertSort(tmp);
					elem = first;
				} else {
					elem = elem.getNext();
				}
			}
		}
	}

	/**
	 * petit test cree une liste et l affiche, on voit que l insertion triee, la
	 * recherche par caractere et la suppresion marchent corectement.
	 * 
	 * @param args
	 *            sans utilitee
	 */
	public static void main(String[] args) {
		ListArbre l = new ListArbre();
		Arbre a = new Arbre();
		Arbre aa = new Arbre();
		Arbre aaa = new Arbre();
		Symbole s = new Symbole('a');
		Symbole sy = new Symbole('b');
		Symbole sym = new Symbole('c');
		NodeArbre m = new NodeFeuille(s, 1);
		NodeArbre mm = new NodeFeuille(sy, 3);
		NodeArbre mmm = new NodeFeuille(sym, 6);
		a.setRacine(m);
		aa.setRacine(mm);
		aaa.setRacine(mmm);
		NodeList n = new NodeList(a);
		NodeList nn = new NodeList(aa);
		NodeList nnn = new NodeList(aaa);
		l.insertSort(n);
		l.insertSort(nn);
		System.out.println(l.length());
		if (l.search('a') != null)
			System.out.println("ok");
		if (l.search('c') == null)
			System.out.println("ok");
		l.erase(nn);
		l.setFirst(nnn);
		nnn.setNext(nn);
		// System.out.println(l);
		l.tri();
		System.out.println(l);
	}
}
