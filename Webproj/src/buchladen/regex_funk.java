package buchladen;

public class regex_funk {
	
   public static boolean chekPreis(String preis) {
		
		String Pattern1 = "^(\\d{1,10})$";
		
		return preis.matches(Pattern1);
	}

}
