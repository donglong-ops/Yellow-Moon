/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.utils;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author Dong Long
 */
public class HttpRequestWrapper {

    public static String getMethod(String link) throws IOException {
        return Request.Get(link)
                .setHeader("Accept-Charset", "utf-8")
                .execute()
                .returnContent()
                .asString();
    }

    public static HttpResponse postMethod(String url, StringEntity entity) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(request);
        return response;
    }
}
