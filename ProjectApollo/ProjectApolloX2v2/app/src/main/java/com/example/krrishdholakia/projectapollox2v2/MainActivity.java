package com.example.krrishdholakia.projectapollox2v2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

public class MainActivity extends AppCompatActivity {

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

    EditText Age;
    private static final String TAG = "MyActivity";
    final List<Double> Age_HD = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Age = (EditText) findViewById(R.id.Age_Edt_Txt);

        final DatabaseReference myFirebaseRef = FirebaseDatabase.getInstance().getReference();
        final double[] sum_of_Var = {0};
        final double[] sum_of_Var_squared = {0};
        /*d = new NormalDistribution(1000, 100);
        System.out.println(d.cumulativeProbability(1100,1200));*/


        myFirebaseRef.child("UserTrial").push().setValue("3/12/2016");


        final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("Patients");

        Log.i(TAG, "TESTING34 "+Age_HD.size());


        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Age_HD_ND").push();

        databaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                double temp_diag = Double.parseDouble(dataSnapshot.child("diagnosis1").getValue().toString());

                if(temp_diag == 1 || temp_diag == 2 || temp_diag == 3)
                {
                    Double temp_Age = Double.parseDouble(dataSnapshot.child("age1").getValue().toString());
                    Double temp_rbp = Double.parseDouble(dataSnapshot.child("resting_Blood_Pressure1").getValue().toString());
                    Double temp_sc = Double.parseDouble(dataSnapshot.child("serum_Cholestoral1").getValue().toString());
                    Double temp_mhr = Double.parseDouble(dataSnapshot.child("maximum_Heart_Rate_Achieved1").getValue().toString());
                    Double temp_oldpeak = Double.parseDouble(dataSnapshot.child("oldPeak1").getValue().toString());

                    Age_HD.add(temp_Age);

                    Log.i(TAG, "AGE: "+Age_HD.size());
                    Resting_Blood_Pressure_HD.add(temp_rbp);

                    Serum_Cholestoral_HD.add(temp_sc);

                    Maximum_Heart_Rate_Achieved_HD.add(temp_mhr);

                    OldPeak_HD.add(temp_oldpeak);

                    Sex_HD.add((double) 1);

                    Chest_Pain_Type_HD.add((double) 1);

                    Fasting_Blood_Sugar_HD.add((double) 1);

                    Resting_Blood_Pressure_HD.add((double) 1);

                    Resting_Electrocardiographic_Results_HD.add((double) 1);

                    Exercise_Induced_Angina_HD.add((double) 1);

                    Slope_HD.add((double) 1);

                    CA_HD.add((double) 1);

                    THAL_HD.add((double) 1);



                }

                if(temp_diag == 0)
                {
                    Double temp_Age = Double.parseDouble(dataSnapshot.child("age1").getValue().toString());
                    Double temp_rbp = Double.parseDouble(dataSnapshot.child("resting_Blood_Pressure1").getValue().toString());
                    Double temp_sc = Double.parseDouble(dataSnapshot.child("serum_Cholestoral1").getValue().toString());
                    Double temp_mhr = Double.parseDouble(dataSnapshot.child("maximum_Heart_Rate_Achieved1").getValue().toString());
                    Double temp_oldpeak = Double.parseDouble(dataSnapshot.child("oldPeak1").getValue().toString());

                    Age_N_HD.add(temp_Age);

                    Resting_Blood_Pressure_N_HD.add(temp_rbp);

                    Serum_Cholestoral_N_HD.add(temp_sc);

                    Maximum_Heart_Rate_Achieved_N_HD.add(temp_mhr);

                    OldPeak_N_HD.add(temp_oldpeak);

                    Age_N_HD.add(temp_Age);

                    Resting_Blood_Pressure_N_HD.add(temp_rbp);

                    Serum_Cholestoral_N_HD.add(temp_sc);

                    Maximum_Heart_Rate_Achieved_N_HD.add(temp_mhr);

                    OldPeak_N_HD.add(temp_oldpeak);

                    Sex_N_HD.add((double) 0);

                    Chest_Pain_Type_N_HD.add((double) 0);

                    Fasting_Blood_Sugar_N_HD.add((double) 0);

                    Resting_Blood_Pressure_N_HD.add((double) 0);

                    Resting_Electrocardiographic_Results_N_HD.add((double) 0);

                    Exercise_Induced_Angina_N_HD.add((double) 0);

                    Slope_N_HD.add((double) 0);

                    CA_N_HD.add((double) 0);

                    THAL_N_HD.add((double) 0);



                }

                Log.i(TAG, "TESTING " + Age_HD.size());
                notify();
                /*for (int count = 0; count < Age_HD.size(); count++) {
                    Log.i(TAG, "ROCHESTER2 ");
                    sum_of_Var[0] = Age_HD.get(count) + sum_of_Var[0];
                    Log.i(TAG, "ROCHESTER3 ");
                    Log.i(TAG, "Sum_of_Var: " + sum_of_Var[0]);
                    dbRef.setValue(sum_of_Var[0]);
                    sum_of_Var_squared[0] = (Age_HD.get(count) * Age_HD.get(count)) + sum_of_Var_squared[0];



                }*/

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


    }


   /* public double variance(LinkedList<Double> Age2_HD) {

        double sum_of_Var = 0;
        double sum_of_Var_squared = 0;
        for(int count = 0; count <Age2_HD.size(); count++)
        {
                    sum_of_Var = Age2_HD.get(count) + sum_of_Var;
                    sum_of_Var_squared = (Age2_HD.get(count) * Age2_HD.get(count)) + sum_of_Var_squared;
        }
        double variance2 = sum_of_Var*sum_of_Var - sum_of_Var_squared;
        return variance2;
    }

    public long probability_of_Age2_HD_occurred(LinkedList<Double> Age2_HD){
        double var = variance(Age2_HD);
        double sum = 0;
        for(int count = 0; count < Age2_HD.size(); count++)
            sum = Age2_HD.get(count) + sum;
        double mean = sum/Age2_HD.size();
        NormalDistribution pr = new NormalDistribution(mean,Math.sqrt(var));
        String temp = Age.getText().toString();

    }

*/

}
