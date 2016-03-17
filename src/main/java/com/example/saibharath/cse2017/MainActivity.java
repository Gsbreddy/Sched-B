package com.example.saibharath.cse2017;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends ActionBarActivity {
  private EditText editTextName;
    private TextView tv;
    //String[] ttable;
    String sname,stime;
    Spinner snames,stimes;
    private Button send,receive;


    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;

    private InterstitialAd interstitialAd;


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    String JSON_SERVER="http://cse2017.96.lt/cse/insert.php";
    String JSON_CLIENT="http://cse2017.96.lt/csecli/receive_row.php";

            @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
                setContentView(R.layout.activity_main);



//                getSupportActionBar().setDisplayUseLogoEnabled(true);

             //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);


                //Subjectdb subdb = new Subjectdb(this);



                toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                viewPager = (ViewPager) findViewById(R.id.viewpager);
                setupViewPager(viewPager);

                tabLayout = (TabLayout) findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(viewPager);




       }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TimeTableFragment(), "Schedule");
        adapter.addFragment(new ChangettFragment(), "Update Schedule");
        adapter.addFragment(new BunkFragment(), "Bunk Record");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }







}