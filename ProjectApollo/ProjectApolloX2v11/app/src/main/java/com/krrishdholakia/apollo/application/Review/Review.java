package com.krrishdholakia.apollo.application.Review;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.krrishdholakia.apollo.application.Information_Hub;
import com.krrishdholakia.apollo.projectapollox2v11.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by krrishdholakia on 8/12/16.
 */
public class Review extends AppCompatActivity {

    private static final String TAG = "Review_Info";

    Button Yes_Btn;
    Button No_Btn;
    String Diagnosis;
    private String uID_test = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Yes_Btn = (Button) findViewById(R.id.yes_btn);
        No_Btn = (Button) findViewById(R.id.no_btn);

        Bundle bundle = getIntent().getExtras();

        uID_test = bundle.getString("uID");


        Yes_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Diagnosis = "1";
                final AlertDialog.Builder builder = new AlertDialog.Builder(Review.this);
                builder.setMessage(R.string.review_update_med_info);
                builder.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i(TAG,"KRIBRI_CHECK_1");
                        Intent Review_Add = new Intent(Review.this, Review_Final_Add.class);
                        Review_Add.putExtra("uID",uID_test);
                        Review_Add.putExtra("Diagnosis",Diagnosis);
                        Log.i(TAG,"KRIBRI_CHECK_2");
                        startActivity(Review_Add);
                    }
                });
                builder.setNegativeButton("NO",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(Review.this,Review_Final_Med_Profile_Push.class);
                        i.putExtra("uID",uID_test);
                        i.putExtra("Diagnosis",Diagnosis);
                        startActivity(i);
                    }
                });
                builder.create();
                builder.show();

            }
        });

        No_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Diagnosis = "0";
                final AlertDialog.Builder builder = new AlertDialog.Builder(Review.this);
                builder.setMessage(R.string.review_update_med_info);
                builder.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i(TAG,"KRIBRI_CHECK_1");
                        Intent i = new Intent(Review.this,Review_Final_Add.class);
                        i.putExtra("uID",uID_test);
                        i.putExtra("Diagnosis",Diagnosis);
                        Log.i(TAG,"KRIBRI_CHECK_2");
                        startActivity(i);
                    }
                });
                builder.setNegativeButton("NO",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(Review.this,Review_Final_Med_Profile_Push.class);
                        i.putExtra("uID",uID_test);
                        i.putExtra("Diagnosis",Diagnosis);
                        startActivity(i);
                    }
                });
                builder.create();
                builder.show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_medical_information_enter, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.info_bar_med_info:
            {
                Log.i(TAG,"TEST");

                InputStream inputStream = getResources().openRawResource(R.raw.review_info);

                InputStreamReader inputreader = new InputStreamReader(inputStream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line;
                StringBuilder text = new StringBuilder();

                try {
                    while (( line = buffreader.readLine()) != null) {
                        text.append(line);
                        text.append('\n');
                    }
                } catch (IOException e) {

                }
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(text.toString());
                builder.setNegativeButton("DISMISS",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                builder.create();
                builder.show();
                return true;
            }

            case R.id.app_icon_med_info:
            {
                Intent i = new Intent(this, Information_Hub.class);
                i.putExtra("uID",uID_test);
                startActivity(i);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
