package com.example.krrishdholakia.projectapollox2v7;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;



import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class Navigation_Drawer extends AppCompatActivity {
    LinearLayout medical_card;
    ProgressBar temp_bar;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
    private Toolbar toolbar;
    private Drawer result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_check);




        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.BLACK);
        toolbar.getBackground().setAlpha(90);
        getSupportActionBar().setTitle(R.string.app_name);

        result = new DrawerBuilder().withActivity(this)
                .withFullscreen(true)
                .withToolbar(toolbar)
                .withTranslucentNavigationBar(true)
                .addDrawerItems(new PrimaryDrawerItem().withName("Home").withIcon(getResources().getDrawable(android.R.drawable.ic_menu_add)).withIdentifier(1),
                        new PrimaryDrawerItem().withName("Speak").withIcon(getResources().getDrawable(android.R.drawable.ic_btn_speak_now)),

                        //add some more items to get a scrolling list
                        new SectionDrawerItem().withName("Header..."), new SecondaryDrawerItem().withName("Settings").withIcon(getResources().getDrawable(android.R.drawable.ic_menu_call)))
                .withSavedInstance(savedInstanceState)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Log.i("You clicked", String.valueOf(position));
                        return false;
                    }
                })
                .build();

        //set the back arrow in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
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
}