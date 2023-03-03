package com.udacity.sandwichclub.ui;

import androidx.appcompat.app.AppCompatActivity;

public class SandwichPagerActivity extends AppCompatActivity {
    // /* Class Constants */
    // private static final String EXTRA_POSITION = "indexPosition";
    // private static final int DEFAULT_POSITION = -1;
    // private static final String TAG = SandwichPagerActivity.class.getSimpleName();
    //
    // /* Class variables */
    // @BindView(R.id.sandwich_pager)
    // ViewPager viewPager;
    //
    // public static Intent newIntent(Context packageContext, int position) {
    //     Intent intent = new Intent(packageContext, SandwichPagerActivity.class);
    //     intent.putExtra(EXTRA_POSITION, position);
    //     return intent;
    // }
    //
    // @Override
    // protected void onCreate(@Nullable Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     setContentView(R.layout.activity_sandwich_pager);
    //     ButterKnife.bind(this);
    //
    //     Intent intent = getIntent();
    //     if (intent == null) {
    //         closeOnError();
    //     }
    //
    //     // Intent is already checked for null, so suppress null pointer exception warning
    //     @SuppressWarnings("ConstantConditions")
    //     int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
    //     if (position == DEFAULT_POSITION) {
    //         // EXTRA_POSITION not found in intent
    //         closeOnError();
    //         return;
    //     }
    //
    //     final String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
    //     FragmentManager fragmentManager = getSupportFragmentManager();
    //
    //     viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
    //         @Override
    //         public Fragment getItem(int position) {
    //             Sandwich sandwich = JsonUtils.parseSandwichJson(sandwiches[position]);
    //             if (sandwich == null) {
    //                 throw new IllegalArgumentException("Did not receive sandwich object from parser");
    //             }
    //             return SandwichFragment.newInstance(sandwich);
    //         }
    //
    //         @Override
    //         public int getCount() {
    //             return sandwiches.length;
    //         }
    //     });
    //
    //     viewPager.setCurrentItem(position);
    //     Sandwich sandwich = JsonUtils.parseSandwichJson(sandwiches[position]);
    //     setMainNameAsTitle(position, sandwich);
    //
    //     // Set title on page change
    //     viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    //         @Override
    //         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    //             /* Do nothing */
    //         }
    //
    //         @Override
    //         public void onPageSelected(int position) {
    //             Sandwich currentSandwich = JsonUtils.parseSandwichJson(sandwiches[position]);
    //             setMainNameAsTitle(position, currentSandwich);
    //         }
    //
    //         @Override
    //         public void onPageScrollStateChanged(int state) {
    //             /* Do nothing */
    //         }
    //     });
    // }
    //
    // private void setMainNameAsTitle(int position, Sandwich sandwich) {
    //     if (sandwich != null) {
    //         setTitle(sandwich.getMainName());
    //     } else {
    //         Log.e(TAG, "Sandwich at position " + position + " encountered JSON parse error!");
    //     }
    // }
    //
    // private void closeOnError() {
    //     finish();
    //     Toast.makeText(
    //             this,
    //             R.string.detail_error_message,
    //             Toast.LENGTH_SHORT
    //     ).show();
    // }
}
