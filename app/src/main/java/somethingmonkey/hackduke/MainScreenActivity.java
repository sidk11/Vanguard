package somethingmonkey.hackduke;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout mDrawerLayout;
    FragmentManager mFragmentManager;
    Button calibrateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        setNavigationViewListner();

        mDrawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
//            actionbar.setHomeAsUpIndicator(R.drawable.bars_solid);
        }
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mDrawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));

        MainFragment fragment = new MainFragment();
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.content_view, fragment).commit();


        calibrateButton = findViewById(R.id.calibrate);
    }


    public void swicthToDate(View v){
        MyDataFragment fragment = new MyDataFragment();
        mFragmentManager.beginTransaction().replace(R.id.content_view, fragment).commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        switch (menuItem.getItemId()) {

            case R.id.nav_home: {
                //do somthing
                MainFragment fragment = new MainFragment();
                mFragmentManager.beginTransaction().replace(R.id.content_view, fragment).commit();
                break;
            }

            case R.id.nav_health:{
                Log.d("Pressed", "Pressed health");
                MyHealthFragment fragment = new MyHealthFragment();
                mFragmentManager.beginTransaction().replace(R.id.content_view, fragment).commit();
                break;
            }

//            case R.id.nav_task:{
//                Log.d("Pressed", "Pressed task");
//                TaskFragment fragment = new TaskFragment();
//                mFragmentManager.beginTransaction().replace(R.id.content_view, fragment).commit();
//                break;
//            }

            case R.id.nav_manage:{
                Log.d("Pressed", "Pressed my data");
                MyDataFragment fragment = new MyDataFragment();
                mFragmentManager.beginTransaction().replace(R.id.content_view, fragment).commit();
                break;
            }
        }
        //close navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListner() {
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

//    public void onClickHeader(View v){
//
//    }

}
