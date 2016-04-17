package com.garygrossi.androidcodefoo3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Gary on 4/15/2016.
 */
public class ArticleListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ArticleItem> articles;

    public ArticleListAdapter(Activity activity, List<ArticleItem> articles){
        this.activity = activity;
        this.articles = articles;
    }

    @Override
    public int getCount(){
        return articles.size();
    }

    @Override
    public Object getItem(int location){
        return articles.get(location);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        if(inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.article_item, null);
        }

        TextView timestamp = (TextView) convertView.findViewById(R.id.timestamp);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        ImageView articleImage = (ImageView) convertView.findViewById(R.id.articleImage);

/*        // WHERE DOES THIS GO?!
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

        ArticleItem item = articles.get(position);

        // Convert timestamp and set timestamp value
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSZ");
        long ms = 0;
        try {
            ms = format.parse(item.getTimestamp()).getTime();
        }catch (Exception e){e.printStackTrace();}

        CharSequence timePassed = DateUtils.getRelativeTimeSpanString(ms);
        timestamp.setText(timePassed);

        // Set article title
        title.setText(item.getTitle());

        // Set article image
        articleImage.setImageBitmap(item.getImage());


        return convertView;
    }
}
