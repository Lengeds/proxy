package libpcap;

import java.util.Properties;

public class My2 {

	public static void main(String[] args) {
		Properties properties=System.getProperties();
		System.out.println("主机"+properties.propertyNames());
		/*for(properties.propertyNames()) {
			
		}*/

		System.setProperty("http.proxySet", "true");  
		 System.setProperty("http.proxyHost", "10.1.2.189");  
		 System.setProperty("http.proxyPort", "80");  
		 System.out.println("设定");
	}

	
}



