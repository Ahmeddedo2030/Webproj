package buchladen;

public class WarenKorb {
	
	private int customerid;
	private int bookid;
	private String title;
	private int price;
	private int count;
	private String imagepath;
	private int totalpreis;
	
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public int getTotalpreis() {
		return totalpreis;
	}
	public void setTotalpreis(int totalpreis) {
		this.totalpreis = totalpreis;
	}
}
