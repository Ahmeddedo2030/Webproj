package buchladen;

import java.sql.DriverManager;
import java.sql.SQLException;


public class connbuild implements Myprovider{
		
  static java.sql.Connection con= null;

  public static java.sql.Connection getConnection() {
			
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(conn,username,"");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				
				System.out.println(e);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return con;
		}

}
