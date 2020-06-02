package buchladen;

public class Bank_Transaction {
	
	private int transaction_id;
	private Customer c;
	private int customer_id;
	private String Date;
	private String konto_Inhaber;
	private String IBAN;
	private String BIC;
	private String lieferungs_adresse;
	private int total_preis;

	public String getKonto_Inhaber() {
		return konto_Inhaber;
	}
	public void setKonto_Inhaber(String konto_Inhaber) {
		this.konto_Inhaber = konto_Inhaber;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getIBAN() {
		return IBAN;
	}
	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	public String getBIC() {
		return BIC;
	}
	public void setBIC(String bIC) {
		BIC = bIC;
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
}
