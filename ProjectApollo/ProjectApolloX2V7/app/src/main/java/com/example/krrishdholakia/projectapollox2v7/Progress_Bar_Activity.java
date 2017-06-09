package com.example.krrishdholakia.projectapollox2v7;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by krrishdholakia on 8/12/16.
 */
public class Progress_Bar_Activity extends AppCompatActivity{
    ProgressBar temp_bar;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_check);

        temp_bar = (ProgressBar) findViewById(R.id.progress_bar);

        new Thread(new Runnable() {
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus = mProgressStatus+10;

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            temp_bar.setProgress(mProgressStatus);
                        }
                    });
                }
            }
        }).start();

        temp_bar.setIndeterminate(true);

        temp_bar.setVisibility(View.INVISIBLE);

        if(mProgressStatus >= 50)
        {
            temp_bar.setVisibility(View.VISIBLE);
        }


    }
}
