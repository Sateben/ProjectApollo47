package com.krrishdholakia.apollo.application.Check;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.krrishdholakia.apollo.application.Medical_Information.Medical_Information;
import com.krrishdholakia.apollo.projectapollox2v11.R;

/**
 * Created by krrishdholakia on 6/12/16.
 */
public class Data_Check_Conclusion extends AppCompatActivity {

    int final_prob_hd;
    TextView textView;
    Toolbar mTbToolbar;

    private String uID = null;
    CardView review;
    CardView information_hub;
    int diagnosis = 0;

    private static final String TAG = "DATA CONCLUSION SCREEN";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusion);

        review = (CardView) findViewById(R.id.review_conclusion_Screen);

        information_hub = (CardView) findViewById(R.id.medical_profile_conclusion_screen);

        Bundle bundle = getIntent().getExtras();

        final_prob_hd = bundle.getInt("prob_HD");

        uID = bundle.getString("uID");

        textView = (TextView) findViewById(R.id.txt_vw_conclusion_prob_statement);

        if(final_prob_hd == 1)
        {
            textView.setText("HIGH RISK");
            diagnosis = 1;
        }
        else
        {
            textView.setText("LOW RISK");
            diagnosis = 0;
        }

        information_hub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Data_Check_Conclusion.this, Medical_Information.class);
                i.putExtra("uID",uID);
                startActivity(i);
            }
        });

    }
}
