package com.udacity.sandwichclub.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SandwichPagerActivity extends AppCompatActivity {
    /* Class Constants */
    private static final String EXTRA_POSITION = "indexPosition";

    /* Class variables */
    @BindView(R.id.sandwich_pager)
    ViewPager viewPager;

    public static Intent newIntent(Context packageContext, int position) {
        Intent intent = new Intent(packageContext, SandwichPagerActivity.class);
        intent.putExtra(EXTRA_POSITION, position);
        return intent;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich_pager);
        ButterKnife.bind(this);

        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        final String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        FragmentManager fragmentManager = getSupportFragmentManager();

        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Sandwich sandwich = JsonUtils.parseSandwichJson(sandwiches[position]);
                return SandwichFragment.newInstance(sandwich);
            }

            @Override
            public int getCount() {
                return sandwiches.length;
            }
        });

        viewPager.setCurrentItem(position);
    }
}
