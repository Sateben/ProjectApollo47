package com.krrishdholakia.apollo.application.Medical_Information;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.krrishdholakia.apollo.application.Check.Check_Initial_Data;
import com.krrishdholakia.apollo.projectapollox2v11.R;
import com.krrishdholakia.apollo.application.Review.Review;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


import android.view.MenuItem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by krrishdholakia on 8/12/16.
 */
public class Medical_Information extends AppCompatActivity {
    private Toolbar toolbar;
    private Drawer result;
    private String uID = null;

    private static final String TAG = "#MedInfo";
    TextView Age_Txt;
    TextView Sex_Txt;
    TextView Resting_Bp_Txt;
    TextView Cholestrol_Txt;
    TextView Max_HR_Txt;
    TextView Thalas_Txt;
    TextView CA_Txt;
    TextView CPT_Txt;
    TextView EIA_Txt;
    TextView FBS_Txt;
    TextView Rest_EKG_Txt;
    TextView Slope_Txt;
    TextView Oldpeak_Txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_check);

         toolbar = (Toolbar) findViewById(R.id.toolbar);


        Bundle bundle = getIntent().getExtras();

        uID = bundle.getString("uID");

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withFullscreen(true)
                .withTranslucentNavigationBar(true)
                .addDrawerItems(new PrimaryDrawerItem()
                                .withIdentifier(1),
                        //add some more items to get a scrolling list
                        new SecondaryDrawerItem().withName("Edit Information")
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Intent i = new Intent(Medical_Information.this, Medical_Information_Enter.class);
                                        i.putExtra("uID", uID);
                                        startActivity(i);
                                        return false;
                                    }
                                }),
                        new SecondaryDrawerItem().withName("Check")
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        Log.i("You were here", String.valueOf(position));
                                        Intent i = new Intent(Medical_Information.this, Check_Initial_Data.class);
                                        i.putExtra("uID", uID);
                                        startActivity(i);
                                        return false;
                                    }
                                }),
                        new SecondaryDrawerItem()
                                .withName("Review"))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Log.i("You clicked", String.valueOf(position));
                        Intent i = new Intent(Medical_Information.this,Review.class);
                        i.putExtra("uID", uID);
                        startActivity(i);
                        return false;
                    }
                })
                .build();

        //set the back arrow in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);



        Rest_EKG_Txt = (TextView) findViewById(R.id.Rest_EKG_Edt_Txt);
        Rest_EKG_Txt.setText("0");
        Slope_Txt = (TextView) findViewById(R.id.Slope_Edt_Txt);
        Slope_Txt.setText("0");
        Age_Txt = (TextView) findViewById(R.id.Age_Edt_Txt);
        Age_Txt.setText("0");
        Sex_Txt = (TextView) findViewById(R.id.Sex_Edt_Txt);
        Sex_Txt.setText("0");
        Resting_Bp_Txt = (TextView) findViewById(R.id.rest_bp_Edt_Txt);
        Resting_Bp_Txt.setText("0");

        Cholestrol_Txt = (TextView) findViewById(R.id.cholesterol_Edt_Txt);
        Cholestrol_Txt.setText("0");

        Max_HR_Txt = (TextView) findViewById(R.id.max_HR_Edt_Txt);
        Max_HR_Txt.setText("0");

        Thalas_Txt = (TextView) findViewById(R.id.Thal_Edt_Txt);
        Thalas_Txt.setText("0");

        CA_Txt = (TextView) findViewById(R.id.CA_Edt_Txt);
        CA_Txt.setText("0");

        CPT_Txt = (TextView) findViewById(R.id.CPT_Edt_Txt);
        CPT_Txt.setText("0");

        EIA_Txt = (TextView) findViewById(R.id.EIA_Edt_Txt);
        EIA_Txt.setText("0");
        Oldpeak_Txt = (TextView) findViewById(R.id.oldpeak_Edt_Txt);
        Oldpeak_Txt.setText("0");

        FBS_Txt = (TextView) findViewById(R.id.Fasting_BS_Edt_Txt);
        FBS_Txt.setText("0");
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
                Log.i(TAG,"Test:" + readstring);
                s += readstring;
            }
            InputRead.close();


            String[] row = readstring.split(",");

            for(int count = 0; count < row.length; count++)
            {
                String[] temp_string = row[count].split(":");
                if(temp_string[0].equals("User_Age"))
                {
                    Age_Txt.setText(temp_string[1]);
                    Log.i(TAG,"trial: "+temp_string[1]);
                }
                else if(temp_string[0].equals("User_Sex"))
                {
                    Sex_Txt.setText(temp_string[1]);
                    Log.i(TAG,"trial: "+temp_string[1]);
                }
                else if(temp_string[0].equals("User_Resting_BP"))
                {
                    Resting_Bp_Txt.setText(temp_string[1]);
                    Log.i(TAG,"trial: "+temp_string[1]);
                }
                else if(temp_string[0].equals("User_Cholestrol"))
                {
                    Cholestrol_Txt.setText(temp_string[1]);
                    Log.i(TAG,"trial: "+temp_string[1]);
                }
                else if(temp_string[0].equals("User_Max_HR"))
                {
                    Max_HR_Txt.setText(temp_string[1]);
                    Log.i(TAG,"trial: "+temp_string[1]);
                }
                else if(temp_string[0].equals("User_Thalas"))
                {
                    Thalas_Txt.setText(temp_string[1]);
                    Log.i(TAG,"trial: "+temp_string[1]);
                }
                else if(temp_string[0].equals("User_CA"))
                {
                    CA_Txt.setText(temp_string[1]);
                    Log.i(TAG,"trial: "+temp_string[1]);
                }
                else if(temp_string[0].equals("User_CPT"))
                {
                    CPT_Txt.setText(temp_string[1]);
                    Log.i(TAG,"trial: "+temp_string[1]);
                }
                else if(temp_string[0].equals("User_EIA"))
                {
                    EIA_Txt.setText(temp_string[1]);
                    Log.i(TAG,"trial: "+temp_string[1]);
                }
                else if(temp_string[0].equals("User_FBS"))
                {
                    FBS_Txt.setText(temp_string[1]);
                    Log.i(TAG,"trial: "+temp_string[1]);
                }
                else if(temp_string[0].equals("User_Rest_EKG"))
                {
                    Rest_EKG_Txt.setText(temp_string[1]);
                    Log.i(TAG,"trial: "+temp_string[1]);
                }
                else if(temp_string[0].equals("User_Slope"))
                {
                    Slope_Txt.setText(temp_string[1]);
                    Log.i(TAG,"trial: "+temp_string[1]);
                }
                else if(temp_string[0].equals("User_Oldpeak"))
                {
                    Oldpeak_Txt.setText(temp_string[1]);
                    Log.i(TAG,"trial: "+temp_string[1]);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    /*
    public static void text_Setter(String[] row, TextView temp_Text)
    {
        for(int count = 0; count < row.length; count++)
        {
            String[] temp_string = row[count].split(":");
            if(temp_string[0].equals("" + temp_Text.))
            {

            }
        }
    }
    */
}