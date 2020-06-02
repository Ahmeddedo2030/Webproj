package buchladen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerlagImpl {
	

	static java.sql.Connection con = null;
	static PreparedStatement ps;
	
	public static int insertVerlag(Verlag v) {

		int status = 0;

		con = connbuild.getConnection();
		try {

			ps = con.prepareStatement("insert into verlag values(default,?,?,?)");

			ps.setString(1, v.getVerlag_name());
			ps.setString(2, v.getVerlag_adresse());
			ps.setString(3, v.getVerlag_kontakt());

			status = ps.executeUpdate();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return status;

	}
	
	public static String showVerlag(int i) {

		String src = "";

		src = "<select id=" + "verlag" + i + "" + " name=" + "verlag" + i + "" +" style=" + "margin-left:20px;"
				+ "margin-top:10px;" + "width:715px;" + "height:30px;" + "border-radius:10px;" + "border-style:ridge;"
				+ "border-color:cadetblue;" + "border-width:3px;"

				+ ">";

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement("select * from verlag");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				src += "<option>" + rs.getString(2) + "</option>";

			}

			src += "</select>";

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return src;

	}
	
	public static int getVerlagid(String name) {

		con = connbuild.getConnection();

		int id = 0;

		try {

			ps = con.prepareStatement("select * from verlag where v_name = ?");
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				id = rs.getInt(1);

			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return id;
	}
	
	public static Verlag getVerlagByID(int gesucht) {
		Verlag publisher = new Verlag();
		
		con = connbuild.getConnection();

			try {
				ps = con.prepareStatement("SELECT * FROM verlag WHERE verlag_id=?");
				ps.setInt(1, gesucht);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					publisher.setVerlag_id(rs.getInt("verlag_id"));
					publisher.setVerlag_name(rs.getString("v_name"));
					publisher.setVerlag_adresse(rs.getString("v_adresse"));
					publisher.setVerlag_kontakt(rs.getString("v_kontakt"));
				}
				
				con.close();
			} catch (SQLException e) {
				return null;
			}
		return publisher;
	}

}
