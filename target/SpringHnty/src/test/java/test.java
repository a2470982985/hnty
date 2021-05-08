import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

public class test {
    @Test
    public void test1(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine().getStatusCode());
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
