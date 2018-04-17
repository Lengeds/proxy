package httpClientTest;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
public class Http {
        
        
        public static void main(String[] args){
                
                SSLContext sslContext = null;
                try {
                        sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
                } catch (Exception e1) {
                        
                        e1.printStackTrace();
                }
                // HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
                 SSLConnectionSocketFactory sslsf=new SSLConnectionSocketFactory(sslContext);
                // CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
                 
        //设置代理IP、端口、协议
        HttpHost proxy = new HttpHost("127.0.0.1", 9000);
       // new Host
        //把代理设置到请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom().setProxy(proxy).build();
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(defaultRequestConfig).build();
        
       // CloseableHttpClient httpClient = HttpClients.createDefault();
                
             //   String url = "http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=测试&oq=java%E7%9A%84http%26lt%3Blient%E4%BD%BF%E7%94%A8%E7%9A%84%E6%97%B6https%E5%8D%8F%E8%AE%AE%E4%B9%88%3F&rsv_pq=a461b006000016c4&rsv_t=33a3wCs5ovkk2Vio6KGxr8pDqOplsuiegHV2Xe+KgXbwK5fkhV5nkiENGSg&rqlang=cn&rsv_enter=1&inputT=7523&rsv_sug3=51&rsv_sug1=19&rsv_sug7=100&rsv_sug2=0&rsv_sug4=8052&rsv_sug=1";
          //    String url="https://etrade.ccbfund.cn/etrading/tradereq/main.do?method=doInit&isHome=1&menuId=10000";
           //  String url ="https://www.cnblogs.com/feiyun126/p/7686302.html";
                String url = "http://www.baidu.com";
             //   String url="http://www.google.com.hk/webhp?hl=zh-CN&sourceid=cnhp";
                HttpGet httpGet = new HttpGet(url);
                try {
                	  //  httpGet.setHeader("Host:", " www.baidu.com:443");
                        HttpResponse httpResponse = httpClient.execute(httpGet);
                        String results = EntityUtils.toString(httpResponse.getEntity());
                        
                        System.out.println("结果:----");
                        System.out.println(results); 
                        //System.out.println(httpResponse);
                } catch (Exception e) {
                        e.printStackTrace();
                } 
                
   }
        
 
         /**
     * 创建一个可以访问Https类型URL的工具类，返回一个CloseableHttpClient实例
     */
    public static CloseableHttpClient createSSLClientDefault(){
        try {
            SSLContext sslContext=new SSLContextBuilder().loadTrustMaterial(
                    null,new TrustStrategy() {
                        //信任所有
                                                @Override
                                                public boolean isTrusted(X509Certificate[] arg0, String arg1)
                                                                throws CertificateException {
                                                        // TODO Auto-generated method stub
                                                        return true;
                                                }

                                                
                    }).build();
            SSLConnectionSocketFactory sslsf=new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }
        
        
        
        
        
}
        
        
        
