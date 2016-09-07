package com.santossingh.jokeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.santossingh.joke_androidlibrary.JokeActivity;
import com.santossingh.jokeapp.AsyncTask.AsyncResponse;
import com.santossingh.jokeapp.AsyncTask.EndpointsAsyncTask;

import is.arontibo.library.ElasticDownloadView;

public class SecondActivity extends AppCompatActivity implements AsyncResponse{

    private static final String JOKE_TAG="joke";
    InterstitialAd mInterstitialAd;
    EndpointsAsyncTask endpointsAsyncTask;
    ElasticDownloadView mElasticDownloadView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mElasticDownloadView=(ElasticDownloadView)findViewById(R.id.elastic_download_view);
        endpointsAsyncTask = new EndpointsAsyncTask(this);
        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }

            @Override
            public void onAdClosed() {
                mElasticDownloadView.setProgress(90);
                showJoke();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                mElasticDownloadView.startIntro();
                Toast.makeText(getApplicationContext(), "Ad is opened!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void showJoke(){
        mElasticDownloadView.success();
        endpointsAsyncTask.execute(getString(R
                .string.keyword));
    }

    @Override
    public void processFinish(String result) {
        Intent intent = new Intent(SecondActivity.this, JokeActivity
                .class)
                .putExtra(JOKE_TAG, result);
        startActivity(intent);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        finish();
    }
}