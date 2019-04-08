package com.example.homie.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.homie.fragmentAdapter.ViewPagerAdapter;
import com.example.homie.R;

public class DummyActivityTestFragmentsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy_fragments);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Add fragments

        adapter.AddFragment(new FragmentDevices(), "Devices");
        adapter.AddFragment(new FragmentDetailedView(), "Detailed view");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);





    }
}
