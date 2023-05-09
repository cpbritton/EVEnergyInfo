package com.brittonvehicles.evenergyinfo

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.brittonvehicles.evenergyinfo.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private val tabsArray = arrayOf( "Home", "Charging","Stats", "Info")

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

       TabLayoutMediator(tabLayout, viewPager) { tab, position ->
           when (position) {
               0 -> {
                   tab.text = tabsArray[position]
                   tab.setIcon( R.drawable.ic_tab0)
                   tab.icon?.setTint(Color.WHITE)
               }
               1 -> {
                   tab.text = tabsArray[position]
                   tab.setIcon( R.drawable.ic_tab1)
                   tab.icon?.setTint(Color.GRAY)
               }
               2 -> {
                   tab.text = tabsArray[position]
                   tab.setIcon( R.drawable.ic_tab2)
                   tab.icon?.setTint(Color.GRAY)
               }
               3 -> {
                   tab.text = tabsArray[position]
                   tab.setIcon( R.drawable.ic_tab2)
                   tab.icon?.setTint(Color.GRAY)
               }
           }
       }.attach()

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tabLayout.getTabAt(0)?.icon?.setTint(Color.GRAY)
                tabLayout.getTabAt(1)?.icon?.setTint(Color.GRAY)
                tabLayout.getTabAt(2)?.icon?.setTint(Color.GRAY)
                tabLayout.getTabAt(3)?.icon?.setTint(Color.GRAY)
                when (tab.position) {
                    0 -> {
                        tab.icon?.setTint(Color.WHITE)
                    }
                    1 -> {
                        tab.icon?.setTint(Color.WHITE)
                    }
                    2 -> {
                        tab.icon?.setTint(Color.WHITE)
                    }
                    3 -> {
                        tab.icon?.setTint(Color.WHITE)
                   }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }


}