import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 */

/**
 * @author Matt
 * 
 */
public class BinaryFile {

	/** sert a ecire le fichier */
	private FileOutputStream writer;

	/** sert a l ecriture de bytes */
	private ByteArrayOutputStream baos;

	/** sert lors de la lecture */
	private FileInputStream lecteur;

	/**
	 * construcuteur intialise les flots en lecture et ecriture
	 * 
	 * @param outFile
	 *            fichier dans lequel on va ecrire
	 * 
	 */
	public BinaryFile(String outFile) {
		new File("outFile");
		try {
			this.writer = new FileOutputStream(outFile);
			this.baos = new ByteArrayOutputStream();
		} catch (FileNotFoundException e) {
			System.err.println("Impossible to create file " + outFile);
		}
	}

	/**
	 * Constructeur sans parametre, permet d utiliser les methode de lecture de
	 * fichier binaires sans en cree un
	 */
	public BinaryFile() {
	}

	/** write a String containing 0 and 1 into a binary file */
	public void write(String toWrite) {
		int i = 0;
		while (i < toWrite.length() / 7) {
			// System.out.println(toWrite.substring(i*7, (i+1)*7));
			byte data = Byte
					.parseByte(toWrite.substring(i * 7, (i + 1) * 7), 2);
			baos.write(data);
			i++;
		}
		if (toWrite.length() % 7 != 0) {
			String st = toWrite.substring(i * 7);
			while (st.length() < 7)
				st +="0";
			byte data = Byte.parseByte(st, 2);
			baos.write(data);
		}
	}

	/**
	 * ecrit le fichier binaire
	 */
	public void writeTo() {
		try {
			baos.writeTo(writer); // write into file
			writer.close();

		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/** read binary data from a file */
	public String read(String inFile) {
		StringBuilder sb = new StringBuilder();
		try {
			lecteur = new FileInputStream(inFile);
			int val;
			// read next byte into val. -1 means End Of File
			val = lecteur.read();

			while (val != -1) {
				// Convert value into binary String
				// add String to StringBuilder
				String st = Integer.toBinaryString(val);

				val = lecteur.read();
				// ne rajoute pas de 0 a gauche sur le dernier octet car cela change le
				// dernier caractere, mais a droite
				if (val != -1) {
					while (st.length() < 7)
						st = "0" + st;
					sb.append(st);
				} else {
					sb.append(st);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * test ecriture et lecture de fichier
	 * 
	 * @param args
	 *            sans utilitee
	 */
	public static void main(String[] args) {
		BinaryFile by = new BinaryFile("plop");
		by.write("011110011101111111111111110101001010110000000");
		by.writeTo();
		System.out.println(by.read("plop"));
	}
}