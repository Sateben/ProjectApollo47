package com.example.krrishdholakia.projectapollox3;


import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.math.BigDecimal;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "tESTING#MainActivity";

    Double User_Age;
    String User_Sex;
    Double User_Resting_BP;
    Double User_Cholestrol;
    Double User_Max_HR;
    String User_Thalas;
    String User_FBS;
    Double User_Oldpeak;
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
    Spinner Oldpeak_Txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Age_Txt = (EditText) findViewById(R.id.Age_Edt_Txt);
        Sex_Txt = (EditText) findViewById(R.id.Sex_Edt_Txt);
        Resting_Bp_Txt = (EditText) findViewById(R.id.RestingBP_Edt_Txt);
        Cholestrol_Txt = (EditText) findViewById(R.id.Cholestrol_Edt_Txt);
        Max_HR_Txt = (EditText) findViewById(R.id.MaxHR_Edt_Txt);
        Thalas_Txt = (EditText) findViewById(R.id.Thalas_Edt_Txt);
*/

        Age_Txt = (EditText) findViewById(R.id.Age_Edt_Txt);
        User_Age = Double.parseDouble(Age_Txt.toString());

        Sex_Txt = (Spinner) findViewById(R.id.spinner_sex);

        User_Sex = Sex_Txt.toString();

        Resting_Bp_Txt = (EditText) findViewById(R.id.rest_bp_Edt_Txt);

        User_Resting_BP = Double.parseDouble(Resting_Bp_Txt.toString());

        Cholestrol_Txt = (EditText) findViewById(R.id.cholesterol_Edt_Txt);

        User_Cholestrol = Double.parseDouble(Cholestrol_Txt.toString());

        Max_HR_Txt = (EditText) findViewById(R.id.max_HR_Edt_Txt);

        Thalas_Txt = (Spinner) findViewById(R.id.spinner_thal);

        User_Thalas = Thalas_Txt.toString();

        CA_Txt = (Spinner) findViewById(R.id.spinner_ca);

        User_CA = CA_Txt.toString();

        CPT_Txt = (Spinner) findViewById(R.id.spinner_cpt);

        User_CPT = CPT_Txt.toString();

        EIA_Txt = (Spinner) findViewById(R.id.spinner_eia);

        User_EIA = EIA_Txt.toString();


        Spinner dropdown = (Spinner)findViewById(R.id.spinner_sex);
        String[] items = new String[]{"Male","Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Spinner dropdown2 = (Spinner)findViewById(R.id.spinner_thal);
        String[] items2 = new String[]{"Three","Six","Seven"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);

        Spinner dropdown3 = (Spinner)findViewById(R.id.spinner_ca);
        String[] items3 = new String[]{"Zero","One","Two","Three"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown3.setAdapter(adapter3);

        Spinner dropdown4 = (Spinner)findViewById(R.id.spinner_cpt);
        String[] items4 = new String[]{"L1","L2","L3","L4"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items4);
        dropdown4.setAdapter(adapter4);

        Spinner dropdown5 = (Spinner)findViewById(R.id.spinner_eia);
        String[] items5 = new String[]{"Zero","One"};
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items5);
        dropdown5.setAdapter(adapter5);

        EditText entered_Fast_BS = (EditText) findViewById(R.id.Fasting_BS_Edt_Txt);

        if(Double.parseDouble(entered_Fast_BS.toString()) > 120)
        {
            User_FBS = "True";
        }
        else if(Double.parseDouble(entered_Fast_BS.toString()) <= 120)
        {
            User_FBS = "False";
        }

        Spinner dropdown6 = (Spinner)findViewById(R.id.spinner_slope);
        String[] items6 = new String[]{"One","Two","Three"};
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items6);
        dropdown5.setAdapter(adapter6);

        EditText temp_oldpeak = (EditText) findViewById(R.id.oldpeak_Edt_Txt);

        User_Oldpeak = Double.parseDouble(temp_oldpeak.toString());

        Rest_EKG_Txt = (Spinner) findViewById(R.id.spinner_rest_ekg);

        User_Rest_EKG = Rest_EKG_Txt.toString();

        Slope_Txt = (Spinner) findViewById(R.id.spinner_slope);

        User_Slope = Slope_Txt.toString();


    }


}