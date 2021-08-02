package com.wise.dominoshelper.utils;

import com.wise.dominoshelper.http.HttpClient;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;

/**
 *
 * @author Wise
 */
public class OrderHelper {

    private static HttpClient web = new HttpClient();

    public static boolean login(String payload) throws IOException {
        HttpResponse r = web.post(new HttpPost(URLs.LOGIN_URL), payload);
        if (r.getStatusLine().getStatusCode() != 200) {
            return false;
        }
        return true;
    }

}
