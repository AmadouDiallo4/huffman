/**
 * 
 */

/**
 * Objet symbole, contient un caractere ainsi que son code associe
 * 
 * @author Matt
 * 
 */
public class Symbole {

	/** caractere sous son code ASCII */
	protected int cara;

	/** code associe au caractere */
	protected String code;

	/**
	 * constructeur sans parametre cree un symbole avec le caractere null
	 */
	public Symbole() {
		this.cara = 0;
		this.code = null;
	}

	/**
	 * constructeur avec caractere mais pas de code, celui ci est initialiser a
	 * null
	 * 
	 * @param c
	 *            le caractere du symbole
	 */
	public Symbole(int c) {
		this.cara = c;
		this.code = null;
	}

	/**
	 * constructeur avec code mais sans caractere. met le caractere *. C est une
	 * exception qui ne doit pas arriver !!
	 * 
	 * @param code
	 *            le code du symbole
	 */
	public Symbole(String code) {
		this.cara = 0;
		this.code = code;
	}

	/**
	 * constructeur avec caractere et code
	 * 
	 * @param c
	 *            le caractere
	 * @param code
	 *            le code
	 */
	public Symbole(int c, String code) {
		this.cara = c;
		this.code = code;
	}

	/**
	 * constructeur avec caractere et code
	 * 
	 * @param code
	 *            le code
	 * @param c
	 *            le caractere
	 */
	public Symbole(String code, int c) {
		this.cara = c;
		this.code = code;
	}

	/**
	 * @return the caractere
	 */
	public int getCara() {
		return cara;
	}

	/**
	 * @param cara
	 *            the caractere to set
	 */
	public void setCara(int cara) {
		this.cara = cara;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * toString
	 * 
	 * @return une chaine correspondant au symbole
	 */
	public String toString() {
		return "cara=" + (char) cara + ", code=" + code;
	}

	/**
	 * test, creation de symbole et affichage
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Symbole s = new Symbole();
		Symbole sy = new Symbole('a');
		Symbole sym = new Symbole('a', "001");
		System.out.println(s + " " + sy + " " + sym);
		s.setCara('r');
		System.out.println(s + " " + sy + " " + sym);
	}
}
