package com.santossingh.jokeapp.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.santossingh.joke_androidlibrary.JokeActivity;
import com.santossingh.jokeapp.AsyncTask.AsyncResponse;
import com.santossingh.jokeapp.AsyncTask.EndpointsAsyncTask;
import com.santossingh.jokeapp.R;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

//Due to Null_Pointer_Exception with AdView and Button Butternife bind not working.
//For solution I just follow simple findViewById method. Here is the stackoverflow link :

    private static final String JOKE_TAG = "joke";
    @BindView(R.id.avi) AVLoadingIndicatorView avLoadingIndicatorView;
    @BindView(R.id.button_jokeTeller) Button button_JokeTeller;
    @BindView(R.id.instruction_TextView) TextView instruction;
    @BindView(R.id.container) RelativeLayout relativeLayout;
    @BindView(R.id.progressBar) LinearLayout linearLayout;
    @BindView(R.id.adView) AdView adView;
    //    Button button_JokeTeller;
//    RelativeLayout relativeLayout;
//    LinearLayout linearLayout;
//    AVLoadingIndicatorView avLoadingIndicatorView;
//    AdView adView;
    EndpointsAsyncTask endpointsAsyncTask;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showProgressbar(false);
        endpointsAsyncTask = new EndpointsAsyncTask(this);
        // Load ads
        AdRequest adRequestBanner = new AdRequest.Builder().build();
        adView.loadAd(adRequestBanner);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        AdRequest adRequestInterstitial = new AdRequest.Builder().build();

        button_JokeTeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterstitialAd.loadAd(adRequestInterstitial);
                showInterstitial();
            }
        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                showProgressbar(true);
                showJoke();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                showProgressbar(true);
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                Toast.makeText(getApplicationContext(), "Ad is opened!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void showJoke() {
        endpointsAsyncTask.execute(getString(R.string.keyword));
    }

    @Override
    public void processFinish(String result) {
        Intent intent = new Intent(this, JokeActivity
                .class)
                .putExtra(JOKE_TAG, result);
        showProgressbar(false);
        startActivity(intent);
    }

    public void showProgressbar(boolean a) {
        if (a == true) {
            relativeLayout.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
        }
    }
}
