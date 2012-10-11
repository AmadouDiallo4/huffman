import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Classe manipulant les objets precedents afin de cree l arbre de Huffman
 * 
 * @author Matt
 * 
 */
public class Compression {

	/** objet representant le fichier source */
	private File leFichierSource;

	/** objet representant le dictionnaire */
	private File leDico;

	/**
	 * objet permettant de lire plus facilement les fichier : permet de lire une
	 * ligne
	 */
	private BufferedReader lecteur;
	private BufferedReader lecteur2;

	/**
	 * objet permettant d'ecrire plus facilement dans un fichier : permet de
	 * faire des print et println
	 */
	private PrintStream dico;

	/**
	 * On cree l arbre de Huffman comme un arbre vide. L algorithme sera charger
	 * de le cree a partir de la liste des caractere
	 */
	private Arbre huffmanTree = new Arbre();

	/**
	 * liste de travail dans laquelle on va mettre les caracteres
	 */
	private ListArbre liste = new ListArbre();

	/**
	 * liste dictionnaire dans laquelle on va mettre les caracteres et le code
	 * associe
	 */
	private ListSymb listeSymb = new ListSymb();

	/**
	 * Objet permettant d ecrire un fichier binaire
	 */
	private BinaryFile bin;

	/**
	 * Constructeur sans parametre, par default, code le fichier ReadMe.txt
	 * Initialise le bufferedReader
	 */
	public Compression() {
		leFichierSource = new File("ReadMe.txt");
		leDico = new File("ReadMeDico.txt");
		bin = new BinaryFile("ReadMeCoder");
		// initialiser le flot de lecture
		lecteur = null;
		lecteur2 = null;
		dico = null;

		try {
			// creer le flot en lecture
			lecteur = new BufferedReader(new FileReader(leFichierSource));
			lecteur2 = new BufferedReader(new FileReader(leFichierSource));
			// creer le flot en ecriture
			dico = new PrintStream(new FileOutputStream(leDico));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

	/**
	 * Constructeur avec parametre
	 * 
	 * @param st
	 *            le nom du fichier sans extension. (pour le fichier test.txt
	 *            taper test)
	 */
	public Compression(String st) {
		leFichierSource = new File(st + ".txt");
		leDico = new File(st + "Dico.txt");
		bin = new BinaryFile(st + "Coder");
		// initialiser le flot de lecture
		lecteur = null;
		lecteur2 = null;
		dico = null;

		try {
			// creer le flot en lecture
			lecteur = new BufferedReader(new FileReader(leFichierSource));
			lecteur2 = new BufferedReader(new FileReader(leFichierSource));
			// creer le flot en ecriture
			dico = new PrintStream(new FileOutputStream(leDico));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

	/**
	 * Methode qui permet de traiter le fichier a compresser en placant les
	 * different caractere dans la liste sous leur caractere ASCII on ne place
	 * pas directement les characteres mais on place un arbre contenant une
	 * racine dans laquel on met le charactere ainsi que sont poids. Si on a un
	 * caractere deja present dans la table on incremente juste son poids.
	 * 
	 * une fois la lecture du fichier terminer, on copie la liste dans une
	 * deuxieme qui sera le dictionnaire.
	 */
	public void traiter() {
		try {
			for (int i = 0; i < leFichierSource.length(); i++) {
				int n = lecteur.read();
				NodeList elem = liste.search(n);
				if (elem != null)
					elem.getVal().getRacine().setPoids(
							elem.getVal().getRacine().getPoids() + 1);
				else {
					NodeList noeudList = new NodeList();
					Arbre a = new Arbre();
					Symbole symbole = new Symbole(n);
					NodeFeuille noeud = new NodeFeuille(symbole, 1);
					a.setRacine(noeud);
					noeudList.setVal(a);
					liste.insertSort(noeudList);
				}
			}

			// une fois la lecture du fichier terminer, on copie la liste dans
			// une deuxieme qui sera le dictionnaire.
			NodeList elem = liste.getFirst();
			while (elem != null) {
				NodeSymb sy = new NodeSymb(((NodeFeuille) elem.getVal()
						.getRacine()).getSymbole());
				listeSymb.insertFirst(sy);
				elem = elem.getNext();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * tir la liste d arbre, ce base sur la methode tri de l objet List
	 */
	public void tri() {
		liste.tri();
	}

	/**
	 * affiche la liste, ce base sur la methode toString de l objet List. A
	 * servi lors des differents tests
	 */
	public void affiche() {
		System.out.println(huffmanTree);
		System.out.println(listeSymb);
	}

	/**
	 * Methode qui cree l arbre de Huffman a partir de la liste.
	 */
	public void algorithme() {
		while (liste.length() > 1) {
			liste.tri();
			NodeList noeud = new NodeList();
			noeud.setVal(liste.getFirst().getVal().assembler(
					liste.getFirst().getVal(),
					liste.getFirst().getNext().getVal()));
			liste.insertSort(noeud);
			liste.erase(liste.getFirst());
			liste.erase(liste.getFirst());
		}
		liste.tri();
		this.huffmanTree = liste.getFirst().getVal();
	}

	/**
	 * code chaque caratere (0 pour gauche, 1 pour droite)
	 */
	public void codage() {
		huffmanTree.code(huffmanTree.getRacine(), "");
	}

	/**
	 * on ecrit le fichier binaire compressé a partir des codes stockes dans la
	 * liste dictionnaire. On ecrit egalement le dictionnaire
	 */
	public void compression() {
		try {

			// on met le premier byte du fichier a 0 car on n a pas utilise
			// compressFast
			// bin.write("0");
			String st = "0";
			for (int i = 0; i < leFichierSource.length(); i++) {
				int n = lecteur2.read();
				// bin.write(searchCode((char) n));
				st += searchCode((char) n);
			}

			bin.write(st);
			bin.writeTo();
			NodeSymb elem = listeSymb.getFirst();
			while (elem != null) {
				dico.println(elem.getSymbole().getCara() + " "
						+ elem.getSymbole().getCode());
				elem = elem.getNext();
			}
			dico.println(st.length()+ " ");  // new
			System.out.println("done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * appel la methode search du dictionnaire
	 * 
	 * @param cara
	 *            le caractere a chercher
	 * @return le code associe
	 */
	public String searchCode(char cara) {
		return listeSymb.search(cara).getSymbole().getCode();
	}

	/**
	 * compression d un fichier
	 * 
	 * @param args
	 *            le nom du fichier a compresser sans extension
	 */
	public static void main(String args) {

		if (!args.equalsIgnoreCase("mon fichier")) {
			Compression comp = new Compression(args);
			comp.traiter();
			comp.algorithme();
			comp.codage();
			comp.compression();
		} else {
			Compression huff = new Compression();
			huff.traiter();
			huff.algorithme();
			huff.codage();
			huff.compression();
		}
	}
}