// package com.udacity.sandwichclub.ui
//
// import android.content.Context
// import android.content.Intent
// import android.os.Bundle
// import android.util.Log
// import android.widget.Toast
// import androidx.appcompat.app.AppCompatActivity
// import androidx.fragment.app.FragmentStatePagerAdapter
// import androidx.viewpager.widget.ViewPager
// import com.udacity.sandwichclub.R
// import com.udacity.sandwichclub.databinding.ActivitySandwichPagerBinding
// import com.udacity.sandwichclub.model.Sandwich
//
// class SandwichPagerActivity : AppCompatActivity() {
//     /* Class Constants */
//     companion object {
//         const val SANDWICH_INDEX = "indexPosition"
//         val TAG = SandwichPagerActivity::class.simpleName
//
//         fun newIntent(packageContext: Context, position: Int): Intent {
//             val intent = Intent(packageContext, SandwichPagerActivity::class.java)
//             intent.putExtra(SANDWICH_INDEX, position)
//
//             return intent
//         }
//     }
//
//     private lateinit var binding: ActivitySandwichPagerBinding
//
//     // @BindView(R.id.sandwich_pager)
//     // ViewPager viewPager;
//
//     override fun onCreate(savedInstanceState: Bundle?) {
//         super.onCreate(savedInstanceState)
//         binding = ActivitySandwichPagerBinding.inflate(layoutInflater)
//         val view = binding.root
//         setContentView(view)
//
//         val viewPager = binding.sandwichPager
//
//         val position = intent?.extras?.getInt(SANDWICH_INDEX, -1).toString()
//         if (position == "-1") {
//             // EXTRA_POSITION not found in intent
//             closeOnError()
//             return
//         }
//
//         val sandwiches = resources.getStringArray(R.array.sandwich_details)
//         val fragmentManager = supportFragmentManager
//
//         viewPager.adapter = FragmentStatePagerAdapter(fragmentManager) {
//             @Override
//             public Fragment getItem(int position) {
//                 Sandwich sandwich = JsonUtils.parseSandwichJson(sandwiches[position]);
//                 if (sandwich == null) {
//                     throw new IllegalArgumentException("Did not receive sandwich object from parser");
//                 }
//                 return SandwichFragment.newInstance(sandwich);
//             }
//
//             @Override
//             public int getCount() {
//                 return sandwiches.length;
//             }
//         };
//
//         viewPager.setCurrentItem(position);
//         Sandwich sandwich = JsonUtils.parseSandwichJson(sandwiches[position]);
//         setMainNameAsTitle(position, sandwich);
//
//         // Set title on page change
//         viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//             @Override
//             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                 /* Do nothing */
//             }
//
//             @Override
//             public void onPageSelected(int position) {
//                 Sandwich currentSandwich = JsonUtils.parseSandwichJson(sandwiches[position]);
//                 setMainNameAsTitle(position, currentSandwich);
//             }
//
//             @Override
//             public void onPageScrollStateChanged(int state) {
//                 /* Do nothing */
//             }
//         });
//     }
//
//     private fun setMainNameAsTitle(position: Int, sandwich: Sandwich?) {
//         if (sandwich != null) {
//             title = sandwich.mainName
//         } else {
//             Log.e(TAG, "Sandwich at position $position encountered JSON parse error!")
//         }
//     }
//
//     private fun closeOnError() {
//         finish()
//         Toast.makeText(
//                 this,
//                 R.string.detail_error_message,
//                 Toast.LENGTH_SHORT
//         ).show()
//     }
// }
