package buchladen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarenKorbImpl {
	
	static java.sql.Connection con = null;
	static PreparedStatement ps;
	
	public static int insertWarenkorb(WarenKorb wk) {

		int status = 0;

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement("insert into warenkorb values(?,?,?,?,?,?,?)");

			ps.setInt(1, wk.getCustomerid());
			ps.setInt(2, wk.getBookid());
			ps.setString(3, wk.getTitle());
			ps.setInt(4, wk.getPrice());
			ps.setInt(5,wk.getCount());
			ps.setString(6, wk.getImagepath());
			ps.setInt(7, wk.getTotalpreis());

			status = ps.executeUpdate();

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return status;

	}
	
	
	public static boolean chekExistedBookid(int bookid,int userid){
		
		con = connbuild.getConnection();
		
		boolean chek = true;
		
		try {

			ps = con.prepareStatement("SELECT * FROM warenkorb where customerid = ?");
			ps.setInt(1, userid);
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				
				int chekid = rs.getInt("bookid");
				
				if(bookid == chekid) {
					
					chek = false;
					
				}
			}
			
			con.close();

		} catch (SQLException e) {
			
		   e.printStackTrace();
			
		}
		
		return chek;
	}
	
	
	public static ArrayList<WarenKorb> getWarenkorb(int userid) {
		
		con = connbuild.getConnection();
		
		ArrayList<WarenKorb> arrwk = new ArrayList<WarenKorb>();
		
		try {

			ps = con.prepareStatement("SELECT * FROM warenkorb where customerid = ?");
			ps.setInt(1, userid);
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				
			WarenKorb wk = new WarenKorb();
			
			wk.setBookid(rs.getInt("bookid"));
			wk.setTitle(rs.getString("title"));
			wk.setPrice(rs.getInt("preis"));
			wk.setCount(rs.getInt("count"));
			wk.setImagepath(rs.getString("imagepath"));
			wk.setTotalpreis(rs.getInt("totalpreis"));
				
			arrwk.add(wk);
				
			}
			
			con.close();

		} catch (SQLException e) {
			
		   e.printStackTrace();
			
		}
		
		return arrwk;
	}
	
	public static int getCount(int userid) {
		
		con = connbuild.getConnection();
		
		int count = 0;
		
		try {

			ps = con.prepareStatement("SELECT sum(count) FROM warenkorb where customerid = ?");
			ps.setInt(1, userid);
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				
			 count = rs.getInt(1);
				
			}
			
			con.close();

		} catch (SQLException e) {
			
		   e.printStackTrace();
			
		}
		
		return count;
	}
	
	public static void delWarenkorb(int userid) {

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement("delete from warenkorb where customerid = ?");
			ps.setInt(1, userid);

			ps.executeUpdate();

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	
	public static void delSelectedItem(int userid,int bookid) {
		
		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement("delete from warenkorb where customerid = ? and bookid = ?");
			ps.setInt(1, userid);
			ps.setInt(2, bookid);

			ps.executeUpdate();

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}
	
	public static void increSelectedItem(int userid,int bookid) {
		
		con = connbuild.getConnection();
		
		int count = 0;

		try {

			ps = con.prepareStatement("select count from warenkorb where customerid = ? and bookid = ?");
			ps.setInt(1, userid);
			ps.setInt(2, bookid);
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt(1);
				count++;
			
			}
			
			ps = con.prepareStatement("update warenkorb set count = ? where customerid = ? and bookid = ?");
			ps.setInt(1, count);
			ps.setInt(2, userid);
			ps.setInt(3, bookid);
			
			ps.executeUpdate();

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}
	
public static void decreSelectedItem(int userid,int bookid) {
		
		con = connbuild.getConnection();
		
		int count = 0;

		try {

			ps = con.prepareStatement("select count from warenkorb where customerid = ? and bookid = ?");
			ps.setInt(1, userid);
			ps.setInt(2, bookid);
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				
				count = rs.getInt(1);
				count--;
			
			}
			
			ps = con.prepareStatement("update warenkorb set count = ? where customerid = ? and bookid = ?");
			ps.setInt(1, count);
			ps.setInt(2, userid);
			ps.setInt(3, bookid);
			
			ps.executeUpdate();

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}

public static void setTotalpreis(int userid,int bookid) {
	

	con = connbuild.getConnection();
	
	int totalpreis = 0;

	try {

		ps = con.prepareStatement("select preis,count from warenkorb where customerid = ? and bookid = ?");
		ps.setInt(1, userid);
		ps.setInt(2, bookid);
		
		ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			
			totalpreis = rs.getInt(1) * rs.getInt(2);
		
		}
		
		ps = con.prepareStatement("update warenkorb set totalpreis = ? where customerid = ? and bookid = ?");
		ps.setInt(1, totalpreis);
		ps.setInt(2, userid);
		ps.setInt(3, bookid);
		
		ps.executeUpdate();

		con.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e);
	}
	
}

public static int getTotalpreisAllitems(int userid) {
	
	con = connbuild.getConnection();
	
	int total = 0;

	try {

		ps = con.prepareStatement("select totalpreis from warenkorb where customerid = ?");
		ps.setInt(1, userid);
		
		ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			
			total += rs.getInt(1);
		
		}

		con.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e);
	}
	
	return total;
	
}

public static String displayWarenkorb(int userid) {

	con = connbuild.getConnection();

	String res = "";

	try {

		ps = con.prepareStatement("select * from warenkorb where customerid = ?");
		ps.setInt(1, userid);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			res += "<div class= "+"blockbox"+">"
				+ "<img src="+rs.getString(6)+" style="+"height:100px;"+">"
				+ "<span>"+rs.getString(3)+"</span>"
				+ "<span style="+"font-weight:bold;"+"font-size:25px;"+"color:cadetblue;"+"margin-top:40px;"+">Anzahl: "+rs.getInt(5)+"</span>"
				+ "<span style="+"font-weight:bold;"+"font-size:25px;"+"color:cadetblue;"+"margin-top:80px;"+">Preis: "+rs.getInt(7)+" $</span>"
				+"<div id=line><hr></div></div>";
			

		}

		con.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return res;
}


}
