package com.santossingh.jokeapp.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.santossingh.joke_androidlibrary.JokeActivity;
import com.santossingh.jokeapp.AsyncTask.AsyncResponse;
import com.santossingh.jokeapp.AsyncTask.EndpointsAsyncTask;
import com.santossingh.jokeapp.R;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AsyncResponse{

    private static final String JOKE_TAG = "joke";
    @BindView(R.id.avi)
    AVLoadingIndicatorView avLoadingIndicatorView;
    @BindView(R.id.button_jokeTeller)
    Button button_JokeTeller;
    @BindView(R.id.instruction_TextView)
    TextView instruction;
    @BindView(R.id.contents)
    LinearLayout contents;
    @BindView(R.id.progressBar)
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void showJoke(View view) {
        showProgressbar(true);
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
        endpointsAsyncTask.execute(getString(R.string.keyword));
    }

    @Override
    public void processFinish(String result) {
        Intent intent = new Intent(this, JokeActivity
                .class)
                .putExtra(JOKE_TAG, result);
        startActivity(intent);
    }

    public void showProgressbar(boolean a) {
        if (a == true) {
            contents.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.GONE);
            contents.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        showProgressbar(false);
    }
}
