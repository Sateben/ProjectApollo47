package com.example.krrishdholakia.projectapollox2v8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "#MYACTIVITY";
EditText age_edt_txt;
    EditText gender_edt_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button done_Btn = (Button) findViewById(R.id.done_btn);

        done_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age_edt_txt = (EditText) findViewById(R.id.age_editText);

                gender_edt_txt = (EditText) findViewById(R.id.gender_editText);

                String data_input = age_edt_txt.getText().toString() + "," + gender_edt_txt.getText().toString();

                try {
                    FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                    outputWriter.write(data_input);
                    outputWriter.close();

                    //display file saved message
                    Toast.makeText(getBaseContext(), "File saved successfully!",
                            Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                String temp_val = null;

                try {
                   // FileInputStream fileIn=openFileInput("mytextfile.txt");
                    FileInputStream fileIn=openFileInput("temp_val.txt");
                    InputStreamReader InputRead= new InputStreamReader(fileIn);

                    char[] inputBuffer= new char[100];
                    String s="";
                    int charRead;

                    while ((charRead=InputRead.read(inputBuffer))>0) {
                        // char to string conversion
                        String readstring=String.copyValueOf(inputBuffer,0,charRead);
                        s +=readstring;
                    }
                    InputRead.close();
                    gender_edt_txt.setText(s);


                } catch (Exception e) {

                    Log.i(TAG,"Test");
                    e.printStackTrace();
                }
            }
        });


    }
}
