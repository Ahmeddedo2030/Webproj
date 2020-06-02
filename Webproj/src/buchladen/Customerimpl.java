package buchladen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Customerimpl implements CustomerInter {

	static java.sql.Connection con = null;
	static PreparedStatement ps;

	@Override
	public int insertCustomer(Customer c) {

		int status = 0;

		con = connbuild.getConnection();
		try {

			ps = con.prepareStatement("insert into kunde values(default,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, c.getF_name());
			ps.setString(2, c.getL_name());
			ps.setString(3, c.getUsername());
			ps.setString(4, c.getPassword());
			ps.setString(5, c.getB_date());
			ps.setString(6, c.getEmail());
			ps.setString(7, c.getGender());
			ps.setString(8, c.getPLZ());
			ps.setString(9, c.getOrt());
			ps.setString(10, c.getStrasse());
			ps.setString(11, c.getBeruf());

			status = ps.executeUpdate();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return status;
	}

	@Override
	public Customer getCustomer(String username, String pass) {

		Customer c = new Customer();

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement("select * from kunde where username = ? and pwd=?");
			ps.setString(1, username);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
                c.setC_id(rs.getInt(1));
				c.setF_name(rs.getString(2));
				c.setL_name(rs.getString(3));
				c.setUsername(rs.getString(4));
				c.setPassword(rs.getString(5));

			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return c;
	}

	@Override
	public ArrayList<Transaction> getTransactions(String username) {

		ArrayList<Transaction> tranarr = new ArrayList<Transaction>();
		Customer c = new Customer();

		con = connbuild.getConnection();

		try {

			ps = con.prepareStatement(
					"SELECT c.f_name,c.l_name,c.username,b.bestellung_id,b.datum,b.kart_nummer,b.kart_besitzer,b.CVC,b.kart_ablauf_datum,b.lieferungs_adresse,b.total_preis FROM kunde as c INNER JOIN bestellung as b ON c.customer_id = b.customer_id WHERE c.username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Transaction t = new Transaction();

				c.setF_name(rs.getString(1));
				c.setL_name(rs.getString(2));
				c.setUsername(rs.getString(3));
				t.setC(c);
				t.setTransaction_id(rs.getInt(4));
				t.setT_date(rs.getString(5));
				t.setKarte_nummer(rs.getString(6));
				t.setKarte_besitzer(rs.getString(7));
				t.setCvc(rs.getInt(8));
				t.setKarte_ablauf_datum(rs.getString(9));
				t.setLieferungs_adresse(rs.getString(10));
				t.setTotal_preis(rs.getInt(11));

				tranarr.add(t);

			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return tranarr;
	}

	public static String showTransactions(ArrayList<Transaction> arrtran) {

		String res = "";

		boolean lock = true;

		res += "<table style=" + "margin-top:30px;" + "margin-left:20px;"
				+ "><tr><th>Vorname</th><th>Nachname</th><th>Benutzername</th><th>Transaktion_ID</th>"
				+ "<th>Datum</th><th>Kartenummer</th><th>Kartebesitzer</th><th>CVC</th><th>Ablaufdatum</th>"
				+ "<th>Lieferungsadresse</th><th>Total</th></tr>";

		for (int i = 0; i < arrtran.size(); i++) {

			if (lock) {

				res += "<tr style=" + "background:grey;" + "height:30px;" + "><td>" + arrtran.get(i).getC().getF_name()
						+ "</td><td>" + arrtran.get(i).getC().getL_name() + "</td>" + "<td>"
						+ arrtran.get(i).getC().getUsername() + "</td><td>" + arrtran.get(i).getTransaction_id()
						+ "</td>" + "<td>" + arrtran.get(i).getT_date() + "</td><td>" + arrtran.get(i).getKarte_nummer()
						+ "</td>" + "<td>" + arrtran.get(i).getKarte_besitzer() + "</td><td>" + arrtran.get(i).getCvc()
						+ "</td>" + "<td>" + arrtran.get(i).getKarte_ablauf_datum() + "</td><td>"
						+ arrtran.get(i).getLieferungs_adresse() + "</td>" + "<td>" + arrtran.get(i).getTotal_preis()
						+ " $</td></tr>";

				lock = false;

			} else {

				res += "<tr style=" + "background:cadetblue;" + "height:30px;" + "><td>"
						+ arrtran.get(i).getC().getF_name() + "</td><td>" + arrtran.get(i).getC().getL_name() + "</td>"
						+ "<td>" + arrtran.get(i).getC().getUsername() + "</td><td>"
						+ arrtran.get(i).getTransaction_id() + "</td>" + "<td>" + arrtran.get(i).getT_date()
						+ "</td><td>" + arrtran.get(i).getKarte_nummer() + "</td>" + "<td>"
						+ arrtran.get(i).getKarte_besitzer() + "</td><td>" + arrtran.get(i).getCvc() + "</td>" + "<td>"
						+ arrtran.get(i).getKarte_ablauf_datum() + "</td><td>" + arrtran.get(i).getLieferungs_adresse()
						+ "</td>" + "<td>" + arrtran.get(i).getTotal_preis() + " $</td></tr>";

				lock = true;
			}
		}

		res += "</table>";

		return res;
	}

	public static String getallUsers() {

		Customer c = new Customer();
		con = connbuild.getConnection();

		String res = "";

		try {

			ps = con.prepareStatement("select * from kunde");
			ResultSet rs = ps.executeQuery();

			boolean lock = true;

			res += "<table style=" + "margin-top:30px;" + "margin-left:20px;"
					+ "><tr><th>Vorname</th><th>Nachname</th><th>Benutzername</th><th>Passwort</th>"
					+ "<th>Geburtsdatum</th><th>E-mail</th><th>Geschlecht</th><th>PLZ</th><th>Ort</th>"
					+ "<th>Strasse</th><th>Beruf</th></tr>";

			while (rs.next()) {

				c.setF_name(rs.getString(2));
				c.setL_name(rs.getString(3));
				c.setUsername(rs.getString(4));
				c.setPassword(rs.getString(5));
				c.setB_date(rs.getString(6));
				c.setEmail(rs.getString(7));
				c.setGender(rs.getString(8));
				c.setPLZ(rs.getString(9));
				c.setOrt(rs.getString(10));
				c.setStrasse(rs.getString(11));
				c.setBeruf(rs.getString(12));

				if (lock) {

					res += "<tr style=" + "background:grey;" + "height:30px;" + "><td>" + c.getF_name() + "</td><td>"
							+ c.getL_name() + "</td>" + "<td>" + c.getUsername() + "</td><td>" + c.getPassword()
							+ "</td>" + "<td>" + c.getB_date() + "</td><td>" + c.getEmail() + "</td>" + "<td>"
							+ c.getGender() + "</td><td>" + c.getPLZ() + "</td>" + "<td>" + c.getOrt() + "</td><td>"
							+ c.getStrasse() + "</td>" + "<td>" + c.getBeruf() + "</td></tr>";

					lock = false;

				} else {

					res += "<tr style=" + "background:cadetblue;" + "height:30px;" + "><td>" + c.getF_name()
							+ "</td><td>" + c.getL_name() + "</td>" + "<td>" + c.getUsername() + "</td><td>"
							+ c.getPassword() + "</td>" + "<td>" + c.getB_date() + "</td><td>" + c.getEmail() + "</td>"
							+ "<td>" + c.getGender() + "</td><td>" + c.getPLZ() + "</td>" + "<td>" + c.getOrt()
							+ "</td><td>" + c.getStrasse() + "</td>" + "<td>" + c.getBeruf() + "</td></tr>";

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

}
