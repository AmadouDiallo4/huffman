import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Methode de compression rapide. On utilise un arbre deja cree
 * 
 * @author Matt
 * 
 */
public class CompressFast {

	/** objet representant le fichier source */
	private File leFichierSource;

	/** objet representant le fichier destination */
	private BinaryFile leFichierDest;

	/** objet representant le dictionnaire */
	private File leDico;

	/**
	 * objet permettant de lire plus facilement les fichier : permet de lire une
	 * ligne
	 */
	private BufferedReader lecteur;
	private BufferedReader dico;

	/**
	 * liste dictionnaire dans laquelle on va mettre les caracteres et le code
	 * associe a partir du dico deja existant
	 */
	private ListSymb listeSymb = new ListSymb();

	/**
	 * Constructeur sans parametre, par default, code le fichier ReadMe.txt
	 * Initialise le bufferedReader
	 */
	public CompressFast() {
		leFichierSource = new File("ReadMe.txt");
		leFichierDest = new BinaryFile("ReadMeCoder");
		leDico = new File("Dico.txt");
		// initialiser le flot de lecture

		lecteur = null;
		dico = null;

		try {
			// creer le flot en lecture
			lecteur = new BufferedReader(new FileReader(leFichierSource));
			dico = new BufferedReader(new FileReader(leDico));
			// creer le flot en ecriture

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
	public CompressFast(String st) {
		leFichierSource = new File(st + ".txt");
		leFichierDest = new BinaryFile(st + "Coder");
		leDico = new File("Dico.txt");
		// initialiser le flot de lecture
		lecteur = null;
		dico = null;

		try {
			// creer le flot en lecture
			lecteur = new BufferedReader(new FileReader(leFichierSource));
			dico = new BufferedReader(new FileReader(leDico));
			// creer le flot en ecriture

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

	/**
	 * on ecrit le fichier compresser a partir des codes stockes dans la liste
	 * dictionnaire.
	 */
	public void compression() {
		try {
			// on met le premier byte du fichier a 1 car on a utilise
			// compressFast
			String st = "1";
			for (int i = 0; i < leFichierSource.length(); i++) {
				int n = lecteur.read();
				st+=searchCode((char) n);
			}
			leFichierDest.write(st);
			leFichierDest.writeTo();
			System.out.println("done");
		} catch (IOException e) {
			System.out.println("erreur, utilise la compression classique");
			e.printStackTrace();
		}
	}

	/**
	 * lit le fichier dictionnaire et stock chaque caractere sous son code ASCII
	 * ainsi que chaque code associe dans la liste dictionnaire
	 */
	public void recupDico() {
		try {
			String cara = "";
			String st = dico.readLine();
			while (st != null) {
				int i = 0;
				while (st.toCharArray()[i] != ' ') {
					cara += st.toCharArray()[i];
					i++;
				}
				Symbole symb = new Symbole(Integer.parseInt(cara), st
						.substring(i + 1));
				NodeSymb node = new NodeSymb(symb);
				listeSymb.insertFirst(node);
				st = dico.readLine();
				cara = "";
			}
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
	 * Compression de fichier
	 * 
	 * @param args
	 *            le nom du fichier a compresser sans extension
	 */
	public static void main(String args) {
		if (!args.equalsIgnoreCase("mon fichier")) {

			CompressFast comp = new CompressFast(args);
			comp.recupDico();
			comp.compression();
		} else {
			CompressFast comp = new CompressFast();
			comp.recupDico();
			comp.compression();
		}
	}
}