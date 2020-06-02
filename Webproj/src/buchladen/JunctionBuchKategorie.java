package buchladen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JunctionBuchKategorie {
	
	static java.sql.Connection con = null;
	static PreparedStatement ps;
	
	public static List<Buch> getBuchByKategorieID(int gesucht){
		ArrayList<Buch> books = new ArrayList<Buch>();
		
		con = connbuild.getConnection();
		try {
			ps = con.prepareStatement("SELECT book_id FROM book_category WHERE category_id=?");
			ps.setInt(1, gesucht);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				books.add(BuchImpl.getBuchByID(rs.getInt("book_id")));
			}
		}catch(SQLException e) {
			return null;
		}
		
		return books;
	}
	
	public static List<Kategorie> getKategorieByBuchID(int gesucht){
		ArrayList<Kategorie> categories = new ArrayList<Kategorie>();
		
		con = connbuild.getConnection();
		try {
			ps = con.prepareStatement("SELECT category_id FROM book_category WHERE book_id=?");
			ps.setInt(1, gesucht);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				categories.add(KategorieImpl.getKategorieByID(rs.getInt("category_id")));
			}
		}catch(SQLException e) {
			return null;
		}
		
		return categories;
	}
	
}
