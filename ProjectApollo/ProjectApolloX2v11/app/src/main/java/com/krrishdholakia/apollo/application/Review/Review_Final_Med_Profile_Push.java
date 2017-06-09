package com.krrishdholakia.apollo.application.Review;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.krrishdholakia.apollo.application.Information_Hub;
import com.krrishdholakia.apollo.projectapollox2v11.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by krrishdholakia on 8/12/16.
 */
public class Review_Final_Med_Profile_Push extends AppCompatActivity {

    Dialog info_dialog;
    Double User_Age;
    String User_Sex;
    Double User_Resting_BP = 0.0;
    Double User_Cholestrol = 0.0;
    Double User_Max_HR = 0.0;
    String User_Thalas;
    String User_FBS;
    Double User_Oldpeak = 0.0;
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
    String diagnosis = null;
    private String uID = null;

    private static final String TAG = "#Review_Med_Push";
    int track = 0;
    ProgressBar temp_Bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_med_profile_push);

        Bundle bundle = getIntent().getExtras();

        diagnosis = bundle.getString("Diagnosis");

        uID = bundle.getString("uID");

        temp_Bar = (ProgressBar) findViewById(R.id.progress_bar_med_push);

        temp_Bar.setIndeterminate(true);
        temp_Bar.setVisibility(View.VISIBLE);

        try {
            FileInputStream fileIn = openFileInput(uID + ".txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[1000];//setting arbitrary large number since the known dataset is far smaller
            String s = "";
            int charRead;
            String readstring = null;
            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;

                Log.i(TAG, "readstring: "+readstring);
            }
            InputRead.close();


            ArrayList<String> required_medical_information = new ArrayList<String>();

            final String[] row1 = readstring.split(",");

            final String[] row = new String[14];

            for (int count = 0; count < row1.length; count++) {
                String[] temp_string = row1[count].split(":");
                if (temp_string[0].equals("User_Age")) {
                    row[0] = temp_string[1];
                } else if (temp_string[0].equals("User_Sex")) {
                    if(temp_string[1].equals("Male"))
                    {
                        row[1] = "1";
                    }
                    else if(temp_string[1].equals("Female"))
                    {
                        row[1] = "0";
                    }
                } else if (temp_string[0].equals("User_Resting_BP")) {
                    row[3] = temp_string[1];
                } else if (temp_string[0].equals("User_Cholestrol")) {
                    row[4] = temp_string[1];
                } else if (temp_string[0].equals("User_Max_HR")) {
                    row[7] = temp_string[1];
                } else if (temp_string[0].equals("User_Thalas")) {
                    if(temp_string[1].equals("Three"))
                    {
                        row[12] = "3";
                    }
                    else if(temp_string[1].equals("Six"))
                    {
                        row[12] = "6";
                    }
                    else if(temp_string[1].equals("Seven"))
                    {
                        row[12] = "7";
                    }
                } else if (temp_string[0].equals("User_CA")) {
                    if(temp_string[1].equals("Zero"))
                    {
                        row[11] = "0";
                    }
                    else if(temp_string[0].equals("One"))
                    {
                        row[11] = "1";
                    }
                    else if(temp_string[0].equals("Two"))
                    {
                        row[11] = "2";
                    }
                    else if(temp_string[0].equals("Three"))
                    {
                        row[11] = "3";
                    }
                } else if (temp_string[0].equals("User_CPT")) {
                    //row[2] = temp_string[0];
                    if(temp_string[1].equals("L1"))
                    {
                        row[2] = "1";
                    }
                    else if(temp_string[1].equals("L2"))
                    {
                        row[2] = "2";
                    }
                    else if(temp_string[1].equals("L3"))
                    {
                        row[2] = "3";
                    }
                    else if(temp_string[1].equals("L4"))
                    {
                        row[2] = "4";
                    }
                } else if (temp_string[0].equals("User_EIA")) {
                    //row[8] = temp_string[0];
                    if(temp_string[1].equals("Zero"))
                    {
                        row[8] = "0";
                    }
                    else if(temp_string[1].equals("One"))
                    {
                        row[8] = "1";
                    }
                } else if (temp_string[0].equals("User_FBS")) {
                    row[5] = temp_string[1];
                } else if (temp_string[0].equals("User_Rest_EKG")) {
                    //row[6] = temp_string[0];
                    if(temp_string[1].equals("Zero"))
                    {
                        row[6] = "0";
                    }
                    else if(temp_string[1].equals("One"))
                    {
                        row[6] = "1";
                    }
                    else if(temp_string[1].equals("Two"))
                    {
                        row[6] = "2";
                    }
                } else if (temp_string[0].equals("User_Slope")) {
                    //row[10] = temp_string[0];
                    if(temp_string[1].equals("One"))
                    {
                        row[10] = "1";
                    }
                    else if(temp_string[1].equals("Two"))
                    {
                        row[10] = "2";
                    }
                    else if(temp_string[1].equals("Three"))
                    {
                        row[10] = "3";
                    }
                } else if (temp_string[0].equals("User_Oldpeak")) {
                    row[9] = temp_string[1];
                }
            }
            row[13] = diagnosis;


            final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("Probability");
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Probability");

            databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    DatabaseReference godRef = FirebaseDatabase.getInstance().getReference().child("Probability");
                    DatabaseReference userRef = godRef.child("HD_ND");

                    DatabaseReference hd_prob = godRef.child("HD_Probability");
                    //NORMALLY DISTRIBUTED VARIABELS (ND)
                    DatabaseReference ageRef_HD = userRef.child("Age_HD_ND");
                    DatabaseReference rbp_Level_HD = userRef.child("Rest_BP_Level_HD_ND");
                    DatabaseReference max_HR_HD = userRef.child("Max_HeartRate_HD_ND");
                    DatabaseReference s_Cholestoral_HD = userRef.child("Cholestoral_HD_ND");
                    DatabaseReference oldpeak_ND_HD = userRef.child("Oldpeak_HD_ND");
                    //BINARY [1-0] VARIABLES
                    DatabaseReference userRef_Bin = godRef.child("HD_Bin");
                    DatabaseReference sex_HD_male_HD = userRef_Bin.child("Sex_HD").child("Male");
                    DatabaseReference sex_HD_female_HD = userRef_Bin.child("Sex_HD").child("Female");
                    DatabaseReference cpt_L1_HD = userRef_Bin.child("CPT_HD").child("L1");
                    DatabaseReference cpt_L2_HD = userRef_Bin.child("CPT_HD").child("L2");
                    DatabaseReference cpt_L3_HD = userRef_Bin.child("CPT_HD").child("L3");
                    DatabaseReference cpt_L4_HD = userRef_Bin.child("CPT_HD").child("L4");
                    DatabaseReference fasting_BS_Level_True_HD = userRef_Bin.child("Fasting_Blood_Sugar_HD").child("True");
                    DatabaseReference fasting_BS_Level_False_HD = userRef_Bin.child("Fasting_Blood_Sugar_HD").child("False");
                    DatabaseReference rest_ecg_Zero_HD = userRef_Bin.child("Resting_ECG_HD").child("Zero");
                    DatabaseReference rest_ecg_One_HD = userRef_Bin.child("Resting_ECG_HD").child("One");
                    DatabaseReference rest_ecg_Two_HD = userRef_Bin.child("Resting_ECG_HD").child("Two");
                    DatabaseReference eia_HD_Zero_HD = userRef_Bin.child("Exercise_Induced_Angina_HD").child("Zero");
                    DatabaseReference eia_HD_One_HD = userRef_Bin.child("Exercise_Induced_Angina_HD").child("One");
                    DatabaseReference slope_HD_one_HD = userRef_Bin.child("Slope_HD").child("One");
                    DatabaseReference slope_HD_two_HD = userRef_Bin.child("Slope_HD").child("Two");
                    DatabaseReference slope_HD_three_HD = userRef_Bin.child("Slope_HD").child("Three");
                    DatabaseReference ca_HD_zero_HD = userRef_Bin.child("CA_HD").child("Zero");
                    DatabaseReference ca_HD_one_HD = userRef_Bin.child("CA_HD").child("One");
                    DatabaseReference ca_HD_two_HD = userRef_Bin.child("CA_HD").child("Two");
                    DatabaseReference ca_HD_three_HD = userRef_Bin.child("CA_HD").child("Three");
                    DatabaseReference thal_HD_three_HD = userRef_Bin.child("Thal_HD").child("Three");
                    DatabaseReference thal_HD_six_HD = userRef_Bin.child("Thal_HD").child("Six");
                    DatabaseReference thal_HD_seven_HD = userRef_Bin.child("Thal_HD").child("Seven");

                    DatabaseReference userRef2 = godRef.child("NO_HD_ND");
                    DatabaseReference no_hd_prob = godRef.child("NO_HD_Probability");
                    //NORMALLY DISTRIBUTED VARIABELS (ND)
                    DatabaseReference ageRef_NO_HD = userRef2.child("Age_NO_HD_ND");
                    DatabaseReference rbp_Level_NO_HD = userRef2.child("Rest_BP_Level_NO_HD_ND");
                    DatabaseReference max_HR_NO_HD = userRef2.child("Max_HeartRate_NO_HD_ND");
                    DatabaseReference oldpeak_ND_NO_HD = userRef2.child("Oldpeak_NO_HD_ND");
                    DatabaseReference s_Cholestoral_NO_HD = userRef2.child("Cholestoral_NO_HD");
                    //BINARY [1-0] VARIABLES
                    DatabaseReference userRef_Bin2 = godRef.child("NO_HD_Bin");
                    DatabaseReference sex_HD_male_NO_HD = userRef_Bin2.child("Sex_NO_HD").child("Male");
                    DatabaseReference sex_HD_female_NO_HD = userRef_Bin2.child("Sex_NO_HD").child("Female");
                    DatabaseReference cpt_L1_NO_HD = userRef_Bin2.child("CPT_NO_HD").child("L1");
                    DatabaseReference cpt_L2_NO_HD = userRef_Bin2.child("CPT_NO_HD").child("L2");
                    DatabaseReference cpt_L3_NO_HD = userRef_Bin2.child("CPT_NO_HD").child("L3");
                    DatabaseReference cpt_L4_NO_HD = userRef_Bin2.child("CPT_NO_HD").child("L4");
                    DatabaseReference fasting_BS_Level_True_NO_HD = userRef_Bin2.child("Fasting_Blood_Sugar_NO_HD").child("True");
                    DatabaseReference fasting_BS_Level_False_NO_HD = userRef_Bin2.child("Fasting_Blood_Sugar_NO_HD").child("False");
                    DatabaseReference rest_ecg_Zero_NO_HD = userRef_Bin2.child("Resting_ECG_NO_HD").child("Zero");
                    DatabaseReference rest_ecg_One_NO_HD = userRef_Bin2.child("Resting_ECG_NO_HD").child("One");
                    DatabaseReference rest_ecg_Two_NO_HD = userRef_Bin2.child("Resting_ECG_NO_HD").child("Two");
                    DatabaseReference eia_HD_Zero_NO_HD = userRef_Bin2.child("Exercise_Induced_Angina_NO_HD").child("Zero");
                    DatabaseReference eia_HD_One_NO_HD = userRef_Bin2.child("Exercise_Induced_Angina_NO_HD").child("One");
                    DatabaseReference slope_HD_one_NO_HD = userRef_Bin2.child("Slope_NO_HD").child("One");
                    DatabaseReference slope_HD_two_NO_HD = userRef_Bin2.child("Slope_NO_HD").child("Two");
                    DatabaseReference slope_HD_three_NO_HD = userRef_Bin2.child("Slope_NO_HD").child("Three");
                    DatabaseReference ca_HD_zero_NO_HD = userRef_Bin2.child("CA_NO_HD").child("Zero");
                    DatabaseReference ca_HD_one_NO_HD = userRef_Bin2.child("CA_NO_HD").child("One");
                    DatabaseReference ca_HD_two_NO_HD = userRef_Bin2.child("CA_NO_HD").child("Two");
                    DatabaseReference ca_HD_three_NO_HD = userRef_Bin2.child("CA_NO_HD").child("Three");
                    DatabaseReference thal_HD_three_NO_HD = userRef_Bin2.child("Thal_NO_HD").child("Three");
                    DatabaseReference thal_HD_six_NO_HD = userRef_Bin2.child("Thal_NO_HD").child("Six");
                    DatabaseReference thal_HD_seven_NO_HD = userRef_Bin2.child("Thal_NO_HD").child("Seven");

                    if(diagnosis.equals("1"))
                    {
                        for (int count = 0; count < row1.length; count++) {
                            String[] temp_string = row1[count].split(":");
                            if (temp_string[0].equals("User_Age")) {
                                double sum_of_Var = Double.parseDouble(dataSnapshot.child("HD_ND").child("Age_HD_ND").child("sum_of_Var").getValue().toString()) + Double.parseDouble(temp_string[1]);
                                ageRef_HD.child("sum_of_Var").setValue(sum_of_Var);
                                double sum_of_Var_squared = Double.parseDouble(dataSnapshot.child("HD_ND").child("Age_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Double.parseDouble(temp_string[1])*Double.parseDouble(temp_string[1]));
                                ageRef_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                double number_of_Var = Double.parseDouble(dataSnapshot.child("HD_ND").child("Age_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                ageRef_HD.child("number_of_Var").setValue(number_of_Var);


                                Log.i(TAG,"brian's met");

                            } else if (temp_string[0].equals("User_Sex")) {
                                if(temp_string[1].equals("Male"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Sex_HD").child("Male").child("probability_of_occ").getValue().toString()) + 1;
                                    sex_HD_male_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Sex_HD").child("Male").child("total_number").getValue().toString()) + 1;
                                    sex_HD_male_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("Female"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Sex_HD").child("Female").child("probability_of_occ").getValue().toString()) + 1;
                                    sex_HD_female_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Sex_HD").child("Female").child("total_number").getValue().toString()) + 1;
                                    sex_HD_female_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_Resting_BP")) {
                                double sum_of_Var = Double.parseDouble(dataSnapshot.child("HD_ND").child("Rest_BP_Level_HD_ND").child("sum_of_Var").getValue().toString()) + Double.parseDouble(temp_string[1]);
                                rbp_Level_HD.child("sum_of_Var").setValue(sum_of_Var);
                                double sum_of_Var_squared = Double.parseDouble(dataSnapshot.child("HD_ND").child("Rest_BP_Level_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Double.parseDouble(temp_string[1])*Double.parseDouble(temp_string[1]));
                                rbp_Level_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                double number_of_Var = Double.parseDouble(dataSnapshot.child("HD_ND").child("Rest_BP_Level_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                rbp_Level_HD.child("number_of_Var").setValue(number_of_Var);
                            } else if (temp_string[0].equals("User_Cholestrol")) {
                                double sum_of_Var = Double.parseDouble(dataSnapshot.child("HD_ND").child("Cholestoral_HD_ND").child("sum_of_Var").getValue().toString()) + Double.parseDouble(temp_string[1]);
                                s_Cholestoral_HD.child("sum_of_Var").setValue(sum_of_Var);
                                double sum_of_Var_squared = Double.parseDouble(dataSnapshot.child("HD_ND").child("Cholestoral_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Double.parseDouble(temp_string[1])*Double.parseDouble(temp_string[1]));
                                s_Cholestoral_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                double number_of_Var = Double.parseDouble(dataSnapshot.child("HD_ND").child("Cholestoral_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                s_Cholestoral_HD.child("number_of_Var").setValue(number_of_Var);
                            } else if (temp_string[0].equals("User_Max_HR")) {
                                double sum_of_Var = Double.parseDouble(dataSnapshot.child("HD_ND").child("Max_HeartRate_HD_ND").child("sum_of_Var").getValue().toString()) + Double.parseDouble(temp_string[1]);
                                max_HR_HD.child("sum_of_Var").setValue(sum_of_Var);
                                double sum_of_Var_squared = Double.parseDouble(dataSnapshot.child("HD_ND").child("Max_HeartRate_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Double.parseDouble(temp_string[1])*Double.parseDouble(temp_string[1]));
                                max_HR_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                double number_of_Var = Double.parseDouble(dataSnapshot.child("HD_ND").child("Max_HeartRate_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                max_HR_HD.child("number_of_Var").setValue(number_of_Var);
                            } else if (temp_string[0].equals("User_Thalas")) {
                                if(temp_string[1].equals("Three"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Thal_HD").child("Three").child("probability_of_occ").getValue().toString()) + 1;
                                    thal_HD_three_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Thal_HD").child("Three").child("total_number").getValue().toString()) + 1;
                                    thal_HD_three_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("Six"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Thal_HD").child("Six").child("probability_of_occ").getValue().toString()) + 1;
                                    thal_HD_six_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Thal_HD").child("Six").child("total_number").getValue().toString()) + 1;
                                    thal_HD_six_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("Seven"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Thal_HD").child("Seven").child("probability_of_occ").getValue().toString()) + 1;
                                    thal_HD_seven_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Thal_HD").child("Seven").child("total_number").getValue().toString()) + 1;
                                    thal_HD_seven_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_CA")) {
                                if(temp_string[1].equals("Zero"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child("Zero").child("probability_of_occ").getValue().toString()) + 1;
                                    ca_HD_zero_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child("Zero").child("total_number").getValue().toString()) + 1;
                                    ca_HD_zero_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[0].equals("One"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                    ca_HD_one_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child("One").child("total_number").getValue().toString()) + 1;
                                    ca_HD_one_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[0].equals("Two"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child("Two").child("probability_of_occ").getValue().toString()) + 1;
                                    ca_HD_two_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child("Two").child("total_number").getValue().toString()) + 1;
                                    ca_HD_two_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[0].equals("Three"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child("Three").child("probability_of_occ").getValue().toString()) + 1;
                                    ca_HD_three_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CA_HD").child("Three").child("total_number").getValue().toString()) + 1;
                                    ca_HD_three_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_CPT")) {
                                //row[2] = temp_string[0];
                                if(temp_string[1].equals("L1"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L1").child("probability_of_occ").getValue().toString()) + 1;
                                    cpt_L1_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L1").child("total_number").getValue().toString()) + 1;
                                    cpt_L1_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("L2"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L2").child("probability_of_occ").getValue().toString()) + 1;
                                    cpt_L2_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L2").child("total_number").getValue().toString()) + 1;
                                    cpt_L2_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("L3"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L3").child("probability_of_occ").getValue().toString()) + 1;
                                    cpt_L3_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L3").child("total_number").getValue().toString()) + 1;
                                    cpt_L3_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("L4"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L4").child("probability_of_occ").getValue().toString()) + 1;
                                    cpt_L4_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L4").child("total_number").getValue().toString()) + 1;
                                    cpt_L4_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_EIA")) {
                                //row[8] = temp_string[0];
                                if(temp_string[1].equals("Zero"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Exercise_Induced_Angina_HD").child("Zero").child("probability_of_occ").getValue().toString()) + 1;
                                    eia_HD_Zero_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Exercise_Induced_Angina_HD").child("Zero").child("total_number").getValue().toString()) + 1;
                                    eia_HD_Zero_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("One"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Exercise_Induced_Angina_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                    eia_HD_One_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Exercise_Induced_Angina_HD").child("One").child("total_number").getValue().toString()) + 1;
                                    eia_HD_One_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_FBS")) {
                                //row[5] = temp_string[0];
                                if(temp_string[1].equals("False"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Fasting_Blood_Sugar_HD").child("False").child("probability_of_occ").getValue().toString()) + 1;
                                    fasting_BS_Level_False_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Fasting_Blood_Sugar_HD").child("False").child("total_number").getValue().toString()) + 1;
                                    fasting_BS_Level_False_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("True"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Fasting_Blood_Sugar_HD").child("True").child("probability_of_occ").getValue().toString()) + 1;
                                    fasting_BS_Level_True_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Fasting_Blood_Sugar_HD").child("True").child("total_number").getValue().toString()) + 1;
                                    fasting_BS_Level_True_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_Rest_EKG")) {
                                //row[6] = temp_string[0];
                                if(temp_string[1].equals("Zero"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("Zero").child("probability_of_occ").getValue().toString()) + 1;
                                    rest_ecg_Zero_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("Zero").child("total_number").getValue().toString()) + 1;
                                    rest_ecg_Zero_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("One"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                    rest_ecg_One_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("One").child("total_number").getValue().toString()) + 1;
                                    rest_ecg_One_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("Two"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("Two").child("probability_of_occ").getValue().toString()) + 1;
                                    rest_ecg_Two_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("Two").child("total_number").getValue().toString()) + 1;
                                    rest_ecg_Two_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_Slope")) {
                                //row[10] = temp_string[0];
                                if(temp_string[1].equals("One"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Slope_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                    slope_HD_one_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Slope_HD").child("One").child("total_number").getValue().toString()) + 1;
                                    slope_HD_one_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("Two"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Slope_HD").child("Two").child("probability_of_occ").getValue().toString()) + 1;
                                    slope_HD_two_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Slope_HD").child("Two").child("total_number").getValue().toString()) + 1;
                                    slope_HD_two_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("Three"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Slope_HD").child("Three").child("probability_of_occ").getValue().toString()) + 1;
                                    slope_HD_three_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("HD_Bin").child("Slope_HD").child("Three").child("total_number").getValue().toString()) + 1;
                                    slope_HD_three_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_Oldpeak")) {
                                double sum_of_Var = Double.parseDouble(dataSnapshot.child("HD_ND").child("Oldpeak_HD_ND").child("sum_of_Var").getValue().toString()) + Double.parseDouble(temp_string[1]);
                                oldpeak_ND_HD.child("sum_of_Var").setValue(sum_of_Var);
                                double sum_of_Var_squared = Double.parseDouble(dataSnapshot.child("HD_ND").child("Oldpeak_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Double.parseDouble(temp_string[1])*Double.parseDouble(temp_string[1]));
                                oldpeak_ND_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                double number_of_Var = Double.parseDouble(dataSnapshot.child("HD_ND").child("Oldpeak_HD_ND").child("number_of_Var").getValue().toString()) + Double.parseDouble(temp_string[1]);
                                oldpeak_ND_HD.child("number_of_Var").setValue(number_of_Var);
                            }
                        }

                        double probability_of_occ = Double.parseDouble(dataSnapshot.child("HD_Probability").child("probability_of_occ").getValue().toString()) + 1;
                        hd_prob.child("probability_of_occ").setValue(probability_of_occ);
                        double total_number = Double.parseDouble(dataSnapshot.child("HD_Probability").child("total_number").getValue().toString()) + 1;
                        hd_prob.child("total_number").setValue(total_number);

                        Log.i(TAG,"krrish going to cmu");

                        Intent i = new Intent(Review_Final_Med_Profile_Push.this, Information_Hub.class);
                        i.putExtra("uID",uID);
                        startActivity(i);
                    }

                    else if(diagnosis.equals("0"))
                    {
                        for (int count = 0; count < row1.length; count++) {
                            String[] temp_string = row1[count].split(":");
                            if (temp_string[0].equals("User_Age")) {
                                double sum_of_Var = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Age_NO_HD_ND").child("sum_of_Var").getValue().toString()) + Double.parseDouble(temp_string[1]);
                                ageRef_NO_HD.child("sum_of_Var").setValue(sum_of_Var);
                                double sum_of_Var_squared = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Age_NO_HD_ND").child("sum_of_Var_squared").getValue().toString()) + Double.parseDouble(temp_string[1]);
                                ageRef_NO_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                double number_of_Var = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Age_NO_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                ageRef_NO_HD.child("number_of_Var").setValue(number_of_Var);
                            } else if (temp_string[0].equals("User_Sex")) {
                                if(temp_string[1].equals("Male"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Sex_NO_HD").child("Male").child("probability_of_occ").getValue().toString()) + 1;
                                    sex_HD_male_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Sex_NO_HD").child("Male").child("total_number").getValue().toString()) + 1;
                                    sex_HD_male_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("Female"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Sex_NO_HD").child("Female").child("probability_of_occ").getValue().toString()) + 1;
                                    sex_HD_female_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Sex_NO_HD").child("Female").child("total_number").getValue().toString()) + 1;
                                    sex_HD_female_NO_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_Resting_BP")) {
                                double sum_of_Var = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Rest_BP_Level_NO_HD_ND").child("sum_of_Var").getValue().toString()) + Double.parseDouble(temp_string[1]);
                                rbp_Level_NO_HD.child("sum_of_Var").setValue(sum_of_Var);
                                double sum_of_Var_squared = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Rest_BP_Level_NO_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Double.parseDouble(temp_string[1])*Double.parseDouble(temp_string[1]));
                                rbp_Level_NO_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                double number_of_Var = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Rest_BP_Level_NO_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                rbp_Level_NO_HD.child("number_of_Var").setValue(number_of_Var);
                            } else if (temp_string[0].equals("User_Cholestrol")) {
                                double sum_of_Var = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Cholestoral_NO_HD").child("sum_of_Var").getValue().toString()) + Double.parseDouble(temp_string[1]);
                                s_Cholestoral_NO_HD.child("sum_of_Var").setValue(sum_of_Var);
                                double sum_of_Var_squared = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Cholestoral_NO_HD").child("sum_of_Var_squared").getValue().toString()) + (Double.parseDouble(temp_string[1])*Double.parseDouble(temp_string[1]));
                                s_Cholestoral_NO_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                double number_of_Var = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Cholestoral_NO_HD").child("number_of_Var").getValue().toString()) + 1;
                                s_Cholestoral_NO_HD.child("number_of_Var").setValue(number_of_Var);
                            } else if (temp_string[0].equals("User_Max_HR")) {
                                double sum_of_Var = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Max_HeartRate_NO_HD_ND").child("sum_of_Var").getValue().toString()) + Double.parseDouble(temp_string[1]);
                                max_HR_NO_HD.child("sum_of_Var").setValue(sum_of_Var);
                                double sum_of_Var_squared = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Max_HeartRate_NO_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Double.parseDouble(temp_string[1])*Double.parseDouble(temp_string[1]));
                                max_HR_NO_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                double number_of_Var = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Max_HeartRate_NO_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                max_HR_NO_HD.child("number_of_Var").setValue(number_of_Var);
                            } else if (temp_string[0].equals("User_Thalas")) {
                                if(temp_string[1].equals("Three"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Thal_NO_HD").child("Three").child("probability_of_occ").getValue().toString()) + 1;
                                    thal_HD_three_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Thal_NO_HD").child("Three").child("total_number").getValue().toString()) + 1;
                                    thal_HD_three_NO_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("Six"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Thal_NO_HD").child("Six").child("probability_of_occ").getValue().toString()) + 1;
                                    thal_HD_six_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Thal_NO_HD").child("Six").child("total_number").getValue().toString()) + 1;
                                    thal_HD_six_NO_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("Seven"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Thal_NO_HD").child("Seven").child("probability_of_occ").getValue().toString()) + 1;
                                    thal_HD_seven_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Thal_NO_HD").child("Seven").child("total_number").getValue().toString()) + 1;
                                    thal_HD_seven_NO_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_CA")) {
                                if(temp_string[1].equals("Zero"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Zero").child("probability_of_occ").getValue().toString()) + 1;
                                    ca_HD_zero_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Zero").child("total_number").getValue().toString()) + 1;
                                    ca_HD_zero_NO_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[0].equals("One"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                    ca_HD_one_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("One").child("total_number").getValue().toString()) + 1;
                                    ca_HD_one_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[0].equals("Two"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Two").child("probability_of_occ").getValue().toString()) + 1;
                                    ca_HD_two_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Two").child("total_number").getValue().toString()) + 1;
                                    ca_HD_two_NO_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[0].equals("Three"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Three").child("probability_of_occ").getValue().toString()) + 1;
                                    ca_HD_three_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Three").child("total_number").getValue().toString()) + 1;
                                    ca_HD_three_NO_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_CPT")) {
                                //row[2] = temp_string[0];
                                if(temp_string[1].equals("L1"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L1").child("probability_of_occ").getValue().toString()) + 1;
                                    cpt_L1_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L1").child("total_number").getValue().toString()) + 1;
                                    cpt_L1_NO_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("L2"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L2").child("probability_of_occ").getValue().toString()) + 1;
                                    cpt_L2_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L2").child("total_number").getValue().toString()) + 1;
                                    cpt_L2_NO_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("L3"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L3").child("probability_of_occ").getValue().toString()) + 1;
                                    cpt_L3_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L3").child("total_number").getValue().toString()) + 1;
                                    cpt_L3_NO_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("L4"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L4").child("probability_of_occ").getValue().toString()) + 1;
                                    cpt_L4_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L4").child("total_number").getValue().toString()) + 1;
                                    cpt_L4_NO_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_EIA")) {
                                //row[8] = temp_string[0];
                                if(temp_string[1].equals("Zero"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Exercise_Induced_Angina_NO_HD").child("Zero").child("probability_of_occ").getValue().toString()) + 1;
                                    eia_HD_Zero_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Exercise_Induced_Angina_NO_HD").child("Zero").child("total_number").getValue().toString()) + 1;
                                    eia_HD_Zero_NO_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("One"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Exercise_Induced_Angina_NO_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                    eia_HD_One_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Exercise_Induced_Angina_NO_HD").child("One").child("total_number").getValue().toString()) + 1;
                                    eia_HD_One_NO_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_FBS")) {
                                //row[5] = temp_string[0];
                                if(temp_string.equals("False"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Fasting_Blood_Sugar_NO_HD").child("False").child("probability_of_occ").getValue().toString()) + 1;
                                    fasting_BS_Level_False_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Fasting_Blood_Sugar_NO_HD").child("False").child("total_number").getValue().toString()) + 1;
                                    fasting_BS_Level_False_NO_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string.equals("False"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Fasting_Blood_Sugar_NO_HD").child("True").child("probability_of_occ").getValue().toString()) + 1;
                                    fasting_BS_Level_True_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Fasting_Blood_Sugar_NO_HD").child("True").child("total_number").getValue().toString()) + 1;
                                    fasting_BS_Level_True_NO_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_Rest_EKG")) {
                                //row[6] = temp_string[0];
                                if(temp_string[1].equals("Zero"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("Zero").child("probability_of_occ").getValue().toString()) + 1;
                                    rest_ecg_Zero_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("Zero").child("total_number").getValue().toString()) + 1;
                                    rest_ecg_Zero_NO_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("One"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                    rest_ecg_One_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("One").child("total_number").getValue().toString()) + 1;
                                    rest_ecg_One_NO_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("Two"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("Two").child("probability_of_occ").getValue().toString()) + 1;
                                    rest_ecg_Two_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("Two").child("total_number").getValue().toString()) + 1;
                                    rest_ecg_Two_NO_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_Slope")) {
                                //row[10] = temp_string[0];
                                if(temp_string[1].equals("One"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                    slope_HD_one_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("One").child("total_number").getValue().toString()) + 1;
                                    slope_HD_one_NO_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("Two"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("Two").child("probability_of_occ").getValue().toString()) + 1;
                                    slope_HD_two_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("Two").child("total_number").getValue().toString()) + 1;
                                    slope_HD_two_NO_HD.child("total_number").setValue(total_number);
                                }
                                else if(temp_string[1].equals("Three"))
                                {
                                    double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("Three").child("probability_of_occ").getValue().toString()) + 1;
                                    slope_HD_three_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                    double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("Three").child("total_number").getValue().toString()) + 1;
                                    slope_HD_three_NO_HD.child("total_number").setValue(total_number);
                                }
                            } else if (temp_string[0].equals("User_Oldpeak")) {
                                double sum_of_Var = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Oldpeak_NO_HD_ND").child("sum_of_Var").getValue().toString()) + Double.parseDouble(temp_string[1]);
                                oldpeak_ND_NO_HD.child("sum_of_Var").setValue(sum_of_Var);
                                double sum_of_Var_squared = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Oldpeak_NO_HD_ND").child("sum_of_Var_squared").getValue().toString()) + Double.parseDouble(temp_string[1]);
                                oldpeak_ND_NO_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                double number_of_Var = Double.parseDouble(dataSnapshot.child("NO_HD_ND").child("Oldpeak_NO_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                oldpeak_ND_NO_HD.child("number_of_Var").setValue(number_of_Var);
                            }
                        }

                        double probability_of_occ = Double.parseDouble(dataSnapshot.child("NO_HD_Probability").child("probability_of_occ").getValue().toString()) + 1;
                        no_hd_prob.child("probability_of_occ").setValue(probability_of_occ);
                        double total_number = Double.parseDouble(dataSnapshot.child("NO_HD_Probability").child("total_number").getValue().toString()) + 1;
                        no_hd_prob.child("total_number").setValue(total_number);

                        Intent i = new Intent(Review_Final_Med_Profile_Push.this, Information_Hub.class);
                        i.putExtra("uID",uID);
                        startActivity(i);

                    }


                    track = 1;


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });

        } catch (FileNotFoundException e) {

            Toast.makeText(Review_Final_Med_Profile_Push.this,"Please update your medical information",Toast.LENGTH_LONG).show();
            Intent i = new Intent(Review_Final_Med_Profile_Push.this, Review_Final_Add.class);
            i.putExtra("uID",uID);
            i.putExtra("Diagnosis",diagnosis);
            startActivity(i);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(track == 1)
        {
            Intent i = new Intent(Review_Final_Med_Profile_Push.this, Information_Hub.class);
            i.putExtra("uID",uID);
            startActivity(i);
        }


    }
}

