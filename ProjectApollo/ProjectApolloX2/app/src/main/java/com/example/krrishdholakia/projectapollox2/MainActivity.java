package com.example.krrishdholakia.projectapollox2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.util.DoubleArray;

import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static NormalDistribution d;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseReference myFirebaseRef = FirebaseDatabase.getInstance().getReference();

        /*d = new NormalDistribution(1000, 100);
        System.out.println(d.cumulativeProbability(1100,1200));*/


        myFirebaseRef.child("UserTrial").push().setValue("3/12/2016");


        final List Diagnosis = new LinkedList();
        final List Age = new LinkedList();
        final List Sex = new LinkedList();
        final List Chest_Pain_Type = new LinkedList();
        final List Resting_Blood_Pressure = new LinkedList();
        final List Serum_Cholestoral = new LinkedList();
        final List Fasting_Blood_Sugar = new LinkedList();
        final List Resting_Electrocardiographic_Results = new LinkedList();
        final List Maximum_Heart_Rate_Achieved = new LinkedList();
        final List Exercise_Induced_Angina = new LinkedList();
        final List OldPeak = new LinkedList();
        final List Slope = new LinkedList();
        final List CA = new LinkedList();
        final List THAL = new LinkedList();
        final List DIAGNOSIS = new LinkedList();


        myFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Double Age_1 = (Double) dataSnapshot.child("Age").getValue();
                Double Sex_1 = (Double) dataSnapshot.child("Sex").getValue();
                Double Chest_Pain_Type_1 = (Double) dataSnapshot.child("Age").getValue();
                Double Resting_Blood_Sugar_1 = (Double) dataSnapshot.child("Age").getValue();
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

                Age.add(Age_1);
                Sex.add(Sex_1);
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

        DescriptiveStatistics dr = new DescriptiveStatistics();


    }
}
