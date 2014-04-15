package com.example.bijlicut2.database;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by pgoyal on 03/04/14.
 */
public class API {

    static InputStream is = null;
    static String json = "";
    static JSONObject jObj = null;

    /**
     * This method will execute a post request with provided url and the corresponding parameters
     * @param url URL for the post request
     * @param params Enter the parameters that are needed with the post request
     * @return
     */
    public JSONObject getJSONFromUrl(String url, List<NameValuePair> params) {
        //Make an HTTP request
        Log.e("getJsonFromUrl", "started this method");
        try {
            Log.d("getJsonFromUrl", "getting the http client");

            // defaultHttpClientx
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
            Log.d("getJsonFromUrl", "getting the http client ended");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Log.d("getJsonFromUrl", "getting the buffered reader");

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();

            json = sb.toString();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("getJsonFromUrl", "getting the json object");
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("getJsonFromUrl", "returning from this method");
        Log.e("getJsonFromUrl", jObj.toString());
        return jObj;
    }
}
