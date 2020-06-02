package buchladen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuchImpl {

	static java.sql.Connection con = null;
	static PreparedStatement ps;
	

	public static String showLager(int i) {

		String src = "";

		src = "<select id=" + "lager" + i + "" + " name=" + "lager" + i + "" + " style=" + "margin-left:25px;"
				+ "margin-top:10px;" + "width:715px;" + "height:30px;" + "border-radius:10px;" + "border-style:ridge;"
				+ "border-color:cadetblue;" + "border-width:3px;"

				+ ">";

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement("select * from lager");
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

	public static int getLagerid(String name) {

		con = connbuild.getConnection();

		int id = 0;

		try {

			ps = con.prepareStatement("select * from lager where lager_name = ?");
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

	public static String showBuch(int i) {

		String src = "";

		src = "<select id=" + "buch" + i + "" + " name=" + "buch" + i + "" + " style=" + "margin-left:10px;"
				+ "margin-top:10px;" + "width:610px;" + "height:30px;" + "border-radius:10px;" + "border-style:ridge;"
				+ "border-color:cadetblue;" + "border-width:3px;" + "font-size:15px;"

				+ ">";

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement("select * from buch");
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

	public static int getBuchid(String name) {

		con = connbuild.getConnection();

		int id = 0;

		try {

			ps = con.prepareStatement("select * from buch where titel = ?");
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

	public static void delBuch(int id) {

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement("delete from book_category where book_id = ?");
			ps.setInt(1, id);

			ps.executeUpdate();

			ps = con.prepareStatement("delete from buch where book_id = ?");
			ps.setInt(1, id);

			ps.executeUpdate();

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	public static int buchUpdate(String name, Buch b, ArrayList<String> kategorien) {

		int status = 0;

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement(
					"UPDATE buch SET titel=?,Preis=?,ISBN=?,Sprache=?,bildpfad=?,author_id=?,verlag_id=?,b_anzahl=?,lager_id=? WHERE titel = ?");

			ps.setString(1, b.getTitel());
			ps.setInt(2, b.getPreis());
			ps.setString(3, b.getISBN());
			ps.setString(4, b.getSprache());
			ps.setString(5, b.getBildpfad());
			ps.setInt(6, b.getAutor_id());
			ps.setInt(7, b.getVerlag_id());
			ps.setInt(8, b.getAnzahl());
			ps.setInt(9, b.getLager_id());
			ps.setString(10, name);

			status = ps.executeUpdate();

			ps = con.prepareStatement("select * from buch where ISBN = ?");

			ps.setString(1, b.getISBN());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				b.setBook_id(rs.getInt(1));

			}

			ps = con.prepareStatement("delete from book_category where book_id = ?");
			ps.setInt(1, b.getBook_id());

			status = ps.executeUpdate();

			for (int i = 0; i < kategorien.size(); i++) {

				ps = con.prepareStatement("select * from kategorien where Name = ?");

				ps.setString(1, kategorien.get(i));
				ResultSet rs2 = ps.executeQuery();

				while (rs2.next()) {

					ps = con.prepareStatement("insert into book_category values(?,?)");

					ps.setInt(1, b.getBook_id());
					ps.setInt(2, rs2.getInt(1));

					status = ps.executeUpdate();

				}
			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return status;
	}

	public static String getalleBuecher() {

		con = connbuild.getConnection();

		String res = "";

		try {

			ps = con.prepareStatement("SELECT b.titel,b.Preis,b.ISBN,b.Sprache,a.name,v.v_name,b.b_anzahl,l.lager_name "
					+ "FROM buch as b INNER JOIN autor as a  INNER JOIN verlag as v INNER JOIN lager as l "
					+ "ON b.verlag_id = v.verlag_id and b.author_id = a.author_id and b.lager_id = l.lager_id");

			ResultSet rs = ps.executeQuery();

			boolean lock = true;

			res += "<table style=" + "margin-top:30px;" + "margin-left:20px;"
					+ "><tr><th>Titel</th><th>Preis</th><th>ISBN</th><th>Sprache</th><th>Autor"
					+ "</th><th>Verlag</th><th>Anzahl</th><th>Lager</th></tr>";

			while (rs.next()) {

				if (lock) {

					res += "<tr style=" + "background:grey;" + "height:30px;" + "><td>" + rs.getString(1) + "</td><td>"
							+ rs.getInt(2) + " $</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4)
							+ "</td><td>" + rs.getString(5) + "</td><td>" + rs.getString(6) + "</td>" + "</td><td>"
							+ rs.getInt(7) + "</td><td>" + rs.getString(8) + "</tr>";

					lock = false;

				} else {

					res += "<tr style=" + "background:cadetblue;" + "height:30px;" + "><td>" + rs.getString(1)
							+ "</td><td>" + rs.getInt(2) + " $</td><td>" + rs.getString(3) + "</td><td>"
							+ rs.getString(4) + "</td><td>" + rs.getString(5) + "</td><td>" + rs.getString(6) + "</td>"
							+ "</td><td>" + rs.getInt(7) + "</td><td>" + rs.getString(8) + "</tr>";

					lock = true;
				}

			}

			res += "</table>";

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}

	public static int insertBuch(Buch b, ArrayList<String> kategorien) {

		int status = 0;

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement("insert into buch values(default,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, b.getTitel());
			ps.setInt(2, b.getPreis());
			ps.setString(3, b.getISBN());
			ps.setString(4, b.getSprache());
			ps.setString(5, b.getBildpfad());
			ps.setInt(6, b.getAutor_id());
			ps.setInt(7, b.getVerlag_id());
			ps.setInt(8, b.getAnzahl());
			ps.setInt(9, b.getLager_id());

			status = ps.executeUpdate();

			ps = con.prepareStatement("select * from buch where ISBN = ?");

			ps.setString(1, b.getISBN());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				b.setBook_id(rs.getInt(1));

			}

			for (int i = 0; i < kategorien.size(); i++) {

				ps = con.prepareStatement("select * from kategorien where Name = ?");

				ps.setString(1, kategorien.get(i));
				ResultSet rs2 = ps.executeQuery();

				while (rs2.next()) {

					ps = con.prepareStatement("insert into book_category values(?,?)");

					ps.setInt(1, b.getBook_id());
					ps.setInt(2, rs2.getInt(1));

					status = ps.executeUpdate();

				}

			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return status;

	}
	
	public static Buch getBuchByID(int gesucht) {

		con = connbuild.getConnection();
		Buch book = new Buch();

		try {

			ps = con.prepareStatement("SELECT * FROM buch WHERE buch.book_id = ?");
			ps.setInt(1, gesucht);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				book.setBook_id(rs.getInt("book_id"));
				book.setTitel(rs.getString("titel"));
				book.setPreis(rs.getInt("Preis"));
				book.setISBN(rs.getString("ISBN"));
				book.setSprache(rs.getString("Sprache"));
				book.setBildpfad(rs.getString("bildpfad"));
				book.setAutor_id(rs.getInt("author_id"));
				book.setVerlag_id(rs.getInt("verlag_id"));
				book.setAnzahl(rs.getInt("b_anzahl"));
				book.setLager_id(rs.getInt("lager_id"));
			}
			
			con.close();

		} catch (SQLException e) {
			return null;
		}

		return book;

	}

}
