package com.garygrossi.androidcodefoo3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    public final static String ARTICLE_URL = "com.garygrossi.androidcodefoo3.MESSAGE";
    private ListView listView;
    private List<ArticleItem> articles;
    private ArticleListAdapter listAdapter;
    private String URL_FEED = "http://ign-apis.herokuapp.com/articles?startIndex=0&count=10";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        articles = new ArrayList<>();
        listAdapter = new ArticleListAdapter(this, articles);
        listView.setAdapter(listAdapter);
        handler = new Handler();

        updateArticleData();
    }

    private void updateArticleData(){
        new Thread(){
            public void run(){
                final JSONObject json = FetchContent.getJSON(URL_FEED);
                if(json == null){
                    Log.d("IGN", "No JSON");
                }else{
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            parseJSON(json);
                        }
                    });
                }
            }
        }.start();
    }

    private void parseJSON(JSONObject response){
        try{

            JSONArray articleArray = response.getJSONArray("data");

            for(int i = 0; i < articleArray.length(); i++){
                JSONObject articleObject = (JSONObject) articleArray.get(i);
                String publishDate = articleObject.getJSONObject("metadata").getString("publishDate");
                String headline = articleObject.getJSONObject("metadata").getString("headline");
                String slug = articleObject.getJSONObject("metadata").getString("slug");
                String url = constructURL(publishDate, slug);
                Log.d("IGN", url);

                ArticleItem item = new ArticleItem();

                item.setTimestamp(publishDate);
                item.setTitle(headline);
                item.setUrl(url);
                item.setImage(new MyDownloadTask().execute(articleObject.getString("thumbnail")).get());

                articles.add(item);
            }

            listAdapter.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("IGN", "JSON data not found.");
        }
    }

    private String constructURL(String publishDate, String slug){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSZ");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dateURL = null;
        try {
            dateURL = targetFormat.format(format.parse(publishDate));
        }catch (Exception e){e.printStackTrace();}

        return "http://ign.com/articles/" + dateURL + "/" + slug;
    }

    public void openWebview(View view){
        String url = articles.get(listView.getPositionForView(view)).getUrl();

        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("articleURL", url);
        startActivity(intent);
    }

    // A background task that allows downloading of images from a network
    class MyDownloadTask extends AsyncTask<String, Void, Bitmap>
    {
        protected void onPreExecute() {
            // No pre execution tasks are needed
        }

        // Attempts to download an image from the given URL into to a bitmap
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                return BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(Bitmap result) {
            // No post execution tasks are needed
        }
    }
}
