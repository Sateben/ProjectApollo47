package com.example.krrishdholakia.projectapollox2v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.krrishdholakia.projectapollox2v3.R.id.Edt_Txt;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    Firebase userRef;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        final List<Double> Age_HD = new LinkedList<>();


        userRef = new Firebase("https://projectapollox1.firebaseio.com/").child("Patients");

        final Firebase dataRef = new Firebase("https://projectapollox1.firebaseio.com/").child("Age_HD_ND").push();

        final Firebase dataRef2 = new Firebase("https://projectapollox1.firebaseio.com/").child("Age_HD_ND_2").push();

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG, "BLAKE "+dataSnapshot.getValue().toString());
                dataRef2.setValue(dataSnapshot.child("age1").getValue());

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        final int[] count = new int[1];
        count[0] = 0;

        userRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Double temp_diag = Double.parseDouble(dataSnapshot.child("diagnosis1").getValue().toString());

                if (temp_diag == 1 || temp_diag == 2 || temp_diag == 3) {
                    Double temp_Age = Double.parseDouble(dataSnapshot.child("age1").getValue().toString());
                    Age_HD.add(temp_Age);
                }

                double[] sums_of_Vars_2 = sums(Age_HD);
                    Ager sums_of_Vars = new Ager(sums_of_Vars_2[0], sums_of_Vars_2[1]);

                    dataRef.setValue(sums_of_Vars);

                count[0] = count[0] + 1;
                Log.i(TAG,"PARENT SIZE: " + count[0]);
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
            public void onCancelled(FirebaseError firebaseError) {

            }

        });

    }


    public static double[] sums(List<Double> Temp_List)
    {
        double sum_of_Var = 0;
        double sum_of_Var_squared = 0;
        for (int count = 0; count < Temp_List.size(); count++) {
            Log.i(TAG, "ROCHESTER2 ");
            sum_of_Var = Temp_List.get(count) + sum_of_Var;
            Log.i(TAG, "ROCHESTER3 ");
            Log.i(TAG, "Sum_of_Var: " + sum_of_Var);
            sum_of_Var_squared = (Temp_List.get(count) * Temp_List.get(count)) + sum_of_Var_squared;
        }

        double[] sums_of_Vars = new double[2];
        sums_of_Vars[0] = sum_of_Var;
        sums_of_Vars[1] = sum_of_Var_squared;


        return sums_of_Vars;
    }
}
