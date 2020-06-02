package buchladen;

import java.util.ArrayList;

public interface CustomerInter {
	
	public int insertCustomer(Customer c);
	public Customer getCustomer(String username,String pass);
	public ArrayList<Transaction> getTransactions(String username);

}
