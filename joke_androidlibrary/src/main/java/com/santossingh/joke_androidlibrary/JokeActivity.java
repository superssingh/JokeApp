package com.santossingh.joke_androidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    public final String joke="joke";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Intent intent=getIntent();
        String data=intent.getStringExtra(joke);
        TextView textView=(TextView)findViewById(R.id.joke_text);
        textView.setText(data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
