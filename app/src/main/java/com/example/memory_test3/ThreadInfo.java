package com.example.memory_test3;

import android.graphics.drawable.Drawable;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

public class ThreadInfo extends Thread{
    private boolean finished;
    private JSONArray quizs;
    private String url_path;

    public void run() {
        try {

            //api version 23以上なら走らない
            HttpClient httpClient = new DefaultHttpClient();

            HttpGet httpGet = new HttpGet(url_path);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String str = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            //System.out.println(str);

            //デコード
            quizs = new JSONArray(str);
            //Iterator<String> sss = quizs.keys();
//            System.out.println(quizs);
//            JSONObject key = quizs.getJSONObject("1");
//            System.out.println(key.getString("name"));
//            System.out.println(quizs.keys());

        } catch(Exception ex) {
            System.out.println(ex);
        }

        finished = true;
    }

    void setUrl(String set_url_path){
        url_path = set_url_path;
    }

    JSONArray getResult() {
        return quizs;
    }

    boolean finished() {
        return finished;
    }
}
