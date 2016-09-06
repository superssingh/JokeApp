package com.santossingh.jokeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    private AdView mAdView;
    private Button btnFullscreenAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        btnFullscreenAd = (Button) findViewById(R.id.button_jokeTeller);
        btnFullscreenAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

//    public void showJoke(View view) {
//        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
//        endpointsAsyncTask.execute(new Pair<Context, String>(this, "free"));
//    }

//    @Override
//    public void processFinish(String result) {
//        Intent intent = new Intent(com.santossingh.jokeapp.MainActivity.this, JokeActivity
//                .class)
//                .putExtra(JOKE_TAG, result);
//        startActivity(intent);
//    }

}