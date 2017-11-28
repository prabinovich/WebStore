package store;

public class SanitizationUtils {
	
	public static String validateInput(String s)
	{
		String safeS = null;
	    if (s.matches("[a-zA-Z0-9]+")){
	    	safeS = s;
	    }
	    else {
	    	safeS = "";
	    }
	    return safeS;
	}
}
