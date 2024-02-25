package Http;

import com.java.httpserver.Http.HttpParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HttpParserTest {

    private HttpParser httpParser;
    @BeforeAll
    public void beforeClass(){
        this.httpParser = new HttpParser();
    }
    @Test
    void parseHttpRequest(){
        httpParser.parseHttpRequest(
                getValidTestCase()
        );
    }

    InputStream getValidTestCase(){
      String rawData = "GET / HTTP/1.1\r\n" +
              "Host: localhost:8083\r\n" +
              "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:122.0) Gecko/20100101 Firefox/122.0\r\n" +
              "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8\r\n" +
              "Accept-Language: en-US,en;q=0.5\r\n" +
              "Accept-Encoding: gzip, deflate, br\r\n" +
              "Connection: keep-alive\r\n" +
              "Upgrade-Insecure-Requests: 1\r\n" +
              "Sec-Fetch-Dest: document\r\n" +
              "Sec-Fetch-Mode: navigate\r\n" +
              "Sec-Fetch-Site: none\r\n" +
              "Sec-Fetch-User: ?1\r\n" +
              "\r\n";
      InputStream in = new ByteArrayInputStream(
              rawData.getBytes(
                      StandardCharsets.US_ASCII
              )
      );
      return in;
    }
}
