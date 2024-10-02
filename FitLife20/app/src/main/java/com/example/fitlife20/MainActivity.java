package com.example.fitlife20;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        toggle = new  ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.syncState();

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       //Set navigation view listener
       navigationView.setNavigationItemSelectedListener ( new NavigationView.OnNavigationItemSelectedListener()
       {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               Log.d("MainActivity","Navigation Item Selected"+ item.getItemId());
              return setDrawerItem(item);
           }

       });

       //Load Default Fragment
        if (savedInstanceState == null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new DietaryPlannerFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_dietary_planner);
        }
    }


    private boolean setDrawerItem(MenuItem menuItem){
        Fragment fragment= null;

        switch (menuItem.getItemId()){
            case R.id.nav_dietary_planner:
                fragment = new DietaryPlannerFragment();
                break;
                //Handle other menu items here
            default:
                fragment = new DietaryPlannerFragment();
        }
        if (fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

}