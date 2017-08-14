package kr.pe.ned;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncodeUriTest {


    @Test
    public void test() throws UnsupportedEncodingException {
        String url = "http://localhost:9000/resource";
        String encodedURL = URLEncoder.encode(url, "UTF-8");
        System.out.println(encodedURL);

    }

}
