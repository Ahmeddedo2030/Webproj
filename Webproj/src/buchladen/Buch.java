package buchladen;

public class Buch {
	
	private int book_id;
	private String titel;
	private int preis;
	private String ISBN;
	private String Sprache;
	private String bildpfad;
	private int anzahl;
	private int verlag_id;
	private int autor_id;
	private int lager_id;
	private String verlag_name;
	private String autor_name;
	private String lager_name;
	
	public String getVerlag_name() {
		return verlag_name;
	}
	public void setVerlag_name(String verlag_name) {
		this.verlag_name = verlag_name;
	}
	public String getAutor_name() {
		return autor_name;
	}
	public void setAutor_name(String autor_name) {
		this.autor_name = autor_name;
	}
	public String getLager_name() {
		return lager_name;
	}
	public void setLager_name(String lager_name) {
		this.lager_name = lager_name;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public int getPreis() {
		return preis;
	}
	public void setPreis(int preis) {
		this.preis = preis;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getSprache() {
		return Sprache;
	}
	public void setSprache(String sprache) {
		Sprache = sprache;
	}
	public String getBildpfad() {
		return bildpfad;
	}
	public void setBildpfad(String bildpfad) {
		this.bildpfad = bildpfad;
	}
	public int getAnzahl() {
		return anzahl;
	}
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}
	public int getVerlag_id() {
		return verlag_id;
	}
	public void setVerlag_id(int verlag_id) {
		this.verlag_id = verlag_id;
	}
	public int getAutor_id() {
		return autor_id;
	}
	public void setAutor_id(int autor_id) {
		this.autor_id = autor_id;
	}
	public int getLager_id() {
		return lager_id;
	}
	public void setLager_id(int lager_id) {
		this.lager_id = lager_id;
	}
}
