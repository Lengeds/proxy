package httpClientTest;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Http {
	
	
	public static void main(String[] args){
		HttpClient httpClient = HttpClients.createDefault();
		String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=测试&oq=java%E7%9A%84http%26lt%3Blient%E4%BD%BF%E7%94%A8%E7%9A%84%E6%97%B6https%E5%8D%8F%E8%AE%AE%E4%B9%88%3F&rsv_pq=a461b006000016c4&rsv_t=33a3wCs5ovkk2Vio6KGxr8pDqOplsuiegHV2Xe+KgXbwK5fkhV5nkiENGSg&rqlang=cn&rsv_enter=1&inputT=7523&rsv_sug3=51&rsv_sug1=19&rsv_sug7=100&rsv_sug2=0&rsv_sug4=8052&rsv_sug=1";
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			String results = EntityUtils.toString(httpResponse.getEntity());
			System.out.println(results); 
			//System.out.println(httpResponse);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
   }
	
   
	
}
