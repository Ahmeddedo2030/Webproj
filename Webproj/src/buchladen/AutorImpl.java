package buchladen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutorImpl {
	
	static java.sql.Connection con = null;
	static PreparedStatement ps;
	
	public static int insertAutor(Autor a) {

		int status = 0;

		con = connbuild.getConnection();
		try {

			ps = con.prepareStatement("insert into autor values(default,?,?)");

			ps.setString(1, a.getAutor_name());
			ps.setString(2, a.getAutor_kontakt());

			status = ps.executeUpdate();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return status;

	}
	
	public static String showAutor(int i) {

		String src = "";

		src = "<select id=" + "autor" + i + "" + " name=" + "autor" + i + "" + " style=" + "margin-left:25px;"
				+ "margin-top:10px;" + "width:715px;" + "height:30px;" + "border-radius:10px;" + "border-style:ridge;"
				+ "border-color:cadetblue;" + "border-width:3px;"

				+ ">";

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement("select * from autor");
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
	
	public static int getAutorid(String name) {

		con = connbuild.getConnection();

		int id = 0;

		try {

			ps = con.prepareStatement("select * from autor where name = ?");
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
	
	public static Autor getAutorByID(int gesucht) {
		Autor author = new Autor();
		
		con = connbuild.getConnection();

			try {
				ps = con.prepareStatement("SELECT * FROM autor WHERE author_id=?");
				ps.setInt(1, gesucht);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					author.setAutor_id(rs.getInt("author_id"));
					author.setAutor_name(rs.getString("name"));
					author.setAutor_kontakt(rs.getString("kontakt"));
				}
				
				con.close();
			} catch (SQLException e) {
				return null;
			}
		return author;
	}

}
