package com.garygrossi.androidcodefoo3;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gary on 4/12/2016.
 */
public class FetchContent {

    public static JSONObject getJSON(String feedURL){
        Log.d("IGN", "getting JSON");
        try{
            URL url = new URL(feedURL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null) {
                json.append(tmp).append("\n");
            }
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            return data;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
