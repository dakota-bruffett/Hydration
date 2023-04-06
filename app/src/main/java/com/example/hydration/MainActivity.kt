package com.example.hydration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.text.DateFormatSymbols
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var waterViewPage:ViewPager2
    private lateinit var waterTabLayout: TabLayout

    private val waterViewModel by lazy {
        WaterViewModelFactory((application as HydrationApplication).respiratory)
            .create(WaterViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        waterViewPage = findViewById(R.id.water_view_page)
        waterTabLayout= findViewById(R.id.water_day_tab_layout)

        val days = resources.getStringArray(R.array.Days)

        val pagerAdapter = WaterViewPagerAdapter(this,days)
        waterViewPage.adapter = pagerAdapter

        TabLayoutMediator(waterTabLayout,waterViewPage){tab, position ->
            tab.text= days[position]
        }.attach()
        scrolltoToday()

        //val tuesday = WaterRecord("Tuesday", 1)
        //waterViewModel.insertNewRecord(tuesday)
        
        //val wednesday = WaterRecord("Wednesday",3)
        //waterViewModel.insertNewRecord(wednesday)

        waterViewModel.allRecords.observe(this){records->
            Log.d("Main_Activity","$records")
        }
        //supportFragmentManager.beginTransaction()
        //    .add(R.id.Contain, Hydration_Fragment.newInstance("Wednesday"))
      //      .commit()
    }

    private fun scrolltoToday() {
        val today = Calendar.getInstance(Locale.getDefault())
        val day = today.get(Calendar.DAY_OF_WEEK)
        waterViewPage.setCurrentItem(day- 1, false)
    }
    private fun getWeekdays():List <String>{
        val dateFormatSymbols = DateFormatSymbols.getInstance(Locale.getDefault())
        return dateFormatSymbols.weekdays.asList().filter { it.isNotBlank() }

    }
}