package buchladen;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionImpl {

	static java.sql.Connection con= null;
	static PreparedStatement ps;
	
	
	public static int insertTransaction(Transaction t,ArrayList<WarenKorb> wkarr) {
       
		int status = 0;
		con = connbuild.getConnection();
		
		try {
			
			ps = con.prepareStatement("insert into bestellung values(default,?,?,?,?,?,?,?,?)");
			
			ps.setInt(1, t.getCustomer_id());
			ps.setString(2, t.getT_date());
			ps.setString(3, t.getKarte_nummer());
			ps.setString(4, t.getKarte_besitzer());
			ps.setInt(5, t.getCvc());
			ps.setString(6, t.getKarte_ablauf_datum());
			ps.setString(7, t.getLieferungs_adresse());
			ps.setInt(8, t.getTotal_preis());
			
			status = ps.executeUpdate();
			
			ps = con.prepareStatement("select * from bestellung where datum = ?");

			ps.setString(1, t.getT_date());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

			  t.setTransaction_id(rs.getInt(1));

			}
			
			for (int i = 0; i < wkarr.size(); i++) {

					ps = con.prepareStatement("insert into buch_bestellung values(?,?,?,?)");

					ps.setInt(1, wkarr.get(i).getBookid());
					ps.setInt(2, t.getTransaction_id());
					ps.setInt(3, wkarr.get(i).getCount());
					ps.setInt(4, wkarr.get(i).getTotalpreis());

					status = ps.executeUpdate();
					
			}
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		return status;
	}
	
	public static int insertBankTransaction(Bank_Transaction bt,ArrayList<WarenKorb> wkarr) {
	       
		int status = 0;
		con = connbuild.getConnection();
		
		try {
			
			ps = con.prepareStatement("insert into bank_bestellung values(default,?,?,?,?,?,?,?)");
			
			ps.setInt(1, bt.getCustomer_id());
			ps.setString(2, bt.getDate());
			ps.setString(3, bt.getKonto_Inhaber());
			ps.setString(4, bt.getIBAN());
			ps.setString(5, bt.getBIC());
			ps.setString(6, bt.getLieferungs_adresse());
			ps.setInt(7, bt.getTotal_preis());
			
			status = ps.executeUpdate();
			
			ps = con.prepareStatement("select * from bank_bestellung where datum = ?");

			ps.setString(1, bt.getDate());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

			  bt.setTransaction_id(rs.getInt(1));

			}
			
			for (int i = 0; i < wkarr.size(); i++) {

					ps = con.prepareStatement("insert into buch_bestellung_bank values(?,?,?,?)");

					ps.setInt(1, wkarr.get(i).getBookid());
					ps.setInt(2, bt.getTransaction_id());
					ps.setInt(3, wkarr.get(i).getCount());
					ps.setInt(4, wkarr.get(i).getTotalpreis());

					status = ps.executeUpdate();
					
			}
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		return status;
	}

}
