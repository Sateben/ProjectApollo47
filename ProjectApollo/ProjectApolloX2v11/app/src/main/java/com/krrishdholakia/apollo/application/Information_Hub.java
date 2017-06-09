package com.krrishdholakia.apollo.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.krrishdholakia.apollo.application.Check.Check_Initial_Data;
import com.krrishdholakia.apollo.application.Medical_Information.Medical_Information;
import com.krrishdholakia.apollo.projectapollox2v11.R;
import com.krrishdholakia.apollo.application.Review.Review;

/**
 * Created by krrishdholakia on 8/12/16.
 */
public class Information_Hub extends AppCompatActivity {

    CardView medical_profile_Card_Vw;
    CardView review_Card_Vw;
    CardView check_Card_Vw;
    private String uID = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_hub);

        medical_profile_Card_Vw = (CardView) findViewById(R.id.medical_profile_info_hub);
        review_Card_Vw = (CardView) findViewById(R.id.review_info_hub);
        check_Card_Vw = (CardView) findViewById(R.id.checkup_info_hub);

        Bundle bundle = getIntent().getExtras();

        uID = bundle.getString("uID");

        medical_profile_Card_Vw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(Information_Hub.this, Medical_Information.class);
                i.putExtra("uID", uID);
                startActivity(i);

            }
        });

        review_Card_Vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Information_Hub.this, Review.class);
                i.putExtra("uID", uID);
                startActivity(i);

            }
        });

        check_Card_Vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Information_Hub.this, Check_Initial_Data.class);
                i.putExtra("uID", uID);
                startActivity(i);

            }
        });






    }
}
