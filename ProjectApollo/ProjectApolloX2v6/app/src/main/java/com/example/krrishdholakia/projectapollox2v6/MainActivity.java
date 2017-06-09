package com.example.krrishdholakia.projectapollox2v6;


import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.krrishdholakia.projectapollox2v6.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.math.BigDecimal;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "tESTING#MainActivity";

    Dialog info_dialog;
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

    private ProgressBar spinner;
    private int progress_count=0;

    ProgressBar temp_bar;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temp_bar = (ProgressBar) findViewById(R.id.progress_bar);
        temp_bar.setVisibility(View.INVISIBLE);




        Spinner dropdown = (Spinner) findViewById(R.id.spinner_sex_screen_all);
        String[] items = new String[]{"-", "Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Spinner dropdown2 = (Spinner) findViewById(R.id.spinner_thal_screen_all);
        String[] items2 = new String[]{"-","Three", "Six", "Seven"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);

        Spinner dropdown3 = (Spinner) findViewById(R.id.spinner_ca_screen_all);
        String[] items3 = new String[]{"-","Zero", "One", "Two", "Three"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown3.setAdapter(adapter3);

        Spinner dropdown4 = (Spinner) findViewById(R.id.spinner_cpt_screen_all);
        String[] items4 = new String[]{"-","L1", "L2", "L3", "L4"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items4);
        dropdown4.setAdapter(adapter4);

        Spinner dropdown5 = (Spinner) findViewById(R.id.spinner_eia_screen_all);
        String[] items5 = new String[]{"-","Zero", "One"};
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items5);
        dropdown5.setAdapter(adapter5);

        Spinner dropdown6 = (Spinner) findViewById(R.id.spinner_slope_screen_all);
        String[] items6 = new String[]{"-","One", "Two", "Three"};
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items6);
        dropdown6.setAdapter(adapter6);

        Spinner dropdown7 = (Spinner) findViewById(R.id.spinner_rest_ekg_screen_all);
        String[] items7 = new String[]{"-","One", "Two", "Three"};
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items7);
        dropdown7.setAdapter(adapter7);


        EditText temp_oldpeak = (EditText) findViewById(R.id.oldpeak_Edt_Txt_screen_all);

        temp_oldpeak.setText("0");
        Rest_EKG_Txt = (Spinner) findViewById(R.id.spinner_rest_ekg_screen_all);

        Slope_Txt = (Spinner) findViewById(R.id.spinner_slope);

        EditText entered_Fast_BS = (EditText) findViewById(R.id.Fasting_BS_Edt_Txt_screen_all);
        entered_Fast_BS.setText("0");
        Age_Txt = (EditText) findViewById(R.id.Age_Edt_Txt_screen_all);
        Age_Txt.setText("0");
        Sex_Txt = (Spinner) findViewById(R.id.spinner_sex_screen_all);

        Resting_Bp_Txt = (EditText) findViewById(R.id.rest_bp_Edt_Txt_screen_all);
        Resting_Bp_Txt.setText("0");

        Cholestrol_Txt = (EditText) findViewById(R.id.cholesterol_Edt_Txt_screen_all);
        Cholestrol_Txt.setText("0");

        Max_HR_Txt = (EditText) findViewById(R.id.max_HR_Edt_Txt_screen_all);
        Max_HR_Txt.setText("0");

        Thalas_Txt = (Spinner) findViewById(R.id.spinner_thal_screen_all);


        CA_Txt = (Spinner) findViewById(R.id.spinner_ca_screen_all);


        CPT_Txt = (Spinner) findViewById(R.id.spinner_cpt_screen_all);


        EIA_Txt = (Spinner) findViewById(R.id.spinner_eia_screen_all);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.activity_bar_apollo, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.done_bar: {

                temp_bar.setIndeterminate(true);

                temp_bar.setVisibility(View.VISIBLE);


                Log.i(TAG,"Running...");

                EditText temp_oldpeak = (EditText) findViewById(R.id.oldpeak_Edt_Txt_screen_all);

                User_Oldpeak = Double.parseDouble(temp_oldpeak.getText().toString());

                Rest_EKG_Txt = (Spinner) findViewById(R.id.spinner_rest_ekg_screen_all);

                User_Rest_EKG = Rest_EKG_Txt.getSelectedItem().toString();

                Slope_Txt = (Spinner) findViewById(R.id.spinner_slope_screen_all);

                User_Slope = Slope_Txt.getSelectedItem().toString();


                EditText entered_Fast_BS = (EditText) findViewById(R.id.Fasting_BS_Edt_Txt_screen_all);

                if (Double.parseDouble(entered_Fast_BS.getText().toString()) > 120) {
                    User_FBS = "True";
                } else if (Double.parseDouble(entered_Fast_BS.getText().toString()) <= 120) {
                    User_FBS = "False";
                }

                Age_Txt = (EditText) findViewById(R.id.Age_Edt_Txt_screen_all);
                User_Age = Double.parseDouble(Age_Txt.getText().toString());

                Sex_Txt = (Spinner) findViewById(R.id.spinner_sex_screen_all);

                User_Sex = Sex_Txt.getSelectedItem().toString();

                Resting_Bp_Txt = (EditText) findViewById(R.id.rest_bp_Edt_Txt_screen_all);


                User_Resting_BP = Double.parseDouble(Resting_Bp_Txt.getText().toString());

                Cholestrol_Txt = (EditText) findViewById(R.id.cholesterol_Edt_Txt_screen_all);

                User_Cholestrol = Double.parseDouble(Cholestrol_Txt.getText().toString());

                Max_HR_Txt = (EditText) findViewById(R.id.max_HR_Edt_Txt_screen_all);

                User_Max_HR = Double.parseDouble(Max_HR_Txt.getText().toString());

                Thalas_Txt = (Spinner) findViewById(R.id.spinner_thal_screen_all);

                User_Thalas = Thalas_Txt.getSelectedItem().toString();

                CA_Txt = (Spinner) findViewById(R.id.spinner_ca_screen_all);

                User_CA = CA_Txt.getSelectedItem().toString();

                CPT_Txt = (Spinner) findViewById(R.id.spinner_cpt_screen_all);

                User_CPT = CPT_Txt.getSelectedItem().toString();

                EIA_Txt = (Spinner) findViewById(R.id.spinner_eia_screen_all);

                User_EIA = EIA_Txt.getSelectedItem().toString();

                Log.i(TAG,"RUNNING: "+User_EIA);

                DatabaseReference db_Ref = FirebaseDatabase.getInstance().getReference().child("UserTrials");
                db_Ref.child("Probability").push().setValue("December");

                Log.i(TAG, "updated_to_firebase");

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Probability");
                final List<Double> CA_HD_prob_of_occ = new LinkedList<Double>();
                final List<Double> CPT_HD_prob_of_occ= new LinkedList<Double>();
                final List<Double> EIA_HD_prob_of_occ= new LinkedList<Double>();
                final List<Double> FBS_HD_prob_of_occ = new LinkedList<Double>();
                final List<Double> R_EKG_HD_prob_of_occ = new LinkedList<Double>();
                final List<Double> SLOPE_HD_prob_of_occ = new LinkedList<Double>();


                final List<Double> CA_NO_HD_prob_of_occ = new LinkedList<Double>();
                final List<Double> CPT_NO_HD_prob_of_occ= new LinkedList<Double>();
                final List<Double> EIA_NO_HD_prob_of_occ= new LinkedList<Double>();
                final List<Double> FBS_NO_HD_prob_of_occ = new LinkedList<Double>();
                final List<Double> R_EKG_NO_HD_prob_of_occ = new LinkedList<Double>();
                final List<Double> SLOPE_NO_HD_prob_of_occ = new LinkedList<Double>();

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        double CA_HD_prob = 0.0;
                        double CPT_prob = 0.0;
                        double EIA_prob = 0.0;
                        double FBS_prob = 0.0;
                        double R_EKG_prob = 0.0;
                        double Slope_prob = 0.0;
                        double Thal_prob = 0.0;
                        double Sex_prob = 0.0;

                        Log.i(TAG,"ON DATA CHANGE WORKS");

                        if (User_CA == "-") {
                            CA_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child("Zero").child("probability_of_occ").getValue().toString()));
                            CA_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child("One").child("probability_of_occ").getValue().toString()));
                            CA_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child("Two").child("probability_of_occ").getValue().toString()));
                            CA_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child("Three").child("probability_of_occ").getValue().toString()));
                            CA_HD_prob = simple_probability(CA_HD_prob_of_occ) / Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child("Zero").child("total_number").getValue().toString());//total number is constant across all

                        } else {
                            CA_HD_prob = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child(User_CA).child("probability_of_occ").getValue().toString())/Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child(User_CA).child("total_number").getValue().toString());
                        }

                        if (User_CPT == "-") {
                            CPT_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L1").child("probability_of_occ").getValue().toString()));
                            CPT_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L2").child("probability_of_occ").getValue().toString()));
                            CPT_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L3").child("probability_of_occ").getValue().toString()));
                            CPT_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L4").child("probability_of_occ").getValue().toString()));
                        }
                        else {
                            CPT_prob = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CPT_HD").child(User_CPT).child("probability_of_occ").getValue().toString()) / Double.parseDouble(dataSnapshot.child("HD_Bin").child("CPT_HD").child(User_CPT).child("total_number").getValue().toString());
                        }

                            if (User_EIA == "-") {
                            EIA_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("Exercise_Induced_Angina_HD").child("Zero").child("probability_of_occ").getValue().toString()));
                            EIA_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("Exercise_Induced_Angina_HD").child("One").child("probability_of_occ").getValue().toString()));
                                EIA_prob = simple_probability(EIA_HD_prob_of_occ)/Double.parseDouble(dataSnapshot.child("HD_Bin").child("Exercise_Induced_Angina_HD").child("Zero").child("probability_of_occ").getValue().toString());

                            }
                        else {
                                EIA_prob =  Double.parseDouble(dataSnapshot.child("HD_Bin").child("Exercise_Induced_Angina_HD").child(User_EIA).child("probability_of_occ").getValue().toString()) /Double.parseDouble(dataSnapshot.child("HD_Bin").child("Exercise_Induced_Angina_HD").child(User_EIA).child("probability_of_occ").getValue().toString());

                            }

                        if (User_FBS == "-") {
                            FBS_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("Fasting_Blood_Sugar_HD").child("False").child("probability_of_occ").getValue().toString()));
                            FBS_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("Fasting_Blood_Sugar_HD").child("True").child("probability_of_occ").getValue().toString()));
                            FBS_prob = simple_probability(FBS_HD_prob_of_occ) / Double.parseDouble(dataSnapshot.child("HD_Bin").child("Fasting_Blood_Sugar_HD").child("False").child("probability_of_occ").getValue().toString());

                        }
                        else {
                            FBS_prob = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Fasting_Blood_Sugar_HD").child(User_FBS).child("probability_of_occ").getValue().toString())/ Double.parseDouble(dataSnapshot.child("HD_Bin").child("Fasting_Blood_Sugar_HD").child("False").child("probability_of_occ").getValue().toString());

                        }


                        if (User_Rest_EKG == "-") {
                            R_EKG_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("Zero").child("probability_of_occ").getValue().toString()));
                            R_EKG_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("One").child("probability_of_occ").getValue().toString()));
                            R_EKG_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("Two").child("probability_of_occ").getValue().toString()));
                            R_EKG_prob = simple_probability(R_EKG_HD_prob_of_occ) / Double.parseDouble(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("Zero").child("probability_of_occ").getValue().toString());

                        }
                        else
                        {
                            R_EKG_prob = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child(User_Rest_EKG).child("probability_of_occ").getValue().toString())/ Double.parseDouble(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("Zero").child("probability_of_occ").getValue().toString());

                        }

                        if (User_Slope == "-") {
                            SLOPE_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("Slope_HD").child("One").child("probability_of_occ").getValue().toString()));
                            SLOPE_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("Slope_HD").child("Two").child("probability_of_occ").getValue().toString()));
                            SLOPE_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("HD_Bin").child("Slope_HD").child("Three").child("probability_of_occ").getValue().toString()));
                            Slope_prob = simple_probability(SLOPE_HD_prob_of_occ) / Double.parseDouble(dataSnapshot.child("HD_Bin").child("Slope_HD").child("One").child("probability_of_occ").getValue().toString());

                        }
                        else
                        {
                            Slope_prob = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Slope_HD").child(User_Slope).child("probability_of_occ").getValue().toString())/Double.parseDouble(dataSnapshot.child("HD_Bin").child("Slope_HD").child("One").child("probability_of_occ").getValue().toString());

                        }


                        Sex_prob = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Sex_HD").child(User_Sex).child("probability_of_occ").getValue().toString()) / Double.parseDouble(dataSnapshot.child("HD_Bin").child("Sex_HD").child(User_Sex).child("total_number").getValue().toString());

                        Thal_prob = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Thal_HD").child(User_Thalas).child("probability_of_occ").getValue().toString()) / Double.parseDouble(dataSnapshot.child("HD_Bin").child("Thal_HD").child(User_Thalas).child("total_number").getValue().toString());

                        BigDecimal HD_BIN_simple_probability = BigDecimal.valueOf(CA_HD_prob * CPT_prob * EIA_prob * FBS_prob * R_EKG_prob * Slope_prob * Sex_prob * Thal_prob);

                        double age_mean = 0.0;
                        double age_std_deviation_biased = 0.0;
                        double age_std_deviation_unbiased = 0.0;
                        double resting_bp_level_mean = 0.0;
                        double resting_bp_level_std_deviation_biased = 0.0;
                        double resting_bp_level_std_deviation_unbiased = 0.0;
                        double cholestoral_mean = 0.0;
                        double cholestoral_std_deviation_biased = 0.0;
                        double cholestoral_std_deviation_unbiased = 0.0;
                        double max_hr_mean = 0.0;
                        double max_hr_std_deviation_biased = 0.0;
                        double max_hr_std_deviation_unbiased = 0.0;
                        double oldpeak_mean = 0.0;
                        double oldpeak_std_deviation_biased = 0.0;
                        double oldpeak_std_deviation_unbiased = 0.0;

                        age_mean = Double.parseDouble(dataSnapshot.child("HD_ND").child("Age_HD_ND").child("sum_of_Var").getValue().toString()) / Double.parseDouble(dataSnapshot.child("HD_ND").child("Age_HD_ND").child("number_of_Var").getValue().toString());

                        age_std_deviation_biased = (Double.parseDouble(dataSnapshot.child("HD_ND").child("Age_HD_ND").child("sum_of_Var_squared").getValue().toString()) / Double.parseDouble(dataSnapshot.child("HD_ND").child("Age_HD_ND").child("number_of_Var").getValue().toString())) - Math.pow(age_mean, 2);

                        age_std_deviation_unbiased = (Double.parseDouble(dataSnapshot.child("HD_ND").child("Age_HD_ND").child("number_of_Var").getValue().toString()) / (Double.parseDouble(dataSnapshot.child("HD_ND").child("Age_HD_ND").child("number_of_Var").getValue().toString()) - 1)) * age_std_deviation_biased;

                        resting_bp_level_mean = Double.parseDouble(dataSnapshot.child("HD_ND").child("Rest_BP_Level_HD_ND").child("sum_of_Var").getValue().toString()) / Double.parseDouble(dataSnapshot.child("HD_ND").child("Rest_BP_Level_HD_ND").child("number_of_Var").getValue().toString());
                        resting_bp_level_std_deviation_biased = (Double.parseDouble(dataSnapshot.child("HD_ND").child("Rest_BP_Level_HD_ND").child("sum_of_Var_squared").getValue().toString()) / Double.parseDouble(dataSnapshot.child("HD_ND").child("Rest_BP_Level_HD_ND").child("number_of_Var").getValue().toString())) - Math.pow(resting_bp_level_mean, 2);

                        resting_bp_level_std_deviation_unbiased = (Double.parseDouble(dataSnapshot.child("HD_ND").child("Age_HD_ND").child("number_of_Var").getValue().toString()) / (Double.parseDouble(dataSnapshot.child("HD_ND").child("Rest_BP_Level_HD_ND").child("number_of_Var").getValue().toString()) - 1)) * resting_bp_level_std_deviation_biased;

                        cholestoral_mean = Double.parseDouble(dataSnapshot.child("HD_ND").child("Cholestoral_HD_ND").child("sum_of_Var").getValue().toString()) / Double.parseDouble(dataSnapshot.child("HD_ND").child("Cholestoral_HD_ND").child("number_of_Var").getValue().toString());
                        cholestoral_std_deviation_biased = (Double.parseDouble(dataSnapshot.child("HD_ND").child("Cholestoral_HD_ND").child("sum_of_Var_squared").getValue().toString()) / Double.parseDouble(dataSnapshot.child("HD_ND").child("Cholestoral_HD_ND").child("number_of_Var").getValue().toString())) - Math.pow(cholestoral_mean, 2);

                        cholestoral_std_deviation_unbiased = (Double.parseDouble(dataSnapshot.child("HD_ND").child("Cholestoral_HD_ND").child("number_of_Var").getValue().toString()) / (Double.parseDouble(dataSnapshot.child("HD_ND").child("Cholestoral_HD_ND").child("number_of_Var").getValue().toString()) - 1)) * cholestoral_std_deviation_biased;

                        max_hr_mean = Double.parseDouble(dataSnapshot.child("HD_ND").child("Max_HeartRate_HD_ND").child("sum_of_Var").getValue().toString()) / Double.parseDouble(dataSnapshot.child("HD_ND").child("Max_HeartRate_HD_ND").child("number_of_Var").getValue().toString());
                        max_hr_std_deviation_biased = (Double.parseDouble(dataSnapshot.child("HD_ND").child("Max_HeartRate_HD_ND").child("sum_of_Var_squared").getValue().toString()) / Double.parseDouble(dataSnapshot.child("HD_ND").child("Max_HeartRate_HD_ND").child("number_of_Var").getValue().toString())) - Math.pow(max_hr_mean, 2);

                        max_hr_std_deviation_unbiased = (Double.parseDouble(dataSnapshot.child("HD_ND").child("Max_HeartRate_HD_ND").child("number_of_Var").getValue().toString()) / (Double.parseDouble(dataSnapshot.child("HD_ND").child("Cholestoral_HD_ND").child("number_of_Var").getValue().toString()) - 1)) * max_hr_std_deviation_biased;

                        oldpeak_mean = Double.parseDouble(dataSnapshot.child("HD_ND").child("Oldpeak_HD_ND").child("sum_of_Var").getValue().toString()) / Double.parseDouble(dataSnapshot.child("HD_ND").child("Oldpeak_HD_ND").child("number_of_Var").getValue().toString());
                        oldpeak_std_deviation_biased = (Double.parseDouble(dataSnapshot.child("HD_ND").child("Oldpeak_HD_ND").child("sum_of_Var_squared").getValue().toString()) / Double.parseDouble(dataSnapshot.child("HD_ND").child("Oldpeak_HD_ND").child("number_of_Var").getValue().toString())) - Math.pow(oldpeak_mean, 2);

                        oldpeak_std_deviation_unbiased = (Double.parseDouble(dataSnapshot.child("HD_ND").child("Oldpeak_HD_ND").child("number_of_Var").getValue().toString()) / (Double.parseDouble(dataSnapshot.child("HD_ND").child("Oldpeak_HD_ND").child("number_of_Var").getValue().toString()) - 1)) * oldpeak_std_deviation_biased;

                        double data_prob_HD = Double.parseDouble(dataSnapshot.child("HD_Probability").child("probability_of_occ").getValue().toString()) / Double.parseDouble(dataSnapshot.child("HD_Probability").child("total_number").getValue().toString());

                        BigDecimal probability_of_HD;

                        if(User_Oldpeak == 0)
                        {
                            probability_of_HD = BigDecimal.valueOf(Normal(age_std_deviation_unbiased, User_Age, age_mean) * (Normal(resting_bp_level_std_deviation_unbiased, User_Resting_BP, resting_bp_level_mean)) * Normal(cholestoral_std_deviation_unbiased, User_Cholestrol, cholestoral_mean) * (Normal(max_hr_std_deviation_unbiased, User_Max_HR, max_hr_mean))*0.5).multiply(HD_BIN_simple_probability);

                        }
                        else
                        {
                            probability_of_HD = BigDecimal.valueOf(Normal(age_std_deviation_unbiased, User_Age, age_mean) * (Normal(resting_bp_level_std_deviation_unbiased, User_Resting_BP, resting_bp_level_mean)) * Normal(cholestoral_std_deviation_unbiased, User_Cholestrol, cholestoral_mean) * (Normal(max_hr_std_deviation_unbiased, User_Max_HR, max_hr_mean)) * Normal(oldpeak_std_deviation_unbiased,User_Oldpeak,oldpeak_mean)).multiply(HD_BIN_simple_probability);
                        }

                        BigDecimal final_prob_hd = probability_of_HD.multiply(BigDecimal.valueOf(data_prob_HD));
                        updateProgress();
                        Log.i(TAG, "MYTRIALS " + final_prob_hd);

                        double CA_NO_HD_prob = 0.0;
                        double CPT_NO_prob = 0.0;
                        double EIA_NO_prob = 0.0;
                        double FBS_NO_prob = 0.0;
                        double R_EKG_NO_prob = 0.0;
                        double Slope_NO_prob = 0.0;
                        double Thal_NO_prob = 0.0;
                        double Sex_NO_prob = 0.0;


                        if(User_CA == "-") {
                            CA_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Zero").child("probability_of_occ").getValue().toString()));
                            CA_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("One").child("probability_of_occ").getValue().toString()));
                            CA_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Two").child("probability_of_occ").getValue().toString()));
                            CA_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Three").child("probability_of_occ").getValue().toString()));
                            CA_NO_HD_prob = simple_probability(CA_NO_HD_prob_of_occ) / Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Zero").child("total_number").getValue().toString());//total number is constant across all

                        }
                        else
                        {
                            CA_NO_HD_prob =  Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child(User_CA).child("total_number").getValue().toString())/Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Zero").child("total_number").getValue().toString());//total number is constant across all

                        }

                        if(User_CPT == "-") {
                            CPT_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L1").child("probability_of_occ").getValue().toString()));
                            CPT_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L2").child("probability_of_occ").getValue().toString()));
                            CPT_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L3").child("probability_of_occ").getValue().toString()));
                            CPT_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L4").child("probability_of_occ").getValue().toString()));
                            CPT_NO_prob = simple_probability(CPT_NO_HD_prob_of_occ) / Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L1").child("probability_of_occ").getValue().toString());
                        }
                        else
                        {
                            CPT_NO_prob =  Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child(User_CPT).child("probability_of_occ").getValue().toString())/Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L1").child("probability_of_occ").getValue().toString());

                        }

                        if(User_EIA == "-") {
                            EIA_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Exercise_Induced_Angina_NO_HD").child("Zero").child("probability_of_occ").getValue().toString()));
                            EIA_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Exercise_Induced_Angina_NO_HD").child("One").child("probability_of_occ").getValue().toString()));
                            EIA_NO_prob = simple_probability(EIA_NO_HD_prob_of_occ) / Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Exercise_Induced_Angina_NO_HD").child("Zero").child("probability_of_occ").getValue().toString());
                        }
                        else
                        {
                            EIA_NO_prob = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Exercise_Induced_Angina_NO_HD").child(User_EIA).child("probability_of_occ").getValue().toString())/ Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Exercise_Induced_Angina_NO_HD").child("Zero").child("probability_of_occ").getValue().toString());

                        }

                        if(User_FBS == "-") {
                            FBS_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Fasting_Blood_Sugar_NO_HD").child("False").child("probability_of_occ").getValue().toString()));
                            FBS_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Fasting_Blood_Sugar_NO_HD").child("True").child("probability_of_occ").getValue().toString()));
                            FBS_NO_prob = simple_probability(FBS_NO_HD_prob_of_occ) / Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Fasting_Blood_Sugar_NO_HD").child("False").child("probability_of_occ").getValue().toString());
                        }
                        else
                        {
                            FBS_NO_prob = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Fasting_Blood_Sugar_NO_HD").child(User_FBS).child("probability_of_occ").getValue().toString())/Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Fasting_Blood_Sugar_NO_HD").child("False").child("probability_of_occ").getValue().toString());

                        }

                        if(User_Rest_EKG == "-") {
                            R_EKG_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("Zero").child("probability_of_occ").getValue().toString()));
                            R_EKG_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("One").child("probability_of_occ").getValue().toString()));
                            R_EKG_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("Two").child("probability_of_occ").getValue().toString()));
                            R_EKG_NO_prob = simple_probability(R_EKG_NO_HD_prob_of_occ) / Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("Zero").child("probability_of_occ").getValue().toString());
                        }
                        else
                        {
                            R_EKG_NO_prob = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child(User_Rest_EKG).child("probability_of_occ").getValue().toString())/ Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("Zero").child("probability_of_occ").getValue().toString());

                        }

                        if(User_Slope == "-") {
                            SLOPE_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("One").child("probability_of_occ").getValue().toString()));
                            SLOPE_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("Two").child("probability_of_occ").getValue().toString()));
                            SLOPE_NO_HD_prob_of_occ.add(Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("Three").child("probability_of_occ").getValue().toString()));
                            Slope_NO_prob = simple_probability(SLOPE_NO_HD_prob_of_occ) / Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("One").child("probability_of_occ").getValue().toString());

                        }

                        else {
                            Slope_NO_prob = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child(User_Slope).child("probability_of_occ").getValue().toString())/ Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("One").child("probability_of_occ").getValue().toString());

                        }

                        Sex_NO_prob = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Sex_NO_HD").child(User_Sex).child("probability_of_occ").getValue().toString()) / Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Sex_NO_HD").child(User_Sex).child("total_number").getValue().toString());

                        Thal_NO_prob = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Thal_NO_HD").child(User_Thalas).child("probability_of_occ").getValue().toString()) / Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Thal_NO_HD").child(User_Thalas).child("total_number").getValue().toString());

                        BigDecimal NO_HD_BIN_simple_prob = BigDecimal.valueOf(CA_HD_prob * CPT_NO_prob * EIA_NO_prob * FBS_NO_prob * R_EKG_NO_prob * Slope_NO_prob * Sex_NO_prob * Thal_NO_prob);

                        double age_NO_mean = 0.0;
                        double age_std_deviation_biased_NO = 0.0;
                        double age_std_deviation_unbiased_NO = 0.0;
                        double resting_bp_level_mean_NO = 0.0;
                        double resting_bp_level_std_deviation_biased_NO = 0.0;
                        double resting_bp_level_std_deviation_unbiased_NO = 0.0;
                        double cholestoral_mean_NO = 0.0;
                        double cholestoral_std_deviation_biased_NO = 0.0;
                        double cholestoral_std_deviation_unbiased_NO = 0.0;
                        double max_hr_mean_NO = 0.0;
                        double max_hr_std_deviation_biased_NO = 0.0;
                        double max_hr_std_deviation_unbiased_NO = 0.0;
                        double oldpeak_mean_NO = 0.0;
                        double oldpeak_std_deviation_biased_NO = 0.0;
                        double oldpeak_std_deviation_unbiased_NO = 0.0;

                        age_NO_mean = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Age_NO_HD_ND").child("sum_of_Var").getValue().toString()) / Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Age_NO_HD_ND").child("number_of_Var").getValue().toString());

                        age_std_deviation_biased_NO = (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Age_NO_HD_ND").child("sum_of_Var_squared").getValue().toString()) / Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Age_NO_HD_ND").child("number_of_Var").getValue().toString())) - Math.pow(age_NO_mean, 2);

                        age_std_deviation_unbiased_NO = (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Age_NO_HD_ND").child("number_of_Var").getValue().toString()) / (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Age_NO_HD_ND").child("number_of_Var").getValue().toString()) - 1)) * age_std_deviation_biased_NO;

                        resting_bp_level_mean_NO = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Rest_BP_Level_NO_HD_ND").child("sum_of_Var").getValue().toString()) / Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Rest_BP_Level_NO_HD_ND").child("number_of_Var").getValue().toString());
                        resting_bp_level_std_deviation_biased_NO = (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Rest_BP_Level_NO_HD_ND").child("sum_of_Var_squared").getValue().toString()) / Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Rest_BP_Level_NO_HD_ND").child("number_of_Var").getValue().toString())) - Math.pow(resting_bp_level_mean_NO, 2);

                        resting_bp_level_std_deviation_unbiased_NO = (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Rest_BP_Level_NO_HD_ND").child("number_of_Var").getValue().toString()) / (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Rest_BP_Level_NO_HD_ND").child("number_of_Var").getValue().toString()) - 1)) * resting_bp_level_std_deviation_biased_NO;

                        cholestoral_mean_NO = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Cholestoral_NO_HD").child("sum_of_Var").getValue().toString()) / Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Cholestoral_NO_HD").child("number_of_Var").getValue().toString());
                        cholestoral_std_deviation_biased_NO = (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Cholestoral_NO_HD").child("sum_of_Var_squared").getValue().toString()) / Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Cholestoral_NO_HD").child("number_of_Var").getValue().toString())) - Math.pow(cholestoral_mean_NO, 2);

                        cholestoral_std_deviation_unbiased_NO = (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Cholestoral_NO_HD").child("number_of_Var").getValue().toString()) / (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Cholestoral_NO_HD").child("number_of_Var").getValue().toString()) - 1)) * cholestoral_std_deviation_biased_NO;

                        max_hr_mean_NO = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Max_HeartRate_NO_HD_ND").child("sum_of_Var").getValue().toString()) / Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Max_HeartRate_NO_HD_ND").child("number_of_Var").getValue().toString());
                        max_hr_std_deviation_biased = (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Max_HeartRate_NO_HD_ND").child("sum_of_Var_squared").getValue().toString()) / Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Max_HeartRate_NO_HD_ND").child("number_of_Var").getValue().toString())) - Math.pow(max_hr_mean_NO, 2);

                        max_hr_std_deviation_unbiased_NO = (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Max_HeartRate_NO_HD_ND").child("number_of_Var").getValue().toString()) / (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Cholestoral_NO_HD").child("number_of_Var").getValue().toString()) - 1)) * max_hr_std_deviation_biased;

                        oldpeak_mean_NO = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Oldpeak_NO_HD_ND").child("sum_of_Var").getValue().toString()) / Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Oldpeak_NO_HD_ND").child("number_of_Var").getValue().toString());
                        oldpeak_std_deviation_biased_NO = (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Oldpeak_NO_HD_ND").child("sum_of_Var_squared").getValue().toString()) / Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Oldpeak_NO_HD_ND").child("number_of_Var").getValue().toString())) - Math.pow(oldpeak_mean_NO, 2);

                        oldpeak_std_deviation_unbiased_NO = (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Oldpeak_NO_HD_ND").child("number_of_Var").getValue().toString()) / (Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Oldpeak_NO_HD_ND").child("number_of_Var").getValue().toString()) - 1)) * oldpeak_std_deviation_biased_NO;

                        double data_prob_NO_HD = Double.parseDouble(dataSnapshot.child("NO_HD_Probability").child("probability_of_occ").getValue().toString()) / Double.parseDouble(dataSnapshot.child("NO_HD_Probability").child("total_number").getValue().toString());
                        //BigDecimal temp_value_oldpeak_Av = BigDecimal.valueOf(0.5);
                        //  BigDecimal true_data_prob_NO_HD = BigDecimal.valueOf(data_prob_HD);

                        BigDecimal probability_of_NO_HD;

                        if(User_Oldpeak == 0)
                        {
                            probability_of_NO_HD = BigDecimal.valueOf(Normal(age_std_deviation_unbiased_NO, User_Age, age_NO_mean) * (Normal(resting_bp_level_std_deviation_unbiased_NO, User_Resting_BP, resting_bp_level_mean_NO)) * Normal(cholestoral_std_deviation_unbiased_NO, User_Cholestrol, cholestoral_mean_NO) * (Normal(max_hr_std_deviation_unbiased_NO, User_Max_HR, max_hr_mean_NO))*0.5).multiply(NO_HD_BIN_simple_prob);

                        }
                        else
                        {
                            probability_of_NO_HD = BigDecimal.valueOf(Normal(age_std_deviation_unbiased_NO, User_Age, age_NO_mean) * (Normal(resting_bp_level_std_deviation_unbiased_NO, User_Resting_BP, resting_bp_level_mean_NO)) * Normal(cholestoral_std_deviation_unbiased_NO, User_Cholestrol, cholestoral_mean_NO) * (Normal(max_hr_std_deviation_unbiased_NO, User_Max_HR, max_hr_mean_NO))*Normal(oldpeak_std_deviation_unbiased_NO, User_Oldpeak, oldpeak_mean_NO)).multiply(NO_HD_BIN_simple_prob);

                        }
                        BigDecimal final_prob_NO_HD = probability_of_NO_HD.multiply(BigDecimal.valueOf(data_prob_NO_HD));
                        updateProgress();
                        Log.i(TAG, "TRIALLERS: " + final_prob_NO_HD);

                        int hd = 0;

                        if (final_prob_hd.compareTo(final_prob_NO_HD) == 1) {
                            Log.i(TAG, "Probability of disease exists: " + final_prob_hd);
                            hd = 1;
                        } else {
                            Log.i(TAG, "YAY YOU LUCK!");
                            hd = 0;
                        }


                        Intent intent = new Intent(MainActivity.this,Conclusion_Screen.class);
                        intent.putExtra("prob_HD", hd);
                        startActivity(intent);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                break;
            }

            case R.id.info_bar:
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
        }
        return super.onOptionsItemSelected(item);
    }




    public Double simple_probability(List<Double> tempList)
    {
        double prob_of_occ=0;
        for(int count = 1; count < tempList.size(); count++)
        {
            if(tempList.get(count) > prob_of_occ)
            {
                prob_of_occ = tempList.get(count);
            }
        }

        return prob_of_occ;
    }

    public double Normal(double sigma_squared, double x, double mean ){
           /* BigDecimal base = BigDecimal.valueOf(1/Math.sqrt(2*Math.PI*(sigma*sigma)));
        double base1 = 0.0;
        if(base.setScale(5, RoundingMode.HALF_UP).compareTo(base.round(new MathContext(3, RoundingMode.HALF_UP))) >= 0)
        {
            base1 = Double.parseDouble(base.setScale(5, RoundingMode.HALF_UP).toString());
        }
        else{

            base1 = Double.parseDouble(base.round(new MathContext(3, RoundingMode.HALF_UP)).toString());
        }

        Log.i(TAG,"Krrish: "+base);

            BigDecimal pow = BigDecimal.valueOf(-(Math.pow((x-mean), 2)/2*(sigma*sigma)));
        double pow1 = 0.0;
        if(pow.setScale(5, RoundingMode.HALF_UP).compareTo(pow.round(new MathContext(3, RoundingMode.HALF_UP))) >= 0)
        {
            pow1 = Double.parseDouble(pow.setScale(5, RoundingMode.HALF_UP).toString());
        }
        else{
            pow1 = Double.parseDouble(pow.round(new MathContext(3, RoundingMode.HALF_UP)).toString());
        }
        Log.i(TAG,"Krrish2: "+pow);


/*
        String tem_var = pow.multiply(BigDecimalMath.log(base)).toString();
        Log.i(TAG,"brikribrikri: "+tem_var);
*/
        double probability1 = 0;

        double sigma = Math.sqrt(sigma_squared);
        Log.i(TAG,"MEAN: "+mean);
        Log.i(TAG,"STD_DEV: "+sigma);
        Log.i(TAG,"VARIABLE_VALUE: "+x);

        /*BigDecimal probability2 = BigDecimalMath.pow(base,pow).round(new MathContext(10,RoundingMode.HALF_UP));
        Log.i(TAG,"PDF: "+probability2.toString());
        if(probability2.setScale(5,RoundingMode.HALF_UP).compareTo(probability2.round(new MathContext(3,RoundingMode.HALF_UP))) >= 0)
        {
            probability = Double.parseDouble(probability2.setScale(5,RoundingMode.HALF_UP).toString());
        }
        else
        {
            probability = Double.parseDouble(probability2.round(new MathContext(3,RoundingMode.HALF_UP)).toString());
        }

*/
        NormalDistribution temp_Distribution = new NormalDistribution(mean,sigma);

        probability1 = temp_Distribution.density(x);

        Log.i(TAG,"Krrish4: "+probability1);

        updateProgress();
        return probability1;


    }

    public void updateProgress(){

        progress_count = progress_count + 10;

        spinner.setProgress(progress_count);
    }


}



