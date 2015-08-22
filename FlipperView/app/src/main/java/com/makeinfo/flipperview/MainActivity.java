package com.makeinfo.flipperview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.aphidmobile.flip.FlipViewController;
import com.makeinfo.flipperview.model.Flower;
import com.makeinfo.flipperview.network.api;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {
    private FlipViewController flipView;

    /**
     * Called when the activity is first created.
     */
    List<Flower> flowerList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("MakeInfo Flipper App");
        flipView = new FlipViewController(this, FlipViewController.VERTICAL);

        //Retrofit
        final RestAdapter restadapter = new RestAdapter.Builder().setEndpoint("https://api.myjson.com").build();
        api flowerapi =  restadapter.create(api.class);
        flowerapi.getData(new Callback<List<Flower>>() {
            @Override
            public void success(List<Flower> flowers, Response response) {
                flowerList = flowers;

             //   flipView.setAdapter(new TravelAdapter(this));
               flipView.setAdapter(new adapter(getApplicationContext(),flowerList));
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(),"Failed", Toast.LENGTH_SHORT).show();
            }
        });







        setContentView(flipView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        flipView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        flipView.onPause();
    }
}
