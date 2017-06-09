package com.example.krrishdholakia.projectapollox2v4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tESTING#MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream inputStream = getResources().openRawResource(R.raw.cleveland_data_apollo);

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

        List resultList = new ArrayList();

        final List<Double> Age_HD = new LinkedList<>();
        final List<Double> Age_N_HD = new LinkedList<>();
        final List<Double> Sex_HD = new LinkedList<>();
        final List<Double> Sex_N_HD = new LinkedList<>();
        final List<Double> Chest_Pain_Type_HD = new LinkedList<>();
        final List<Double> Chest_Pain_Type_N_HD = new LinkedList<>();
        final List<Double> Resting_Blood_Pressure_HD = new LinkedList<>();
        final List<Double> Resting_Blood_Pressure_N_HD = new LinkedList<>();
        final List<Double> Serum_Cholestoral_HD = new LinkedList<>();
        final List<Double> Serum_Cholestoral_N_HD = new LinkedList<>();
        final List<Double> Fasting_Blood_Sugar_HD = new LinkedList<>();
        final List<Double> Fasting_Blood_Sugar_N_HD = new LinkedList<>();
        final List<Double> Resting_Electrocardiographic_Results_HD = new LinkedList<>();
        final List<Double> Resting_Electrocardiographic_Results_N_HD = new LinkedList<>();
        final List<Double> Maximum_Heart_Rate_Achieved_HD = new LinkedList<>();
        final List<Double> Maximum_Heart_Rate_Achieved_N_HD = new LinkedList<>();
        final List<Double> Exercise_Induced_Angina_HD = new LinkedList<>();
        final List<Double> Exercise_Induced_Angina_N_HD = new LinkedList<>();
        final List<Double> OldPeak_HD = new LinkedList<>();
        final List<Double> OldPeak_N_HD = new LinkedList<>();
        final List<Double> Slope_HD = new LinkedList<>();
        final List<Double> Slope_N_HD = new LinkedList<>();
        final List<Double> CA_HD = new LinkedList<>();
        final List<Double> CA_N_HD = new LinkedList<>();
        final List<Double> THAL_HD = new LinkedList<>();
        final List<Double> THAL_N_HD = new LinkedList<>();
        final List<Double> Diagnosis_HD = new LinkedList<>();
        final List<Double> Diagnosis_N_HD = new LinkedList<>();
        final List<Double> Diagnosis = new LinkedList<>();

        final List<Double> Sex_Male_HD = new LinkedList<>();
        final List<Double> Sex_Female_HD = new LinkedList<>();
        final List<Double> CPT_L1_HD = new LinkedList<>();
        final List<Double> CPT_L2_HD = new LinkedList<>();
        final List<Double> CPT_L3_HD = new LinkedList<>();
        final List<Double> CPT_L4_HD = new LinkedList<>();
        final List<Double> rest_ECG_Zero_HD = new LinkedList<>();
        final List<Double> rest_ECG_One_HD = new LinkedList<>();
        final List<Double> rest_ECG_Two_HD = new LinkedList<>();
        final List<Double> Exercise_Induced_Angina_Yes_HD = new LinkedList<>();
        final List<Double> Exercise_Induced_Angina_No_HD = new LinkedList<>();
        final List<Double> Slope_L1_HD = new LinkedList<>();
        final List<Double> Slope_L2_HD = new LinkedList<>();
        final List<Double> Slope_L3_HD = new LinkedList<>();
        final List<Double> CA_Zero_HD = new LinkedList<>();
        final List<Double> CA_One_HD = new LinkedList<>();
        final List<Double> CA_Two_HD = new LinkedList<>();
        final List<Double> CA_Three_HD = new LinkedList<>();
        final List<Double> Thal_Three_HD = new LinkedList<>();
        final List<Double> Thal_Six_HD = new LinkedList<>();
        final List<Double> Thal_Seven_HD = new LinkedList<>();
        final List<Double> Fasting_Blood_Sugar_Level_True_HD = new LinkedList<>();
        final List<Double> Fasting_Blood_Sugar_Level_False_HD = new LinkedList<>();

        final List<Double> Sex_Male_NO_HD = new LinkedList<>();
        final List<Double> Sex_Female_NO_HD = new LinkedList<>();
        final List<Double> CPT_L1_NO_HD = new LinkedList<>();
        final List<Double> CPT_L2_NO_HD = new LinkedList<>();
        final List<Double> CPT_L3_NO_HD = new LinkedList<>();
        final List<Double> CPT_L4_NO_HD = new LinkedList<>();
        final List<Double> rest_ECG_Zero_NO_HD = new LinkedList<>();
        final List<Double> rest_ECG_One_NO_HD = new LinkedList<>();
        final List<Double> rest_ECG_Two_NO_HD = new LinkedList<>();
        final List<Double> Exercise_Induced_Angina_Yes_NO_HD = new LinkedList<>();
        final List<Double> Exercise_Induced_Angina_No_NO_HD = new LinkedList<>();
        final List<Double> Slope_L1_NO_HD = new LinkedList<>();
        final List<Double> Slope_L2_NO_HD = new LinkedList<>();
        final List<Double> Slope_L3_NO_HD = new LinkedList<>();
        final List<Double> CA_Zero_NO_HD = new LinkedList<>();
        final List<Double> CA_One_NO_HD = new LinkedList<>();
        final List<Double> CA_Two_NO_HD = new LinkedList<>();
        final List<Double> CA_Three_NO_HD = new LinkedList<>();
        final List<Double> Thal_Three_NO_HD = new LinkedList<>();
        final List<Double> Thal_Six_NO_HD = new LinkedList<>();
        final List<Double> Thal_Seven_NO_HD = new LinkedList<>();
        final List<Double> Fasting_Blood_Sugar_Level_True_NO_HD = new LinkedList<>();
        final List<Double> Fasting_Blood_Sugar_Level_False_NO_HD = new LinkedList<>();


        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {

                String[] row = csvLine.split(",");

                int value = (Integer.parseInt(row[0].replaceAll("[^0-9]", ""))) / 10;
                int value2 = (Integer.parseInt(row[13].replaceAll("[^0-9]", "")));

                Patient patient_age = new Patient(""+value,row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10],row[11],row[12],""+value2);
                databaseRef.child("Patients").push().setValue(patient_age);


                Diagnosis.add((double) value2);

                if(value2 == 0) //diagnosis was not HD
                {

                    Diagnosis_N_HD.add((double)value2);
                    Age_N_HD.add((double) value);
                    Sex_N_HD.add(Double.parseDouble(row[1]));
                    Chest_Pain_Type_N_HD.add(Double.parseDouble(row[2]));
                    Resting_Blood_Pressure_N_HD.add(Double.parseDouble(row[3]));
                    Serum_Cholestoral_N_HD.add(Double.parseDouble(row[4]));
                    Fasting_Blood_Sugar_N_HD.add(Double.parseDouble(row[5]));
                    Resting_Electrocardiographic_Results_N_HD.add(Double.parseDouble(row[6]));
                    Maximum_Heart_Rate_Achieved_N_HD.add(Double.parseDouble(row[7]));
                    Exercise_Induced_Angina_N_HD.add(Double.parseDouble(row[8]));
                    OldPeak_N_HD.add(Double.parseDouble(row[9]));
                    Slope_N_HD.add(Double.parseDouble(row[10]));
                    CA_N_HD.add(Double.parseDouble(row[11]));
                    THAL_N_HD.add(Double.parseDouble(row[12]));

                    if(Double.parseDouble(row[1]) == 0)
                    {
                        Sex_Female_NO_HD.add(Double.parseDouble(row[1]));
                    }
                    else if(Double.parseDouble(row[1]) == 1)
                    {
                        Sex_Male_NO_HD.add(Double.parseDouble(row[1]));
                    }

                    if(Double.parseDouble(row[2]) == 1)
                    {
                        CPT_L1_NO_HD.add(Double.parseDouble(row[2]));
                    }
                    else if(Double.parseDouble(row[2]) == 2)
                    {
                        CPT_L2_NO_HD.add(Double.parseDouble(row[2]));
                    }
                    else if(Double.parseDouble(row[2]) == 3)
                    {
                        CPT_L3_NO_HD.add(Double.parseDouble(row[2]));
                    }
                    else if(Double.parseDouble(row[2]) == 4)
                    {
                        CPT_L4_NO_HD.add(Double.parseDouble(row[2]));
                    }
                    if(Double.parseDouble(row[5]) == 0)
                    {
                        Fasting_Blood_Sugar_Level_False_NO_HD.add(Double.parseDouble(row[5]));
                    }
                    else if(Double.parseDouble(row[5]) == 1)
                    {
                        Fasting_Blood_Sugar_Level_True_NO_HD.add(Double.parseDouble(row[5]));
                    }
                    if(Double.parseDouble(row[6]) == 0)
                    {
                        rest_ECG_Zero_NO_HD.add(Double.parseDouble(row[6]));
                    }
                    else if(Double.parseDouble(row[6]) == 1)
                    {
                        rest_ECG_One_NO_HD.add(Double.parseDouble(row[6]));
                    }
                    else if(Double.parseDouble(row[6]) == 2)
                    {
                        rest_ECG_Two_NO_HD.add(Double.parseDouble(row[6]));
                    }
                    if(Double.parseDouble(row[8]) == 0)
                    {
                        Exercise_Induced_Angina_No_NO_HD.add(Double.parseDouble(row[8]));
                    }
                    else if(Double.parseDouble(row[8]) == 1)
                    {
                        Exercise_Induced_Angina_Yes_NO_HD.add(Double.parseDouble(row[8]));
                    }
                    if (Double.parseDouble(row[10]) == 1)
                    {
                        Slope_L1_NO_HD.add(Double.parseDouble(row[10]));
                    }
                    else if (Double.parseDouble(row[10]) == 2)
                    {
                        Slope_L2_NO_HD.add(Double.parseDouble(row[10]));
                    }
                    else if (Double.parseDouble(row[10]) == 3)
                    {
                        Slope_L3_NO_HD.add(Double.parseDouble(row[10]));
                    }
                    if (Double.parseDouble(row[11]) == 0)
                    {
                        CA_Zero_NO_HD.add(Double.parseDouble(row[11]));
                    }
                    else if (Double.parseDouble(row[11]) == 1)
                    {
                        CA_One_NO_HD.add(Double.parseDouble(row[11]));
                    }
                    else if (Double.parseDouble(row[11]) == 2)
                    {
                        CA_Two_NO_HD.add(Double.parseDouble(row[11]));
                    }
                    else if (Double.parseDouble(row[11]) == 3)
                    {
                        CA_Three_NO_HD.add(Double.parseDouble(row[11]));
                    }
                    if (Double.parseDouble(row[12]) == 3)
                    {
                        Thal_Three_NO_HD.add(Double.parseDouble(row[12]));
                    }
                    else if (Double.parseDouble(row[12]) == 6)
                    {
                        Thal_Six_NO_HD.add(Double.parseDouble(row[12]));
                    }
                    else if (Double.parseDouble(row[12]) == 7)
                    {
                        Thal_Seven_NO_HD.add(Double.parseDouble(row[12]));
                    }
                }

                else if(value2 == 1 ||  value2 == 2 || value2 == 3 || value2 == 4) // diagnosis was HD
                {
                    Diagnosis_HD.add((double) value2);
                    Age_HD.add((double) value);
                    Sex_HD.add(Double.parseDouble(row[1]));
                    Chest_Pain_Type_HD.add(Double.parseDouble(row[2]));
                    Resting_Blood_Pressure_HD.add(Double.parseDouble(row[3]));
                    Serum_Cholestoral_HD.add(Double.parseDouble(row[4]));
                    Fasting_Blood_Sugar_HD.add(Double.parseDouble(row[5]));
                    Resting_Electrocardiographic_Results_HD.add(Double.parseDouble(row[6]));
                    Maximum_Heart_Rate_Achieved_HD.add(Double.parseDouble(row[7]));
                    Exercise_Induced_Angina_HD.add(Double.parseDouble(row[8]));
                    OldPeak_HD.add(Double.parseDouble(row[9]));
                    Slope_HD.add(Double.parseDouble(row[10]));
                    CA_HD.add(Double.parseDouble(row[11]));
                    THAL_HD.add(Double.parseDouble(row[12]));
                    if(Double.parseDouble(row[1]) == 0)
                    {
                        Sex_Female_HD.add(Double.parseDouble(row[1]));
                    }
                    else if(Double.parseDouble(row[1]) == 1)
                    {
                        Sex_Male_HD.add(Double.parseDouble(row[1]));
                    }

                    if(Double.parseDouble(row[2]) == 1)
                    {
                        CPT_L1_HD.add(Double.parseDouble(row[2]));
                    }
                    else if(Double.parseDouble(row[2]) == 2)
                    {
                        CPT_L2_HD.add(Double.parseDouble(row[2]));
                    }
                    else if(Double.parseDouble(row[2]) == 3)
                    {
                        CPT_L3_HD.add(Double.parseDouble(row[2]));
                    }
                    else if(Double.parseDouble(row[2]) == 4)
                    {
                        CPT_L4_HD.add(Double.parseDouble(row[2]));
                    }
                    if(Double.parseDouble(row[5]) == 0)
                    {
                        Fasting_Blood_Sugar_Level_False_HD.add(Double.parseDouble(row[5]));
                    }
                    else if(Double.parseDouble(row[5]) == 1)
                    {
                        Fasting_Blood_Sugar_Level_True_HD.add(Double.parseDouble(row[5]));
                    }
                    if(Double.parseDouble(row[6]) == 0)
                    {
                        rest_ECG_Zero_HD.add(Double.parseDouble(row[6]));
                    }
                    else if(Double.parseDouble(row[6]) == 1)
                    {
                        rest_ECG_One_HD.add(Double.parseDouble(row[6]));
                    }
                    else if(Double.parseDouble(row[6]) == 2)
                    {
                        rest_ECG_Two_HD.add(Double.parseDouble(row[6]));
                    }
                    if(Double.parseDouble(row[8]) == 0)
                    {
                        Exercise_Induced_Angina_No_HD.add(Double.parseDouble(row[8]));
                    }
                    else if(Double.parseDouble(row[8]) == 1)
                    {
                        Exercise_Induced_Angina_Yes_HD.add(Double.parseDouble(row[8]));
                    }
                    if (Double.parseDouble(row[10]) == 1)
                    {
                        Slope_L1_HD.add(Double.parseDouble(row[10]));
                    }
                    else if (Double.parseDouble(row[10]) == 2)
                    {
                        Slope_L2_HD.add(Double.parseDouble(row[10]));
                    }
                    else if (Double.parseDouble(row[10]) == 3)
                    {
                        Slope_L3_HD.add(Double.parseDouble(row[10]));
                    }
                    if (Double.parseDouble(row[11]) == 0)
                    {
                        CA_Zero_HD.add(Double.parseDouble(row[11]));
                    }
                    else if (Double.parseDouble(row[11]) == 1)
                    {
                        CA_One_HD.add(Double.parseDouble(row[11]));
                    }
                    else if (Double.parseDouble(row[11]) == 2)
                    {
                        CA_Two_HD.add(Double.parseDouble(row[11]));
                    }
                    else if (Double.parseDouble(row[11]) == 3)
                    {
                        CA_Three_HD.add(Double.parseDouble(row[11]));
                    }
                    if (Double.parseDouble(row[12]) == 3)
                    {
                        Thal_Three_HD.add(Double.parseDouble(row[12]));
                    }
                    else if (Double.parseDouble(row[12]) == 6)
                    {
                        Thal_Six_HD.add(Double.parseDouble(row[12]));
                    }
                    else if (Double.parseDouble(row[12]) == 7)
                    {
                        Thal_Seven_HD.add(Double.parseDouble(row[12]));
                    }

                }
                //databaseRef.child("Patients").setValue(patient_sex);



                resultList.add(row);

                /*
                String age1;
    String sex1;
    String chest_pain_type_1;
    String resting_Blood_Pressure1;
    String serum_Cholestoral1;
    //_in_mg/dl
    String fasting_Blood_Sugar1;
    //>120mg/dl
    String resting_Electrocardiographic_Results1;
    //1=true;0=false
    String maximum_Heart_Rate_Achieved1;
    String exercise_Induced_Angina1;
    //1=yes,0=no
    String oldPeak1;
    //ST depression induced by exercise relative to rest
    String slope1;
     String ca1;
    String thal1;
    String diagnosis1;

                 */
                Log.i(TAG, "Count "+resultList.size());

            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }


            databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

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


        hd_prob.setValue(likelihood(Diagnosis_HD,Diagnosis));
        ageRef_HD.setValue(sums(Age_HD));
        rbp_Level_HD.setValue(sums(Resting_Blood_Pressure_HD));
        max_HR_HD.setValue(sums(Maximum_Heart_Rate_Achieved_HD));
        oldpeak_ND_HD.setValue(sums(OldPeak_HD));
        s_Cholestoral_HD.setValue(sums(Serum_Cholestoral_HD));

        sex_HD_male_HD.setValue(likelihood(Sex_Male_HD,Sex_HD));
        sex_HD_female_HD.setValue(likelihood(Sex_Female_HD,Sex_HD));
        cpt_L1_HD.setValue(likelihood(CPT_L1_HD,Chest_Pain_Type_HD));
        cpt_L2_HD.setValue(likelihood(CPT_L2_HD,Chest_Pain_Type_HD));
        cpt_L3_HD.setValue(likelihood(CPT_L3_HD,Chest_Pain_Type_HD));
        cpt_L4_HD.setValue(likelihood(CPT_L4_HD,Chest_Pain_Type_HD));
        fasting_BS_Level_True_HD.setValue(likelihood(Fasting_Blood_Sugar_Level_True_HD,Fasting_Blood_Sugar_HD));
        fasting_BS_Level_False_HD.setValue(likelihood(Fasting_Blood_Sugar_Level_False_HD,Fasting_Blood_Sugar_HD));
        rest_ecg_Zero_HD.setValue(likelihood(rest_ECG_Zero_HD,Resting_Blood_Pressure_HD));
        rest_ecg_One_HD.setValue(likelihood(rest_ECG_One_HD,Resting_Blood_Pressure_HD));
        rest_ecg_Two_HD.setValue(likelihood(rest_ECG_Two_HD,Resting_Blood_Pressure_HD));
        eia_HD_Zero_HD.setValue(likelihood(Exercise_Induced_Angina_No_HD,Exercise_Induced_Angina_HD));
        eia_HD_One_HD.setValue(likelihood(Exercise_Induced_Angina_Yes_HD,Exercise_Induced_Angina_HD));
        slope_HD_one_HD.setValue(likelihood(Slope_L1_HD,Slope_HD));
        slope_HD_two_HD.setValue(likelihood(Slope_L2_HD,Slope_HD));
        slope_HD_three_HD.setValue(likelihood(Slope_L3_HD,Slope_HD));
        ca_HD_zero_HD.setValue(likelihood(CA_Zero_HD,CA_HD));
        ca_HD_one_HD.setValue(likelihood(CA_One_HD,CA_HD));
        ca_HD_two_HD.setValue(likelihood(CA_Two_HD,CA_HD));
        ca_HD_three_HD.setValue(likelihood(CA_Three_HD,CA_HD));
        thal_HD_three_HD.setValue(likelihood(Thal_Three_HD,THAL_HD));
        thal_HD_six_HD.setValue(likelihood(Thal_Six_HD,THAL_HD));
        thal_HD_seven_HD.setValue(likelihood(Thal_Seven_HD,THAL_HD));


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

        no_hd_prob.setValue(likelihood(Diagnosis_N_HD,Diagnosis));
        ageRef_NO_HD.setValue(sums(Age_N_HD));
        rbp_Level_NO_HD.setValue(sums(Resting_Blood_Pressure_N_HD));
        max_HR_NO_HD.setValue(sums(Maximum_Heart_Rate_Achieved_N_HD));
        oldpeak_ND_NO_HD.setValue(sums(OldPeak_N_HD));
        s_Cholestoral_NO_HD.setValue(sums(Serum_Cholestoral_N_HD));

        sex_HD_male_NO_HD.setValue(likelihood(Sex_Male_NO_HD,Sex_N_HD));
        sex_HD_female_NO_HD.setValue(likelihood(Sex_Female_NO_HD,Sex_N_HD));
        cpt_L1_NO_HD.setValue(likelihood(CPT_L1_NO_HD,Chest_Pain_Type_N_HD));
        cpt_L2_NO_HD.setValue(likelihood(CPT_L2_NO_HD,Chest_Pain_Type_N_HD));
        cpt_L3_NO_HD.setValue(likelihood(CPT_L3_NO_HD,Chest_Pain_Type_N_HD));
        cpt_L4_NO_HD.setValue(likelihood(CPT_L4_NO_HD,Chest_Pain_Type_N_HD));
        fasting_BS_Level_True_NO_HD.setValue(likelihood(Fasting_Blood_Sugar_Level_True_NO_HD,Fasting_Blood_Sugar_N_HD));
        fasting_BS_Level_False_NO_HD.setValue(likelihood(Fasting_Blood_Sugar_Level_False_NO_HD,Fasting_Blood_Sugar_N_HD));
        rest_ecg_Zero_NO_HD.setValue(likelihood(rest_ECG_Zero_NO_HD,Resting_Blood_Pressure_N_HD));
        rest_ecg_One_NO_HD.setValue(likelihood(rest_ECG_One_NO_HD,Resting_Blood_Pressure_N_HD));
        rest_ecg_Two_NO_HD.setValue(likelihood(rest_ECG_Two_NO_HD,Resting_Blood_Pressure_N_HD));
        eia_HD_Zero_NO_HD.setValue(likelihood(Exercise_Induced_Angina_No_NO_HD,Exercise_Induced_Angina_N_HD));
        eia_HD_One_NO_HD.setValue(likelihood(Exercise_Induced_Angina_Yes_NO_HD,Exercise_Induced_Angina_N_HD));
        slope_HD_one_NO_HD.setValue(likelihood(Slope_L1_NO_HD,Slope_N_HD));
        slope_HD_two_NO_HD.setValue(likelihood(Slope_L2_NO_HD,Slope_N_HD));
        slope_HD_three_NO_HD.setValue(likelihood(Slope_L3_NO_HD,Slope_N_HD));
        ca_HD_zero_NO_HD.setValue(likelihood(CA_Zero_NO_HD,CA_N_HD));
        ca_HD_one_NO_HD.setValue(likelihood(CA_One_NO_HD,CA_N_HD));
        ca_HD_two_NO_HD.setValue(likelihood(CA_Two_NO_HD,CA_N_HD));
        ca_HD_three_NO_HD.setValue(likelihood(CA_Three_NO_HD,CA_N_HD));
        thal_HD_three_NO_HD.setValue(likelihood(Thal_Three_NO_HD,THAL_N_HD));
        thal_HD_six_NO_HD.setValue(likelihood(Thal_Six_NO_HD,THAL_N_HD));
        thal_HD_seven_NO_HD.setValue(likelihood(Thal_Seven_NO_HD,THAL_N_HD));

    }

    public Normal_Distributions sums(List<Double> Age2_HD) {

        double sum_of_Var = 0;
        double sum_of_Var_squared = 0;
        for(int count = 0; count < Age2_HD.size(); count++)
        {
            sum_of_Var = Age2_HD.get(count) + sum_of_Var;
            sum_of_Var_squared = (Age2_HD.get(count) * Age2_HD.get(count)) + sum_of_Var_squared;
        }

        Normal_Distributions temp_Distribution = new Normal_Distributions(sum_of_Var,sum_of_Var_squared,(double)Age2_HD.size());

        return temp_Distribution;
    }

    public Simple_Probability likelihood(List<Double> Temp1_HD, List<Double> Temp2_N_HD)
    {
        double total = Temp2_N_HD.size();

        double occ = Temp1_HD.size();

        Simple_Probability temp_probability = new Simple_Probability(occ,total);


        return  temp_probability;
    }



}
