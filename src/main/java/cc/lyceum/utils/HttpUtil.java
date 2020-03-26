package cc.lyceum.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * @author Lyceum Hewun
 * @date 2020-03-27 2:30
 */
public class HttpUtil {

    public static Connection connect(String url) {
        return Jsoup.connect(url)
                .ignoreHttpErrors(true);
    }

    public static String getBody(String url) {
        try {
            return connect(url)
                    .execute()
                    .body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
