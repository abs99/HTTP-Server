package Http;

import com.java.httpserver.Exceptions.HttpParsingException;
import com.java.httpserver.Http.HttpMethod;
import com.java.httpserver.Http.HttpParser;
import com.java.httpserver.Http.HttpRequest;
import com.java.httpserver.Http.HttpStatusCode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.commons.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HttpParserTest {

    private HttpParser httpParser;
    @BeforeAll
    public void beforeClass(){
        this.httpParser = new HttpParser();
    }
    @Test
    void parseHttpRequest(){
        HttpRequest req = null;
        try {
            req = httpParser.parseHttpRequest(
                    getValidTestCase()
            );

        } catch (HttpParsingException e) {
            fail(e);
            e.printStackTrace();

        }
        assertEquals(HttpMethod.GET,req.getMethod());

    }

    @Test
    void parseHttpRequestBadMethod1(){
       try {
           HttpRequest req = httpParser.parseHttpRequest(
                   getBadMethodNameTestCase1()
           );
           fail();
       }catch (HttpParsingException e){
            assertEquals(e.getErrorCode(), HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
       }


    }

    @Test
    void parseHttpRequestBadMethod2(){
        try {
            HttpRequest req = httpParser.parseHttpRequest(
                    getBadMethodNameTestCase2()
            );
            fail();
        }catch (HttpParsingException e){
            assertEquals(e.getErrorCode(), HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }


    }

    @Test
    void parseHttpRequestInvalidNumArgsReqLine(){
        try {
            HttpRequest req = httpParser.parseHttpRequest(
                    getInvalidNumArgsinReqLineTestCase()
            );
            fail();
        }catch (HttpParsingException e){
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_BAD_REQUEST);
        }


    }

    @Test
    void parseHttpRequestEmptyReqLine(){
        try {
            HttpRequest req = httpParser.parseHttpRequest(
                    getInvalidTestCaseEmptyReqLine()
            );
            fail();
        }catch (HttpParsingException e){
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_BAD_REQUEST);
        }


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

    InputStream getBadMethodNameTestCase1(){
        String rawData = "GETo / HTTP/1.1\r\n" +
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
    InputStream getBadMethodNameTestCase2(){
        String rawData = "GETTYYPYPYPYYP / HTTP/1.1\r\n" +
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

    InputStream getInvalidNumArgsinReqLineTestCase(){
        String rawData = "GET GIBBERISH / HTTP/1.1\r\n" +
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

    InputStream getInvalidTestCaseEmptyReqLine(){
        String rawData = "\r\n" +
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
