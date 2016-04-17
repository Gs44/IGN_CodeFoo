package com.garygrossi.androidcodefoo3;

import android.graphics.Bitmap;

/**
 * Created by Gary on 4/15/2016.
 * POJO to access data for each article
 */
public class ArticleItem {
    private int id;
    private Bitmap image;
    private String timestamp, title, url;

    public ArticleItem(){
    }

    public ArticleItem(int id, String timestamp, String title, Bitmap image, String url){
        super();
        this.id = id;
        this.timestamp = timestamp;
        this.title = title;
        this.image = image;
        this.url = url;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTimestamp(){
        return timestamp;
    }

    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Bitmap getImage(){
        return image;
    }

    public void setImage(Bitmap image){
        this.image = image;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }
}
