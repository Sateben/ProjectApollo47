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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krrishdholakia on 8/12/16.
 */
public class Review_Final_Add extends AppCompatActivity {

    private static final String TAG = "Review_F_Add";

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
    private String diagnosis = null;
    private String uID = null;
    ProgressBar tempBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_final_add);

        Bundle bundle = getIntent().getExtras();

        diagnosis = bundle.getString("Diagnosis");

        uID = bundle.getString("uID");
        Log.i(TAG,"uID: "+uID.toString());

        tempBar = (ProgressBar) findViewById(R.id.progress_bar_review);

        tempBar.setIndeterminate(true);

        tempBar.setVisibility(View.INVISIBLE);

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
                    tempBar.setVisibility(View.INVISIBLE);
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
                        }
                        InputRead.close();


                        final String[] row1 = readstring.split(",");

                        final String[] row = new String[14];

                        for (int count = 0; count < row1.length; count++) {
                            String[] temp_string = row1[count].split(":");
                            if (temp_string[0].equals("User_Age")) {
                                row[0] = temp_string[1];
                            } else if (temp_string[0].equals("User_Sex")) {
                                if (temp_string[1].equals("Male")) {
                                    row[1] = "1";
                                } else if (temp_string[1].equals("Female")) {
                                    row[1] = "0";
                                }
                            } else if (temp_string[0].equals("User_Resting_BP")) {
                                row[3] = temp_string[1];
                            } else if (temp_string[0].equals("User_Cholestrol")) {
                                row[4] = temp_string[1];
                            } else if (temp_string[0].equals("User_Max_HR")) {
                                row[7] = temp_string[1];
                            } else if (temp_string[0].equals("User_Thalas")) {
                                if (temp_string[1].equals("Three")) {
                                    row[12] = "3";
                                } else if (temp_string[1].equals("Six")) {
                                    row[12] = "6";
                                } else if (temp_string[1].equals("Seven")) {
                                    row[12] = "7";
                                }
                            } else if (temp_string[0].equals("User_CA")) {
                                if (temp_string[1].equals("Zero")) {
                                    row[11] = "0";
                                } else if (temp_string[0].equals("One")) {
                                    row[11] = "1";
                                } else if (temp_string[0].equals("Two")) {
                                    row[11] = "2";
                                } else if (temp_string[0].equals("Three")) {
                                    row[11] = "3";
                                }
                            } else if (temp_string[0].equals("User_CPT")) {
                                //row[2] = temp_string[0];
                                if (temp_string[1].equals("L1")) {
                                    row[2] = "1";
                                } else if (temp_string[1].equals("L2")) {
                                    row[2] = "2";
                                } else if (temp_string[1].equals("L3")) {
                                    row[2] = "3";
                                } else if (temp_string[1].equals("L4")) {
                                    row[2] = "4";
                                }
                            } else if (temp_string[0].equals("User_EIA")) {
                                //row[8] = temp_string[0];
                                if (temp_string[1].equals("Zero")) {
                                    row[8] = "0";
                                } else if (temp_string[1].equals("One")) {
                                    row[8] = "1";
                                }
                            } else if (temp_string[0].equals("User_FBS")) {
                                //row[5] = temp_string[0];
                                if (Double.parseDouble(temp_string[1]) <= 120) {
                                    row[5] = "False";
                                } else if (Double.parseDouble(temp_string[1]) > 120) {
                                    row[5] = "True";
                                }
                            } else if (temp_string[0].equals("User_Rest_EKG")) {
                                //row[6] = temp_string[0];
                                if (temp_string[1].equals("Zero")) {
                                    row[6] = "0";
                                } else if (temp_string[1].equals("One")) {
                                    row[6] = "1";
                                } else if (temp_string[1].equals("Two")) {
                                    row[6] = "2";
                                }
                            } else if (temp_string[0].equals("User_Slope")) {
                                //row[10] = temp_string[0];
                                if (temp_string[1].equals("One")) {
                                    row[10] = "1";
                                } else if (temp_string[1].equals("Two")) {
                                    row[10] = "2";
                                } else if (temp_string[1].equals("Three")) {
                                    row[10] = "3";
                                }
                            } else if (temp_string[0].equals("User_Oldpeak")) {
                                row[9] = temp_string[1];
                            }
                        }
                        row[13] = diagnosis;


                        final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("Probability");
                        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Probability");

                        List resultList = new ArrayList();

                        int value = (Integer.parseInt(row[0].replaceAll("[^0-9]", ""))) / 10;
                        int value2 = (Integer.parseInt(row[13].replaceAll("[^0-9]", "")));


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

                                if (diagnosis.equals("1")) {
                                    for (int count = 0; count < row1.length; count++) {
                                        String[] temp_string = row1[count].split(":");
                                        if (temp_string[0].equals("User_Age")) {
                                            int sum_of_Var = Integer.parseInt(dataSnapshot.child("HD_ND").child("Age_HD_ND").child("sum_of_Var").getValue().toString()) + Integer.parseInt(temp_string[1]);
                                            ageRef_HD.child("sum_of_Var").setValue(sum_of_Var);
                                            int sum_of_Var_squared = Integer.parseInt(dataSnapshot.child("HD_ND").child("Age_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Integer.parseInt(temp_string[1]) * Integer.parseInt(temp_string[1]));
                                            ageRef_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                            int number_of_Var = Integer.parseInt(dataSnapshot.child("HD_ND").child("Age_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                            ageRef_HD.child("number_of_Var").setValue(number_of_Var);

                                        } else if (temp_string[0].equals("User_Sex")) {
                                            if (temp_string[1].equals("Male")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Sex_HD").child("Male").child("probability_of_occ").getValue().toString()) + 1;
                                                sex_HD_male_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Sex_HD").child("Male").child("total_number").getValue().toString()) + 1;
                                                sex_HD_male_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("Female")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Sex_HD").child("Female").child("probability_of_occ").getValue().toString()) + 1;
                                                sex_HD_female_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Sex_HD").child("Female").child("total_number").getValue().toString()) + 1;
                                                sex_HD_female_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_Resting_BP")) {
                                            int sum_of_Var = Integer.parseInt(dataSnapshot.child("HD_ND").child("Rest_BP_Level_HD_ND").child("sum_of_Var").getValue().toString()) + Integer.parseInt(temp_string[1]);
                                            rbp_Level_HD.child("sum_of_Var").setValue(sum_of_Var);
                                            int sum_of_Var_squared = Integer.parseInt(dataSnapshot.child("HD_ND").child("Rest_BP_Level_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Integer.parseInt(temp_string[1]) * Integer.parseInt(temp_string[1]));
                                            rbp_Level_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                            int number_of_Var = Integer.parseInt(dataSnapshot.child("HD_ND").child("Rest_BP_Level_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                            rbp_Level_HD.child("number_of_Var").setValue(number_of_Var);
                                        } else if (temp_string[0].equals("User_Cholestrol")) {
                                            int sum_of_Var = Integer.parseInt(dataSnapshot.child("HD_ND").child("Cholestoral_HD_ND").child("sum_of_Var").getValue().toString()) + Integer.parseInt(temp_string[1]);
                                            s_Cholestoral_HD.child("sum_of_Var").setValue(sum_of_Var);
                                            int sum_of_Var_squared = Integer.parseInt(dataSnapshot.child("HD_ND").child("Cholestoral_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Integer.parseInt(temp_string[1]) * Integer.parseInt(temp_string[1]));
                                            s_Cholestoral_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                            int number_of_Var = Integer.parseInt(dataSnapshot.child("HD_ND").child("Cholestoral_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                            s_Cholestoral_HD.child("number_of_Var").setValue(number_of_Var);
                                        } else if (temp_string[0].equals("User_Max_HR")) {
                                            int sum_of_Var = Integer.parseInt(dataSnapshot.child("HD_ND").child("Max_HeartRate_HD_ND").child("sum_of_Var").getValue().toString()) + Integer.parseInt(temp_string[1]);
                                            max_HR_HD.child("sum_of_Var").setValue(sum_of_Var);
                                            int sum_of_Var_squared = Integer.parseInt(dataSnapshot.child("HD_ND").child("Max_HeartRate_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Integer.parseInt(temp_string[1]) * Integer.parseInt(temp_string[1]));
                                            max_HR_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                            int number_of_Var = Integer.parseInt(dataSnapshot.child("HD_ND").child("Max_HeartRate_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                            max_HR_HD.child("number_of_Var").setValue(number_of_Var);
                                        } else if (temp_string[0].equals("User_Thalas")) {
                                            if (temp_string[1].equals("Three")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Thal_HD").child("Three").child("probability_of_occ").getValue().toString()) + 1;
                                                thal_HD_three_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Thal_HD").child("Three").child("total_number").getValue().toString()) + 1;
                                                thal_HD_three_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("Six")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Thal_HD").child("Six").child("probability_of_occ").getValue().toString()) + 1;
                                                thal_HD_six_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Thal_HD").child("Six").child("total_number").getValue().toString()) + 1;
                                                thal_HD_six_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("Seven")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Thal_HD").child("Seven").child("probability_of_occ").getValue().toString()) + 1;
                                                thal_HD_seven_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Thal_HD").child("Seven").child("total_number").getValue().toString()) + 1;
                                                thal_HD_seven_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_CA")) {
                                            if (temp_string[1].equals("Zero")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CA_HD").child("Zero").child("probability_of_occ").getValue().toString()) + 1;
                                                ca_HD_zero_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CA_HD").child("Zero").child("total_number").getValue().toString()) + 1;
                                                ca_HD_zero_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[0].equals("One")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CA_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                                ca_HD_one_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CA_HD").child("One").child("total_number").getValue().toString()) + 1;
                                                ca_HD_one_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[0].equals("Two")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CA_HD").child("Two").child("probability_of_occ").getValue().toString()) + 1;
                                                ca_HD_two_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CA_HD").child("Two").child("total_number").getValue().toString()) + 1;
                                                ca_HD_two_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[0].equals("Three")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CA_HD").child("Three").child("probability_of_occ").getValue().toString()) + 1;
                                                ca_HD_three_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CA_HD").child("Three").child("total_number").getValue().toString()) + 1;
                                                ca_HD_three_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_CPT")) {
                                            //row[2] = temp_string[0];
                                            if (temp_string[1].equals("L1")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L1").child("probability_of_occ").getValue().toString()) + 1;
                                                cpt_L1_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L1").child("total_number").getValue().toString()) + 1;
                                                cpt_L1_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("L2")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L2").child("probability_of_occ").getValue().toString()) + 1;
                                                cpt_L2_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L2").child("total_number").getValue().toString()) + 1;
                                                cpt_L2_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("L3")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L3").child("probability_of_occ").getValue().toString()) + 1;
                                                cpt_L3_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L3").child("total_number").getValue().toString()) + 1;
                                                cpt_L3_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("L4")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L4").child("probability_of_occ").getValue().toString()) + 1;
                                                cpt_L4_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("CPT_HD").child("L4").child("total_number").getValue().toString()) + 1;
                                                cpt_L4_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_EIA")) {
                                            //row[8] = temp_string[0];
                                            if (temp_string[1].equals("Zero")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Exercise_Induced_Angina_HD").child("Zero").child("probability_of_occ").getValue().toString()) + 1;
                                                eia_HD_Zero_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Exercise_Induced_Angina_HD").child("Zero").child("total_number").getValue().toString()) + 1;
                                                eia_HD_Zero_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("One")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Exercise_Induced_Angina_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                                eia_HD_One_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Exercise_Induced_Angina_HD").child("One").child("total_number").getValue().toString()) + 1;
                                                eia_HD_One_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_FBS")) {
                                            //row[5] = temp_string[0];
                                            if (Double.parseDouble(temp_string[1]) <= 120) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Fasting_Blood_Sugar_HD").child("False").child("probability_of_occ").getValue().toString()) + 1;
                                                fasting_BS_Level_False_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Fasting_Blood_Sugar_HD").child("False").child("total_number").getValue().toString()) + 1;
                                                fasting_BS_Level_False_HD.child("total_number").setValue(total_number);
                                            } else if (Double.parseDouble(temp_string[1]) > 120) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Fasting_Blood_Sugar_HD").child("True").child("probability_of_occ").getValue().toString()) + 1;
                                                fasting_BS_Level_True_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Fasting_Blood_Sugar_HD").child("True").child("total_number").getValue().toString()) + 1;
                                                fasting_BS_Level_True_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_Rest_EKG")) {
                                            //row[6] = temp_string[0];
                                            if (temp_string[1].equals("Zero")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("Zero").child("probability_of_occ").getValue().toString()) + 1;
                                                rest_ecg_Zero_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("Zero").child("total_number").getValue().toString()) + 1;
                                                rest_ecg_Zero_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("One")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                                rest_ecg_One_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("One").child("total_number").getValue().toString()) + 1;
                                                rest_ecg_One_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("Two")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("Two").child("probability_of_occ").getValue().toString()) + 1;
                                                rest_ecg_Two_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Resting_ECG_HD").child("Two").child("total_number").getValue().toString()) + 1;
                                                rest_ecg_Two_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_Slope")) {
                                            //row[10] = temp_string[0];
                                            if (temp_string[1].equals("One")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Slope_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                                slope_HD_one_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Slope_HD").child("One").child("total_number").getValue().toString()) + 1;
                                                slope_HD_one_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("Two")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Slope_HD").child("Two").child("probability_of_occ").getValue().toString()) + 1;
                                                slope_HD_two_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Slope_HD").child("Two").child("total_number").getValue().toString()) + 1;
                                                slope_HD_two_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("Three")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Slope_HD").child("Three").child("probability_of_occ").getValue().toString()) + 1;
                                                slope_HD_three_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("HD_Bin").child("Slope_HD").child("Three").child("total_number").getValue().toString()) + 1;
                                                slope_HD_three_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_Oldpeak")) {
                                            int sum_of_Var = Integer.parseInt(dataSnapshot.child("HD_ND").child("Oldpeak_HD_ND").child("sum_of_Var").getValue().toString()) + Integer.parseInt(temp_string[1]);
                                            oldpeak_ND_HD.child("sum_of_Var").setValue(sum_of_Var);
                                            int sum_of_Var_squared = Integer.parseInt(dataSnapshot.child("HD_ND").child("Oldpeak_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Integer.parseInt(temp_string[1]) * Integer.parseInt(temp_string[1]));
                                            oldpeak_ND_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                            int number_of_Var = Integer.parseInt(dataSnapshot.child("HD_ND").child("Oldpeak_HD_ND").child("number_of_Var").getValue().toString()) + Integer.parseInt(temp_string[1]);
                                            oldpeak_ND_HD.child("number_of_Var").setValue(number_of_Var);
                                        }
                                    }

                                    int probability_of_occ = Integer.parseInt(dataSnapshot.child("HD_Probability").child("probability_of_occ").getValue().toString()) + 1;
                                    hd_prob.child("probability_of_occ").setValue(probability_of_occ);
                                    int total_number = Integer.parseInt(dataSnapshot.child("HD_Probability").child("total_number").getValue().toString()) + 1;
                                    hd_prob.child("total_number").setValue(total_number);

                                } else if (diagnosis.equals("0")) {
                                    for (int count = 0; count < row1.length; count++) {
                                        String[] temp_string = row1[count].split(":");
                                        if (temp_string[0].equals("User_Age")) {
                                            int sum_of_Var = Integer.parseInt(dataSnapshot.child("NO_HD_ND").child("Age_NO_HD").child("sum_of_Var").getValue().toString()) + Integer.parseInt(temp_string[1]);
                                            ageRef_NO_HD.child("sum_of_Var").setValue(sum_of_Var);
                                            int sum_of_Var_squared = Integer.parseInt(dataSnapshot.child("NO_HD_ND").child("Age_NO_HD").child("sum_of_Var").getValue().toString()) + Integer.parseInt(temp_string[1]);
                                            ageRef_NO_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                        } else if (temp_string[0].equals("User_Sex")) {
                                            if (temp_string[1].equals("Male")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Sex_NO_HD").child("Male").child("probability_of_occ").getValue().toString()) + 1;
                                                sex_HD_male_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Sex_NO_HD").child("Male").child("total_number").getValue().toString()) + 1;
                                                sex_HD_male_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("Female")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Sex_NO_HD").child("Female").child("probability_of_occ").getValue().toString()) + 1;
                                                sex_HD_female_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Sex_NO_HD").child("Female").child("total_number").getValue().toString()) + 1;
                                                sex_HD_female_NO_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_Resting_BP")) {
                                            int sum_of_Var = Integer.parseInt(dataSnapshot.child("NO_HD_ND").child("Rest_BP_Level_NO_HD_ND").child("sum_of_Var").getValue().toString()) + Integer.parseInt(temp_string[1]);
                                            rbp_Level_NO_HD.child("sum_of_Var").setValue(sum_of_Var);
                                            int sum_of_Var_squared = Integer.parseInt(dataSnapshot.child("NO_HD_ND").child("Rest_BP_Level_NO_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Integer.parseInt(temp_string[1]) * Integer.parseInt(temp_string[1]));
                                            rbp_Level_NO_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                            int number_of_Var = Integer.parseInt(dataSnapshot.child("NO_HD_ND").child("Rest_BP_Level_NO_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                            rbp_Level_NO_HD.child("number_of_Var").setValue(number_of_Var);
                                        } else if (temp_string[0].equals("User_Cholestrol")) {
                                            int sum_of_Var = Integer.parseInt(dataSnapshot.child("NO_HD_ND").child("Cholestoral_NO_HD_ND").child("sum_of_Var").getValue().toString()) + Integer.parseInt(temp_string[1]);
                                            s_Cholestoral_NO_HD.child("sum_of_Var").setValue(sum_of_Var);
                                            int sum_of_Var_squared = Integer.parseInt(dataSnapshot.child("NO_HD_ND").child("Cholestoral_NO_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Integer.parseInt(temp_string[1]) * Integer.parseInt(temp_string[1]));
                                            s_Cholestoral_NO_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                            int number_of_Var = Integer.parseInt(dataSnapshot.child("NO_HD_ND").child("Cholestoral_NO_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                            s_Cholestoral_NO_HD.child("number_of_Var").setValue(number_of_Var);
                                        } else if (temp_string[0].equals("User_Max_HR")) {
                                            int sum_of_Var = Integer.parseInt(dataSnapshot.child("NO_HD_ND").child("Max_HeartRate_NO_HD_ND").child("sum_of_Var").getValue().toString()) + Integer.parseInt(temp_string[1]);
                                            max_HR_NO_HD.child("sum_of_Var").setValue(sum_of_Var);
                                            int sum_of_Var_squared = Integer.parseInt(dataSnapshot.child("NO_HD_ND").child("Max_HeartRate_NO_HD_ND").child("sum_of_Var_squared").getValue().toString()) + (Integer.parseInt(temp_string[1]) * Integer.parseInt(temp_string[1]));
                                            max_HR_NO_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                            int number_of_Var = Integer.parseInt(dataSnapshot.child("NO_HD_ND").child("Max_HeartRate_NO_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                            max_HR_NO_HD.child("number_of_Var").setValue(number_of_Var);
                                        } else if (temp_string[0].equals("User_Thalas")) {
                                            if (temp_string[1].equals("Three")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Thal_NO_HD").child("Three").child("probability_of_occ").getValue().toString()) + 1;
                                                thal_HD_three_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Thal_NO_HD").child("Three").child("total_number").getValue().toString()) + 1;
                                                thal_HD_three_NO_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("Six")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Thal_NO_HD").child("Six").child("probability_of_occ").getValue().toString()) + 1;
                                                thal_HD_six_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Thal_NO_HD").child("Six").child("total_number").getValue().toString()) + 1;
                                                thal_HD_six_NO_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("Seven")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Thal_NO_HD").child("Seven").child("probability_of_occ").getValue().toString()) + 1;
                                                thal_HD_seven_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Thal_NO_HD").child("Seven").child("total_number").getValue().toString()) + 1;
                                                thal_HD_seven_NO_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_CA")) {
                                            if (temp_string[1].equals("Zero")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Zero").child("probability_of_occ").getValue().toString()) + 1;
                                                ca_HD_zero_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Zero").child("total_number").getValue().toString()) + 1;
                                                ca_HD_zero_NO_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[0].equals("One")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                                ca_HD_one_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("One").child("total_number").getValue().toString()) + 1;
                                                ca_HD_one_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[0].equals("Two")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Two").child("probability_of_occ").getValue().toString()) + 1;
                                                ca_HD_two_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Two").child("total_number").getValue().toString()) + 1;
                                                ca_HD_two_NO_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[0].equals("Three")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Three").child("probability_of_occ").getValue().toString()) + 1;
                                                ca_HD_three_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CA_NO_HD").child("Three").child("total_number").getValue().toString()) + 1;
                                                ca_HD_three_NO_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_CPT")) {
                                            //row[2] = temp_string[0];
                                            if (temp_string[1].equals("L1")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L1").child("probability_of_occ").getValue().toString()) + 1;
                                                cpt_L1_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L1").child("total_number").getValue().toString()) + 1;
                                                cpt_L1_NO_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("L2")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L2").child("probability_of_occ").getValue().toString()) + 1;
                                                cpt_L2_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L2").child("total_number").getValue().toString()) + 1;
                                                cpt_L2_NO_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("L3")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L3").child("probability_of_occ").getValue().toString()) + 1;
                                                cpt_L3_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L3").child("total_number").getValue().toString()) + 1;
                                                cpt_L3_NO_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("L4")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L4").child("probability_of_occ").getValue().toString()) + 1;
                                                cpt_L4_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("CPT_NO_HD").child("L4").child("total_number").getValue().toString()) + 1;
                                                cpt_L4_NO_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_EIA")) {
                                            //row[8] = temp_string[0];
                                            if (temp_string[1].equals("Zero")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Exercise_Induced_Angina_NO_HD").child("Zero").child("probability_of_occ").getValue().toString()) + 1;
                                                eia_HD_Zero_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Exercise_Induced_Angina_NO_HD").child("Zero").child("total_number").getValue().toString()) + 1;
                                                eia_HD_Zero_NO_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("One")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Exercise_Induced_Angina_NO_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                                eia_HD_One_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Exercise_Induced_Angina_NO_HD").child("One").child("total_number").getValue().toString()) + 1;
                                                eia_HD_One_NO_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_FBS")) {
                                            //row[5] = temp_string[0];
                                            if (Double.parseDouble(temp_string[1]) <= 120) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Fasting_Blood_Sugar_NO_HD").child("False").child("probability_of_occ").getValue().toString()) + 1;
                                                fasting_BS_Level_False_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Fasting_Blood_Sugar_NO_HD").child("False").child("total_number").getValue().toString()) + 1;
                                                fasting_BS_Level_False_NO_HD.child("total_number").setValue(total_number);
                                            } else if (Double.parseDouble(temp_string[1]) > 120) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Fasting_Blood_Sugar_NO_HD").child("True").child("probability_of_occ").getValue().toString()) + 1;
                                                fasting_BS_Level_True_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Fasting_Blood_Sugar_NO_HD").child("True").child("total_number").getValue().toString()) + 1;
                                                fasting_BS_Level_True_NO_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_Rest_EKG")) {
                                            //row[6] = temp_string[0];
                                            if (temp_string[1].equals("Zero")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("Zero").child("probability_of_occ").getValue().toString()) + 1;
                                                rest_ecg_Zero_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("Zero").child("total_number").getValue().toString()) + 1;
                                                rest_ecg_Zero_NO_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("One")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                                rest_ecg_One_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("One").child("total_number").getValue().toString()) + 1;
                                                rest_ecg_One_NO_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("Two")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("Two").child("probability_of_occ").getValue().toString()) + 1;
                                                rest_ecg_Two_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Resting_ECG_NO_HD").child("Two").child("total_number").getValue().toString()) + 1;
                                                rest_ecg_Two_NO_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_Slope")) {
                                            //row[10] = temp_string[0];
                                            if (temp_string[1].equals("One")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("One").child("probability_of_occ").getValue().toString()) + 1;
                                                slope_HD_one_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("One").child("total_number").getValue().toString()) + 1;
                                                slope_HD_one_NO_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("Two")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("Two").child("probability_of_occ").getValue().toString()) + 1;
                                                slope_HD_two_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("Two").child("total_number").getValue().toString()) + 1;
                                                slope_HD_two_NO_HD.child("total_number").setValue(total_number);
                                            } else if (temp_string[1].equals("Three")) {
                                                int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("Three").child("probability_of_occ").getValue().toString()) + 1;
                                                slope_HD_three_NO_HD.child("probability_of_occ").setValue(probability_of_occ);
                                                int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Slope_NO_HD").child("Three").child("total_number").getValue().toString()) + 1;
                                                slope_HD_three_NO_HD.child("total_number").setValue(total_number);
                                            }
                                        } else if (temp_string[0].equals("User_Oldpeak")) {
                                            int sum_of_Var = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Oldpeak_NO_HD_ND").child("sum_of_Var").getValue().toString()) + Integer.parseInt(temp_string[1]);
                                            oldpeak_ND_NO_HD.child("sum_of_Var").setValue(sum_of_Var);
                                            int sum_of_Var_squared = Integer.parseInt(dataSnapshot.child("NO_HD_Bin").child("Oldpeak_NO_HD_ND").child("sum_of_Var").getValue().toString()) + Integer.parseInt(temp_string[1]);
                                            oldpeak_ND_NO_HD.child("sum_of_Var_squared").setValue(sum_of_Var_squared);
                                            int number_of_Var = Integer.parseInt(dataSnapshot.child("NO_HD_ND").child("Oldpeak_NO_HD_ND").child("number_of_Var").getValue().toString()) + 1;
                                            oldpeak_ND_NO_HD.child("number_of_Var").setValue(number_of_Var);
                                        }
                                    }

                                    int probability_of_occ = Integer.parseInt(dataSnapshot.child("NO_HD_Probability").child("probability_of_occ").getValue().toString()) + 1;
                                    no_hd_prob.child("probability_of_occ").setValue(probability_of_occ);
                                    int total_number = Integer.parseInt(dataSnapshot.child("NO_HD_Probability").child("total_number").getValue().toString()) + 1;
                                    no_hd_prob.child("total_number").setValue(total_number);


                                }

                                Toast.makeText(Review_Final_Add.this,"Thank you for your help!",Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(Review_Final_Add.this,Information_Hub.class);
                                i.putExtra("uID",uID);
                                startActivity(i);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {


                            }
                        });

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
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


