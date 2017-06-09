package com.krrishdholakia.apollo.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.krrishdholakia.apollo.application.App_Sign_In.EmailPasswordActivity;
import com.krrishdholakia.apollo.projectapollox2v11.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        CardView start_Card = (CardView) findViewById(R.id.begin_Card_View);

        start_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EmailPasswordActivity.class);
                startActivity(i);
            }
        });
    }
}
