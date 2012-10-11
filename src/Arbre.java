/**
 * Objet Arbre renvoie a l objet Node qui correspond aux noeuds de l arbre.
 * 
 * @author Matt
 * 
 */
public class Arbre {

	/**
	 * premier noeud de l'arbre.
	 */
	protected NodeArbre racine;

	/**
	 * permet d afficher l arbre
	 */
	private StringBuilder sb = new StringBuilder();

	/**
	 * constructeur
	 */
	public Arbre() {
		this.racine = null;
	}

	/**
	 * @return the racine
	 */
	public NodeArbre getRacine() {
		return racine;
	}

	/**
	 * @param racine
	 *            the racine to set
	 */
	public void setRacine(NodeArbre racine) {
		this.racine = racine;
	}

	/**
	 * teste si l'arbre est vide.
	 * 
	 * @return true si l'arbre est vide
	 */
	public boolean isEmpty() {
		return racine == null;
	}

	/**
	 * vide l'arbre
	 */
	public void empty() {
		racine = null;
	}

	/**
	 * 
	 *Methode permetant d inserer un noeud, on inserera comme si on cree un
	 * arbre binaire de recherche avec pour valeur le poids du noeud cette
	 * methode ne sert qu a tester les autre methode concernant les arbre en
	 * effet l insertion des noeuds se fait d une maniere completement
	 * differente base sur un autre algorithme decrit dans la class Compression
	 * 
	 * @param n
	 *            est le noeud a inserer
	 */
	public void insert(NodeArbre n) {
		if (isEmpty())
			racine = n;
		else {
			NodeArbre pere = racine;
			NodeArbre tmp = racine;
			while (tmp != null) {
				pere = tmp;
				if (n.getPoids() < tmp.getPoids())
					tmp = tmp.getLeft();
				else
					tmp = tmp.getRight();
			}
			if (n.getPoids() < pere.getPoids())
				pere.setLeft(n);
			else
				pere.setRight(n);
		}
	}

	/**
	 * methode de parcours infixe de l'arbre ici on met chaque noeud dans le
	 * StringBuilder pour l'affichage
	 * 
	 * @param n
	 *            lors du premier appel ce noeud doit forcement etre la racine
	 */
	public void parcours(NodeArbre n) {
		if (n != null) {
			parcours(n.getLeft());
			sb.append(n.toString() + " ");
			parcours(n.getRight());
		}
	}

	/**
	 * methode qui permet de cree les code associés a chaque caractere, pour
	 * cela on parcour tout l arbre
	 * 
	 * @param n
	 *            lors du premier appel ce noeud doit forcement etre la racine,
	 *            sert a la recurence
	 * @param code
	 *            lors du premiere appel doit etre "", sert a l obtention du
	 *            code
	 */
	public void code(NodeArbre n, String code) {
		if (n != null) {
			if (n.getLeft() == null && n.getRight() == null)
				((NodeFeuille) n).getSymbole().setCode(code);
			else {
				code(n.getLeft(), code + "0");
				code(n.getRight(), code + "1");
			}
		}
	}

	/**
	 * methode toString on utilise le StringBuilder pour creer une chaine de
	 * caracteres et on parcours l arbre afin d afficher tous les noeuds
	 */
	public String toString() {
		parcours(getRacine());
		return sb.toString();
	}

	/**
	 * methode qui permet d assembler les arbre comme le suggere l algorithme de
	 * huffman.
	 * 
	 * @param elem1
	 *            fils gauche de la nouvelle racine
	 * @param elem2
	 *            fils droit de la nouvelle racine
	 * @return un nouvel arbre avec pour poids la somme des deux poids fils
	 */
	public Arbre assembler(Arbre elem1, Arbre elem2) {
		int poids = elem1.getRacine().getPoids() + elem2.getRacine().getPoids();
		Arbre elem = new Arbre();
		NodeArbre noeud = new NodeArbre();
		elem.setRacine(noeud);
		elem.getRacine().setPoids(poids);
		elem.getRacine().setLeft(elem1.getRacine());
		elem.getRacine().setRight(elem2.getRacine());
		return elem;
	}

	/**
	 * simple test cree quelques noeuds, les affichent et assemble de noeud d
	 * arbre
	 * 
	 * @param args
	 *            sans utilitee
	 */
	public static void main(String[] args) {
		Arbre a = new Arbre();
		Symbole s = new Symbole('a');
		Symbole sy = new Symbole('c');
		NodeFeuille n = new NodeFeuille(s, 2);
		a.insert(n);
		NodeFeuille m = new NodeFeuille(sy, 1);
		a.insert(m);
		NodeArbre p = new NodeArbre(4);
		a.insert(p);
		NodeArbre k = new NodeArbre(5);
		a.insert(k);
		NodeFeuille nn = new NodeFeuille(s, 5);
		Symbole sym = new Symbole('a');
		NodeFeuille nnn = new NodeFeuille(sym, 7);
		System.out.println(a);
		Arbre aa = new Arbre();
		aa.setRacine(nn);
		Arbre aaa = new Arbre();
		aaa.setRacine(nnn);
		System.out.println(aa);
		System.out.println(aaa);
		Arbre ass = new Arbre();
		ass = ass.assembler(aa, aaa);
		System.out.println("ok : " + ass);
		ass.code(ass.getRacine(), "");
		NodeFeuille f = new NodeFeuille(s, 42);
		f.getSymbole().setCode("00000");
		Arbre ar = new Arbre();
		ar.setRacine(f);
	}
}
