import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */

/**
 * Methodes de decompression. Le choix entre la methode de decompression
 * classique et la methode de decompression associée a la compression rapide ce
 * fait par le boolean decompFast qui sera une case a cocher ou non dans l
 * interface graphique
 * 
 * @author Matt
 * 
 */
public class Decompression {

	/**
	 * objet representant le fichier source, c est a dire le fichier a
	 * decompresser
	 */

	/** objet representant le fichier destination */
	private File leFichierDest;

	/** objet representant le dictionnaire */
	private File leDico;

	/**
	 * objet permettant de lire plus facilement les fichier : permet de lire une
	 * ligne
	 */
	private BufferedReader lecteur;

	private BinaryFile by;
	private String chaine;

	/**
	 * objet permettant d'ecrire plus facilement dans un fichier : permet de
	 * faire des print et println
	 */
	private PrintStream ecrivain;

	/**
	 * liste : dictionnaire dans laquelle on va mettre les caracteres et le code
	 * associe
	 */
	private ListSymb listeSymb = new ListSymb();

	/** Longueur de la chaine de 0 et de 1, permet de sarretter au bon moment */
	private int lenght;

	/** boolean isCompressFast ? */
	private boolean isCompressFast;

	/**
	 * Constructeur sans argument, par defaults decompresse le fichier
	 * ReadMeCoder.txt ! Initialise les bufferedReader, les lecteurs
	 * 
	 * @throws IOException
	 */
	public Decompression() throws IOException {
		leFichierDest = new File("ReadMeDecoder.txt");
		by = new BinaryFile();
		chaine = by.read("ReadMeCoder");

		// initialiser le flot de lecture
		lecteur = null;
		ecrivain = null;

		this.lenght = 0;

		try {
			// creer le flot en ecriture
			ecrivain = new PrintStream(new FileOutputStream(leFichierDest));

			// test le premier byte du fichier code afin de determiner quel
			// dictionnaire utiliser

			if (chaine.substring(0, 1).equals("1")) {
				this.isCompressFast = true;
				leDico = new File("Dico.txt");
			} else {
				leDico = new File("ReadMeDico.txt");
				this.isCompressFast = false;
			}
			lecteur = new BufferedReader(new FileReader(leDico));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

	/**
	 * Constructeur avec parametre
	 * 
	 * @param st
	 *            le nom du fichier a decoder (pour le fichier testCoder.txt
	 *            taper test)
	 * @throws IOException
	 */
	public Decompression(String st) throws IOException {
		leFichierDest = new File(st + "Decoder.txt");
		by = new BinaryFile();
		chaine = by.read(st + "Coder");
		// initialiser le flot de lecture
		lecteur = null;
		ecrivain = null;

		this.lenght = 0;

		try {
			// creer le flot en ecriture
			ecrivain = new PrintStream(new FileOutputStream(leFichierDest));

			// test permetant de savoir si le fichier a ete compresser avec ou
			// sans compressFast
			if (chaine.substring(0, 1).equals("1")) {
				leDico = new File("Dico.txt");
				this.isCompressFast = true;
			} else {
				leDico = new File(st + "Dico.txt");
				this.isCompressFast = false;
			}
			lecteur = new BufferedReader(new FileReader(leDico));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

	/**
	 * lit le fichier dictionnaire et stock chaque caractere sous son code ASCII
	 * ainsi que chaque code associe dans la liste dictionnaire
	 */
	public void recupDico() {
		try {

			String cara = "";
			String st = lecteur.readLine();
			while (st != null) {
				int i = 0;
				while (st.toCharArray()[i] != ' ') {
					cara += st.toCharArray()[i];
					i++;
				}
				if (!isCompressFast && st.substring(i + 1).length() == 0)
					this.lenght = Integer.parseInt(cara) ;

				else {
					Symbole symb = new Symbole(Integer.parseInt(cara), st
							.substring(i + 1));
					NodeSymb node = new NodeSymb(symb);
					listeSymb.insertFirst(node);
				}
				st = lecteur.readLine();
				cara = "";

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * decompresse le fichier en parcours la liste a la recherche du caractere
	 * associe a chaque code
	 */
	public void decompression() {
		recupDico();

		// si le fichier n a pas ete compresser avec compressFast,
		// on recupere le nombre de caractere du fichier
		if (!isCompressFast) {
			int i = 1;
			String code = "";
			while (i < this.lenght && i < chaine.length()) {
				if (i < chaine.length() - chaine.length() % 7) {
					code = "";
					while (i < chaine.length() - chaine.length() % 7
							&& listeSymb.searchcode(code) == null) {
						code += chaine.subSequence(i, i + 1);
						i++;
					}
				} else {
					i=this.lenght;
					String st = chaine.substring(i);
					while (st.length() < this.lenght % 7) {
						st = "0" + st;
						
					}
					code += st;
				}
				if (listeSymb.searchcode(code) != null)
					ecrivain.append((char) listeSymb.searchcode(code)
							.getSymbole().getCara());
			}
		} else {
			int i = 1;
			while (i < chaine.length()) {
				String code = "";
				while (i < chaine.length()
						&& listeSymb.searchcode(code) == null) {
					code += chaine.subSequence(i, i + 1);
					i++;
				}
				if (listeSymb.searchcode(code) != null)
					ecrivain.append((char) listeSymb.searchcode(code)
							.getSymbole().getCara());
			}

		}

		System.out.println("done");
	}

	/**
	 * decompresse un fichier.
	 * 
	 * @param args
	 *            le nom du fichier a decompresser sans extension
	 * @throws IOException
	 */
	public static void main(String args) throws IOException {

		if (!args.equalsIgnoreCase("mon fichier")) {
			Decompression dec = new Decompression(args);
			dec.decompression();
		} else {
			Decompression dec = new Decompression();
			dec.decompression();
		}
	}
}