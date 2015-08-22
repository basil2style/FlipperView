package com.makeinfo.flipperview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aphidmobile.utils.AphidLog;
import com.aphidmobile.utils.IO;
import com.aphidmobile.utils.UI;
import com.makeinfo.flipperview.model.Flower;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Basil on 8/21/2015.
 */
public class adapter extends BaseAdapter {
    private Context context;
    private List<Flower> flowerList;
    private LayoutInflater inflater;
    private int repeatCount = 1;
    ImageView img;
    String imgUrl  = "http://services.hanselandpetal.com/photos/";
    String wikiUrl = "https://en.wikipedia.org/wiki/";

    public adapter(Context context,List<Flower> objects) {
        //super(context,resource,objects);
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.flowerList = objects;

    }

    @Override
    public int getCount() {
        return flowerList.size() * repeatCount;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void removeData(int index) {
        if (flowerList.size() > 1) {
            flowerList.remove(index);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = convertView;
        if (convertView == null) {
            layout = inflater.inflate(R.layout.complex1, null);
            AphidLog.d("created new view from adapter: %d", position);
        }
        final Flower data = flowerList.get(position % flowerList.size());

        UI
                .<TextView>findViewById(layout, R.id.title)
                .setText(AphidLog.format("%d. %s", position, data.getName()));

           img =  UI.findViewById(layout,R.id.photo);
      //  Picasso.with(context).load(imgUrl+data.getPhoto()).into(img);
            boolean needReload = true;
            Picasso.with(context)
                    .load(imgUrl+data.getPhoto())
                    .placeholder(R.drawable.load)
                    .error(R.drawable.error)
                    .fit()
                .into(img);

        UI
                .<TextView>findViewById(layout, R.id.description)
                .setText(Html.fromHtml(data.getInstructions()));

        UI
                .<Button>findViewById(layout, R.id.wikipedia)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(wikiUrl+data.getWikipedia())

                        );
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                        inflater.getContext().startActivity(intent);
                    }
                });



        return layout;
    }
}
