package com.example.krrishdholakia.projectapollox2v10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.krrishdholakia.projectapollox2v10.App_Sign_In.ChooserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, ChooserActivity.class);
        startActivity(intent);

    }
}
