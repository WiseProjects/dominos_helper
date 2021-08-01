package com.wise.dominoshelper.http;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Wise
 */
public class HttpClient {

    CloseableHttpClient client;
    private String cookies;
    private String data;
    private byte[] bytes;

    public HttpClient() {
        client = HttpClients.createDefault();
    }

    public String getData() {
        return data;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    private String getCookies(HttpResponse response) {
        String result = "";
        for (Header h : response.getHeaders("set-cookie")) {
            if (h.getName().toLowerCase().equals("set-cookie")) {
                result += h.getValue().replaceAll("(.*?;).*", "$1");
            }
        }
        return result;
    }

    public HttpResponse get(HttpGet request) throws IOException {
        if (cookies != null) {
            request.addHeader("Cookie", cookies);
        }

        HttpResponse response = client.execute(request);

        //Throw runtime exception if status code isn't 200
        if (response.getStatusLine().getStatusCode() != 200) {
            System.err.println("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }

        cookies = getCookies(response);
        getResponseData(response);
        request.releaseConnection();
        return response;
    }

    public HttpResponse post(HttpPost request, String body) throws IOException {
        StringEntity se = new StringEntity(body);
        request.setEntity(se);
        if (cookies != null) {
            request.addHeader("Cookie", cookies);
        }

        HttpResponse response = client.execute(request);

        //Throw runtime exception if status code isn't 200
        if (response.getStatusLine().getStatusCode() != 200) {
            System.err.println("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }

        cookies = getCookies(response);
        getResponseData(response);
        request.releaseConnection();
        return response;
    }

    private void getResponseData(HttpResponse r) throws IOException {
        bytes = IOUtils.toByteArray(r.getEntity().getContent());
        data = new String(bytes, "utf-8");
    }

    private void getResponseData(HttpResponse r, String charset) throws IOException {
        byte[] res = IOUtils.toByteArray(r.getEntity().getContent());

        FileUtils.writeByteArrayToFile(new File("test.torrent"), res);

        /*BufferedReader br = new BufferedReader(new InputStreamReader((r.getEntity().getContent()), Charset.forName(charset)));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        data = sb.toString();
         */
    }
}
