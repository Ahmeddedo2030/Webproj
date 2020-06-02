package buchladen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KategorieImpl {

	static java.sql.Connection con = null;
	static PreparedStatement ps;

	public static String showCategories(int i) {

		String src = "";

		src = "<select id=" + "kategorie" +i+""+ " name=" + "kategorie" + i + "" + " style=" + "margin-top:10px;"
				+ "width:690px;" + "height:30px;" + "border-radius:10px;" + "border-style:ridge;"
				+ "border-color:cadetblue;" + "border-width:3px;"

				+ ">";

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement("select * from kategorien");
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
	
	public static String showCategories2() {

		String src = "";

		src = "<select onchange="+"getValue(this.value)"+" style="+"margin-left:10px;"+"pxpadding-bottom:10px;"+"margin-bottom:5px;"
		+"width:200px;"+"border-radius:10px;"+"id="+"kategorie"+"name="+"kategorie"
		+">";

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement("select * from kategorien");
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

	public static int insertKategorie(Kategorie k) {

		int status = 0;

		con = connbuild.getConnection();
		try {

			ps = con.prepareStatement("insert into kategorien values(default,?)");

			ps.setString(1, k.getName());

			status = ps.executeUpdate();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return status;

	}

	public static Kategorie getKaegorieid(String name) {

		Kategorie k = new Kategorie();

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement("select * from kategorien where Name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				k.setCat_id(rs.getInt(1));

			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return k;
	}

	public static ArrayList<Buch> getBuecherarr(String kategorie) {

		ArrayList<Buch> bucharr = new ArrayList<Buch>();

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement("SELECT b.titel,b.Preis,b.ISBN,b.Sprache,a.name,v.v_name,b.b_anzahl,l.lager_name "
					+ "FROM buch as b INNER JOIN kategorien as k  INNER JOIN book_category as bc INNER JOIN autor as a  INNER JOIN verlag as v INNER JOIN lager as l "
					+ "ON b.book_id = bc.book_id and k.category_id = bc.category_id  and b.verlag_id = v.verlag_id and b.author_id = a.author_id and b.lager_id = l.lager_id WHERE k.Name = ?");

			ps.setString(1, kategorie);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Buch b = new Buch();

				b.setTitel(rs.getString(1));
				b.setPreis(rs.getInt(2));
				b.setISBN(rs.getString(3));
				b.setSprache(rs.getString(4));
				b.setAutor_name(rs.getString(5));
				b.setVerlag_name(rs.getString(6));
				b.setAnzahl(rs.getInt(7));
				b.setLager_name(rs.getString(8));

				bucharr.add(b);
			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return bucharr;
	}

	public static String showBuecherInkategorie(ArrayList<Buch> bucharr) {

		String res = "";

		boolean lock = true;

		res += "<table style=" + "margin-top:30px;" + "margin-left:20px;"
				+ "><tr><th>Titel</th><th>Preis</th><th>ISBN</th><th>Sprache</th><th>Autor"
				+"</th><th>Verlag</th><th>Anzahl</th><th>Lager</th></tr>";

		for (int i = 0; i < bucharr.size(); i++) {

			if (lock) {

				res += "<tr style=" + "background:grey;" + "height:30px;" + "><td>" + bucharr.get(i).getTitel()
						+ "</td><td>" + bucharr.get(i).getPreis() + " $</td><td>" + bucharr.get(i).getISBN()
						+ "</td><td>" + bucharr.get(i).getSprache() + "</td><td>" + bucharr.get(i).getAutor_name()
						+ "</td><td>"+bucharr.get(i).getVerlag_name()+"</td>"
						+"</td><td>"+bucharr.get(i).getAnzahl()+"</td><td>"+bucharr.get(i).getLager_name()+"</tr>";

				lock = false;

			} else {

				res += "<tr style=" + "background:cadetblue;" + "height:30px;" + "><td>" + bucharr.get(i).getTitel()
						+ "</td><td>" + bucharr.get(i).getPreis() + " $</td><td>" + bucharr.get(i).getISBN()
						+ "</td><td>" + bucharr.get(i).getSprache() + "</td><td>" + bucharr.get(i).getAutor_name()
						+ "</td><td>"+bucharr.get(i).getVerlag_name()+"</td>"
						+"</td><td>"+bucharr.get(i).getAnzahl()+"</td><td>"+bucharr.get(i).getLager_name()+"</tr>";

				lock = true;
			}
		}

		res += "</table>";

		return res;
	}
	
	public static String kategorieList(ArrayList<String> kategorien) {

		String res = "";

		boolean lock = true;

		res += "<table style=" + "margin-top:30px;" + "margin-left:400px;"
				+ "><tr><th>Kategorien Liste</th></tr>";

		for (int i = 0; i < kategorien.size(); i++) {

			if (lock) {

				res += "<tr style=" + "background:grey;" + "height:30px;" + "><td>" + kategorien.get(i)
						+"</td></tr>";

				lock = false;

			} else {

				res += "<tr style=" + "background:cadetblue;" + "height:30px;" + "><td>" + kategorien.get(i)
				+"</td></tr>";

				lock = true;
			}
		}

		res += "</table>";

		return res;
	}
	
	public static Kategorie getKategorieByID(int gesucht) {
		Kategorie category = new Kategorie();
		
		con = connbuild.getConnection();

			try {
				ps = con.prepareStatement("SELECT * FROM kategorien WHERE category_id=?");
				ps.setInt(1, gesucht);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					category.setCat_id(rs.getInt("category_id"));
					category.setName(rs.getString("Name"));
				}
				
				con.close();
			} catch (SQLException e) {
				return null;
			}
		return category;
	}
	
	public static List<Kategorie> getKategorieAll() {
		ArrayList<Kategorie> categories = new ArrayList<Kategorie>();
		
		con = connbuild.getConnection();

			try {
				ps = con.prepareStatement("SELECT * FROM kategorien");
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					Kategorie tmp = new Kategorie();
					tmp.setCat_id(rs.getInt("category_id"));
					tmp.setName(rs.getString("Name"));
					categories.add(tmp);
				}
				
				con.close();
			} catch (SQLException e) {
				return null;
			}
		return categories;
	}
	
}
