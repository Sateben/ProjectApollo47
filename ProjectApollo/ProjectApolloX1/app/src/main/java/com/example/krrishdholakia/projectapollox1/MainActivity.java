package com.example.krrishdholakia.projectapollox1;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tESTING#MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        InputStream inputStream = getResources().openRawResource(R.raw.cleveland_data_apollo);

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

        List resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                /*
                NumberFormat nf = DecimalFormat.getInstance();
nf.setMaximumFractionDigits(0);
String str = nf.format(documentNumber);
                 */

              //  String[] row = csvLine.split(",");

                /*
                String[] row  = new String[row1.length];
                NumberFormat nf = DecimalFormat.getInstance();
                nf.setMaximumFractionDigits(0);
                Log.i(TAG, ""+ row1.length);
                for(int count = 0; count <= row1.length; count++)
                {
                    Log.i(TAG,"TEST1");
                    int temp_Var = Integer.parseInt(row1[count]);
                    Log.i(TAG,"TEST2");
                    row[count] = nf.format(temp_Var);
                    Log.i(TAG,"TEST3");
                    Log.i(TAG, ""+ row[count]);
                }
                */
              /*  int value = (Integer.parseInt(row[0].replaceAll("[^0-9]", ""))) / 10;
                int value2 = (Integer.parseInt(row[13].replaceAll("[^0-9]", "")));
                /*databaseRef.child("Age").push().setValue(value);
                databaseRef.child("Sex").push().setValue(row[1]);
                databaseRef.child("Chest_Pain_Type").push().setValue(row[2]);
                databaseRef.child("Resting_Blood_Pressure").push().setValue(row[3]);
                databaseRef.child("Serum_Cholestoral").push().setValue(row[4]);
                //_in_mg/dl
                databaseRef.child("Fasting_Blood_Sugar").push().setValue(row[5]);
                //>120mg/dl
                databaseRef.child("Resting_Electrocardiographic_Results").push().setValue(row[6]);
                //1=true;0=false
                databaseRef.child("Maximum_Heart_Rate_Achieved").push().setValue(row[7]);
                databaseRef.child("Exercise_Induced_Angina").push().setValue(row[8]);
                //1=yes,0=no
                databaseRef.child("OldPeak").push().setValue(row[9]);
                //ST depression induced by exercise relative to rest
                databaseRef.child("Slope").push().setValue(row[10]);
                /*
                the slope of the peak exercise ST segment
                -- Value 1: upsloping
                -- Value 2: flat
                -- Value 3: downsloping
                 */
                /*databaseRef.child("CA").push().setValue(row[11]);
                //number of major vessels (0-3) colored by flourosopy
                databaseRef.child("THAL").push().setValue(row[12]);
                //3 = normal; 6 = fixed defect; 7 = reversable defect

                databaseRef.child("DIAGNOSIS").push().setValue(value2);
                /*
                (angiographic disease status)
                -- Value 0: < 50% diameter narrowing
                -- Value 1: > 50% diameter narrowing


                //Age patient_sex = new Age(row[1]);
                //Age patient_chest_pain_type = new Age(row[2]);
*/
               /* Age patient_age = new Age(""+value,row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10],row[11],row[12],""+value2);
                databaseRef.child("Patients").push().setValue(patient_age);
                //databaseRef.child("Patients").push().setValue(patient_sex);


                ArrayAdapter arrayAdapter =

                Log.i(TAG, "Hello "+row[13]);

                resultList.add(row);

            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }


        }





      /*
        Log.i(TAG, "Testing " + 3);

        final List<Double> Diagnosis1 = new LinkedList();
        final List<Double> Age1 = new LinkedList();
        final List<Double> Sex1 = new LinkedList();
        final List<Double> Chest_Pain_Type1 = new LinkedList();
        final List<Double> Resting_Blood_Pressure1 = new LinkedList();
        final List<Double> Serum_Cholestoral1 = new LinkedList();
        final List<Double> Fasting_Blood_Sugar1 = new LinkedList();
        final List<Double> Resting_Electrocardiographic_Results1 = new LinkedList();
        final List<Double> Maximum_Heart_Rate_Achieved1 = new LinkedList();
        final List<Double> Exercise_Induced_Angina1 = new LinkedList();
        final List<Double> OldPeak1 = new LinkedList();
        final List<Double> Slope1 = new LinkedList();
        final List<Double> CA1 = new LinkedList();
        final List<Double> THAL1 = new LinkedList();

        Log.i(TAG, "Testing " + 6);

        String[] var_Name = new String[14];

        var_Name[0] = "Age";
        var_Name[1] = "Sex";
        var_Name[2] = "Chest_Pain_Type";
        var_Name[3] = "Resting_Blood_Pressure";
        var_Name[4] = "Serum_Cholestoral";
        var_Name[5] = "Fasting_Blood_Sugar";
        var_Name[6] = "Resting_Electrocardiographic_Results";
        var_Name[7] = "Maximum_Heart_Rate_Achieved";
        var_Name[8] = "Exercise_Induced_Angina";
        var_Name[9] = "Chest_Pain_Type";
        var_Name[10] = "OldPeak";
        var_Name[11] = "Slope";
        var_Name[12] = "CA";
        var_Name[13] = "THAL";



            DatabaseReference UserDatabase = FirebaseDatabase.getInstance().getReference().child("Patient");

            UserDatabase.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Double Age_1 = (Double) Double.parseDouble(dataSnapshot.child("Age").getValue().toString());
               Double Sex_1 = (Double) dataSnapshot.child("Sex").getValue();
                Double Chest_Pain_Type_1 = (Double) dataSnapshot.child("Chest_Pain_Type").getValue();
                Double Resting_Blood_Sugar_1 = (Double) dataSnapshot.child("Resting_Blood_Pressure").getValue();
                Double Serum_Cholestrol_1 = (Double) dataSnapshot.child("Age").getValue();
                Double Fasting_Blood_Sugar_1 = (Double) dataSnapshot.child("Age").getValue();
                Double Resting_Electrocardiographic_Results_1 = (Double) dataSnapshot.child("Age").getValue();
                Double Maximum_Heart_Rate_Achieved_1 = (Double) dataSnapshot.child("Age").getValue();
                Double Exercise_Induced_Angina_1 = (Double) dataSnapshot.child("Age").getValue();
                Double OldPeak_1 = (Double) dataSnapshot.child("Age").getValue();
                Double Slope_1 = (Double) dataSnapshot.child("Age").getValue();
                Double CA_1 = (Double) dataSnapshot.child("Age").getValue();
                Double THAL_1 = (Double) dataSnapshot.child("Age").getValue();
                Double DIAGNOSIS_1 = (Double) dataSnapshot.child("Age").getValue();

                Log.i(TAG, "TRIALLER " + dataSnapshot.getValue());


                Age1.add(Age_1);
                /*Sex.add(Sex_1);
                Chest_Pain_Type.add(Chest_Pain_Type_1);
                Resting_Blood_Pressure.add(Resting_Blood_Sugar_1);
                Serum_Cholestoral.add(Serum_Cholestrol_1);
                Fasting_Blood_Sugar.add(Fasting_Blood_Sugar_1);
                Resting_Electrocardiographic_Results.add(Resting_Electrocardiographic_Results_1);
                Maximum_Heart_Rate_Achieved.add(Maximum_Heart_Rate_Achieved_1);
                Exercise_Induced_Angina.add(Exercise_Induced_Angina_1);
                OldPeak.add(OldPeak_1);
                Slope.add(Slope_1);
                CA.add(CA_1);
                THAL.add(THAL_1);
                Diagnosis.add(DIAGNOSIS_1);

                Log.i(TAG, "Testing " +Age.get(0));
               }


                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
*/

        DescriptiveStatistics dr = new DescriptiveStatistics();

        d

            }

        }


