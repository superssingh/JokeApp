package com.santossingh.jokeapp.paid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;

import com.santossingh.joke_androidlibrary.JokeActivity;
import com.santossingh.jokeapp.AsyncTask.AsyncResponse;
import com.santossingh.jokeapp.AsyncTask.EndpointsAsyncTask;
import com.santossingh.jokeapp.R;

public class MainActivity extends AppCompatActivity implements AsyncResponse{

    private static final String JOKE_TAG = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showJoke(View view) {
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
}
