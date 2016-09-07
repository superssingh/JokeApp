package com.santossingh.jokeapp.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.stark.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by Stark on 05/09/2016.
 */

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    public AsyncResponse listener = null;

    public EndpointsAsyncTask(AsyncResponse listener) {
        this.listener = listener;
    }

    public EndpointsAsyncTask() {
        super();
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://joketellingapp-140720.appspot.com/_ah/api/");
            myApiService = builder.build();
        }
        context = params[0].first;
        String name = params[0].second;

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        listener.processFinish(result);
    }

}
