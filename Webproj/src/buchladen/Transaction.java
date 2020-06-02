package buchladen;

public class Transaction {
	
	private int transaction_id;
	private Customer c;
	private int customer_id;
	private String t_date;
	private String karte_nummer;
	private String karte_besitzer;
	private int cvc;
	private String karte_ablauf_datum;
	private String lieferungs_adresse;
	private int total_preis;
	
	
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int cutomer_id) {
		this.customer_id = cutomer_id;
	}
	public String getT_date() {
		return t_date;
	}
	public void setT_date(String t_date) {
		this.t_date = t_date;
	}
	public String getKarte_nummer() {
		return karte_nummer;
	}
	public void setKarte_nummer(String karte_nummer) {
		this.karte_nummer = karte_nummer;
	}
	public String getKarte_besitzer() {
		return karte_besitzer;
	}
	public void setKarte_besitzer(String karte_besitzer) {
		this.karte_besitzer = karte_besitzer;
	}
	public int getCvc() {
		return cvc;
	}
	public void setCvc(int cvc) {
		this.cvc = cvc;
	}
	public String getKarte_ablauf_datum() {
		return karte_ablauf_datum;
	}
	public void setKarte_ablauf_datum(String karte_ablauf_datum) {
		this.karte_ablauf_datum = karte_ablauf_datum;
	}
	public String getLieferungs_adresse() {
		return lieferungs_adresse;
	}
	public void setLieferungs_adresse(String lieferungs_adresse) {
		this.lieferungs_adresse = lieferungs_adresse;
	}
	public int getTotal_preis() {
		return total_preis;
	}
	public void setTotal_preis(int total_preis) {
		this.total_preis = total_preis;
	}
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	
}
