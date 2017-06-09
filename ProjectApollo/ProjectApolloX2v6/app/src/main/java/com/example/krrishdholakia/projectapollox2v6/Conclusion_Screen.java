package com.example.krrishdholakia.projectapollox2v6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;

/**
 * Created by krrishdholakia on 6/12/16.
 */
public class Conclusion_Screen extends AppCompatActivity {

    int final_prob_hd;
    TextView textView;
    Toolbar mTbToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusion_screen);

        mTbToolbar = (Toolbar) findViewById(R.id.tb_toolbar);

        getSupportActionBar().setElevation(0);

        Bundle bundle = getIntent().getExtras();

        final_prob_hd = bundle.getInt("prob_HD");

        textView = (TextView) findViewById(R.id.txt_vw_conclusion_prob_statement);

        if(final_prob_hd == 1)
        {
            textView.setText("HIGH RISK");
        }
        else
        {
            textView.setText("LOW RISK");
        }

    }
}
