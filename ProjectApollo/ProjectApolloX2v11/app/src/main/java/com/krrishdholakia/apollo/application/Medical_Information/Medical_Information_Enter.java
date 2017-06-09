package com.krrishdholakia.apollo.application.Medical_Information;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.krrishdholakia.apollo.application.Information_Hub;
import com.krrishdholakia.apollo.projectapollox2v11.R;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by krrishdholakia on 8/12/16.
 */
public class Medical_Information_Enter extends AppCompatActivity {
    Double User_Age;
    String User_Sex;
    Double User_Resting_BP = 0.0;
    Double User_Cholestrol = 0.0;
    Double User_Max_HR= 0.0;
    String User_Thalas;
    String User_FBS;
    Double User_Oldpeak= 0.0;
    String User_CA;
    String User_CPT;
    String User_EIA;
    String User_Rest_EKG;
    String User_Slope;

    EditText Age_Txt;
    Spinner Sex_Txt;
    EditText Resting_Bp_Txt;
    EditText Cholestrol_Txt;
    EditText Max_HR_Txt;
    Spinner Thalas_Txt;
    Spinner CA_Txt;
    Spinner CPT_Txt;
    Spinner EIA_Txt;
    Spinner FBS_Txt;
    Spinner Rest_EKG_Txt;
    Spinner Slope_Txt;
    private String uID = null;

    private static final String TAG = "MedicalINFO_ENTER";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_information_enter);


        Bundle bundle = getIntent().getExtras();

        uID = bundle.getString("uID");

        Spinner dropdown = (Spinner) findViewById(R.id.spinner_sex);
        String[] items = new String[]{"-", "Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Spinner dropdown2 = (Spinner) findViewById(R.id.spinner_thal);
        String[] items2 = new String[]{"-","Three", "Six", "Seven"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);

        Spinner dropdown3 = (Spinner) findViewById(R.id.spinner_ca);
        String[] items3 = new String[]{"-","Zero", "One", "Two", "Three"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown3.setAdapter(adapter3);

        Spinner dropdown4 = (Spinner) findViewById(R.id.spinner_cpt);
        String[] items4 = new String[]{"-","L1", "L2", "L3", "L4"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items4);
        dropdown4.setAdapter(adapter4);

        Spinner dropdown5 = (Spinner) findViewById(R.id.spinner_eia);
        String[] items5 = new String[]{"-","Zero", "One"};
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items5);
        dropdown5.setAdapter(adapter5);

        Spinner dropdown6 = (Spinner) findViewById(R.id.spinner_slope);
        String[] items6 = new String[]{"-","One", "Two", "Three"};
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items6);
        dropdown6.setAdapter(adapter6);

        Spinner dropdown7 = (Spinner) findViewById(R.id.spinner_rest_ekg);
        String[] items7 = new String[]{"-","One", "Two", "Three"};
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items7);
        dropdown7.setAdapter(adapter7);


        EditText temp_oldpeak = (EditText) findViewById(R.id.oldpeak_Edt_Txt);

        temp_oldpeak.setText("0");
        Rest_EKG_Txt = (Spinner) findViewById(R.id.spinner_rest_ekg);

        Slope_Txt = (Spinner) findViewById(R.id.spinner_slope);

        EditText entered_Fast_BS = (EditText) findViewById(R.id.Fasting_BS_Edt_Txt);
        entered_Fast_BS.setText("0");
        Age_Txt = (EditText) findViewById(R.id.Age_Edt_Txt);
        Age_Txt.setText("0");
        Sex_Txt = (Spinner) findViewById(R.id.spinner_sex);

        Resting_Bp_Txt = (EditText) findViewById(R.id.rest_bp_Edt_Txt);
        Resting_Bp_Txt.setText("0");

        Cholestrol_Txt = (EditText) findViewById(R.id.cholesterol_Edt_Txt);
        Cholestrol_Txt.setText("0");

        Max_HR_Txt = (EditText) findViewById(R.id.max_HR_Edt_Txt);
        Max_HR_Txt.setText("0");

        Thalas_Txt = (Spinner) findViewById(R.id.spinner_thal);


        CA_Txt = (Spinner) findViewById(R.id.spinner_ca);


        CPT_Txt = (Spinner) findViewById(R.id.spinner_cpt);


        EIA_Txt = (Spinner) findViewById(R.id.spinner_eia);


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

            case R.id.done_bar_med_info: {

                if(Age_Txt.getText() != null && Sex_Txt.getSelectedItem() != null && Resting_Bp_Txt.getText() != null && Cholestrol_Txt.getText() != null && Max_HR_Txt.getText() != null && Thalas_Txt.getSelectedItem() != null)

                {
                    EditText temp_oldpeak = (EditText) findViewById(R.id.oldpeak_Edt_Txt);

                    ArrayList<String> required_medical_information = new ArrayList<String>();

                    User_Oldpeak = Double.parseDouble(temp_oldpeak.getText().toString());

                    if(temp_oldpeak != null)
                        required_medical_information.add("User_Oldpeak:"+User_Oldpeak);

                    Rest_EKG_Txt = (Spinner) findViewById(R.id.spinner_rest_ekg);

                    User_Rest_EKG = Rest_EKG_Txt.getSelectedItem().toString();
                    if(Rest_EKG_Txt != null)
                        required_medical_information.add("User_Rest_EKG:"+User_Rest_EKG);
                    Slope_Txt = (Spinner) findViewById(R.id.spinner_slope);

                    User_Slope = Slope_Txt.getSelectedItem().toString();
                    if(Slope_Txt != null)
                        required_medical_information.add("User_Slope:"+User_Slope);


                    EditText entered_Fast_BS = (EditText) findViewById(R.id.Fasting_BS_Edt_Txt);

                    if(entered_Fast_BS != null)
                    {
                        if (Double.parseDouble(entered_Fast_BS.getText().toString()) > 120) {
                            User_FBS = "True";
                        } else if (Double.parseDouble(entered_Fast_BS.getText().toString()) <= 120) {
                            User_FBS = "False";
                        }

                        required_medical_information.add("User_FBS:"+User_FBS);
                    }




                    Age_Txt = (EditText) findViewById(R.id.Age_Edt_Txt);
                    User_Age = Double.parseDouble(Age_Txt.getText().toString());

                    if(Age_Txt.getText() != null)
                        required_medical_information.add("User_Age:"+User_Age.toString());

                    Sex_Txt = (Spinner) findViewById(R.id.spinner_sex);

                    User_Sex = Sex_Txt.getSelectedItem().toString();

                    if(Sex_Txt.getSelectedItem() != null)
                        required_medical_information.add("User_Sex:"+User_Sex);

                    Resting_Bp_Txt = (EditText) findViewById(R.id.rest_bp_Edt_Txt);


                    User_Resting_BP = Double.parseDouble(Resting_Bp_Txt.getText().toString());

                    if(Resting_Bp_Txt.getText() != null)
                        required_medical_information.add("User_Resting_BP:"+User_Resting_BP.toString());

                    Cholestrol_Txt = (EditText) findViewById(R.id.cholesterol_Edt_Txt);

                    User_Cholestrol = Double.parseDouble(Cholestrol_Txt.getText().toString());
                    if(Cholestrol_Txt.getText() != null)
                        required_medical_information.add("User_Cholestrol:"+User_Cholestrol.toString());
                    Max_HR_Txt = (EditText) findViewById(R.id.max_HR_Edt_Txt);

                    User_Max_HR = Double.parseDouble(Max_HR_Txt.getText().toString());

                    if(Max_HR_Txt.getText() != null)
                        required_medical_information.add("User_Max_HR:"+User_Max_HR.toString());

                    Thalas_Txt = (Spinner) findViewById(R.id.spinner_thal);

                    User_Thalas = Thalas_Txt.getSelectedItem().toString();

                    if(Thalas_Txt.getSelectedItem() != null)
                        required_medical_information.add("User_Thalas:"+User_Thalas.toString());

                    CA_Txt = (Spinner) findViewById(R.id.spinner_ca);

                    User_CA = CA_Txt.getSelectedItem().toString();

                    if(CA_Txt.getSelectedItem() != null)
                        required_medical_information.add("User_CA:"+User_CA.toString());

                    CPT_Txt = (Spinner) findViewById(R.id.spinner_cpt);

                    User_CPT = CPT_Txt.getSelectedItem().toString();

                    if(CPT_Txt.getSelectedItem() != null)
                        required_medical_information.add("User_CPT:"+User_CPT.toString());
                    EIA_Txt = (Spinner) findViewById(R.id.spinner_eia);

                    User_EIA = EIA_Txt.getSelectedItem().toString();

                    if(EIA_Txt.getSelectedItem() != null)
                        required_medical_information.add("User_EIA:"+User_EIA.toString());

                    Log.i(TAG, "RUNNING: " + User_EIA);

                    String data_input = required_medical_information.get(0);

                    for(int count = 1; count < required_medical_information.size(); count++)
                    {
                        data_input = data_input + "," + required_medical_information.get(count);
                    }

                    try {
                        FileOutputStream fileout=openFileOutput(uID+".txt", MODE_PRIVATE);
                        OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                        outputWriter.write(data_input);
                        outputWriter.close();

                        //display file saved message
                        Toast.makeText(getBaseContext(), "Information saved successfully!",
                                Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(this, Information_Hub.class);
                        i.putExtra("uID",uID);

                        startActivity(i);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
                else
                {
                    InputStream inputStream = getResources().openRawResource(R.raw.necessary_medical_information);

                    InputStreamReader inputreader = new InputStreamReader(inputStream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    StringBuilder text = new StringBuilder();

                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(text.toString());
                    builder.setNegativeButton("DISMISS",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
                    builder.create();
                    builder.show();
                }




                break;
            }

            case R.id.info_bar_med_info:
            {
                Log.i(TAG,"TEST");

                InputStream inputStream = getResources().openRawResource(R.raw.dialogue_info);

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
                i.putExtra("uID",uID);
                startActivity(i);
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
