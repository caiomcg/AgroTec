package com.caiomcg.es.Controllers;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.caiomcg.es.HomeFragment;
import com.caiomcg.es.Models.User;

import com.caiomcg.es.OptionsFragment;
import com.caiomcg.es.PostFragment;
import com.caiomcg.es.R;

public class RegionsNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OptionsFragment.OnFragmentInteractionListener,
        PostFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener  {
    public static String TAG = "RegionsNavigation";

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regions_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HomeFragment homeFragment = HomeFragment.newInstance("", "");
        getSupportFragmentManager().beginTransaction().replace(R.id.screen_area, homeFragment).commit();

        Bundle bundle = getIntent().getExtras();

        //TODO: Handle null pointers
        this.user = (User)bundle.getSerializable("User");
        Log.e(TAG, this.user.toString());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.regions_navigation, menu);

        //TODO: Add image replacement
        ((TextView)findViewById(R.id.nav_user_name)).setText(String.format("%s %s", this.user.firstName, this.user.lastName));
        ((TextView)findViewById(R.id.nav_user_email)).setText(this.user.email);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_home:
                HomeFragment homeFragment = HomeFragment.newInstance("", "");
                getSupportFragmentManager().beginTransaction().replace(R.id.screen_area, homeFragment).commit();
                break;
            case R.id.nav_mata:
                OptionsFragment optionsFragment = OptionsFragment.newInstance("", "");
                getSupportFragmentManager().beginTransaction().replace(R.id.screen_area, optionsFragment).commit();
                break;
            case R.id.nav_agreste:
                break;
            case R.id.nav_borborema:
                break;
            case R.id.nav_sertao:
                break;
            case R.id.nav_share:
                PostFragment postFragment = PostFragment.newInstance("", "");
                getSupportFragmentManager().beginTransaction().replace(R.id.screen_area, postFragment).commit();
                break;
            case R.id.nav_send:
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.e(TAG, uri.toString());
    }
}
