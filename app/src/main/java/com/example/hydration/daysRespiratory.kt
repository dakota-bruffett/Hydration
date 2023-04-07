package com.example.hydration

import java.text.DateFormatSymbols
import java.util.*

class daysRespiratory {
    val weekdays: List<String>
    get(){
        val dateFormatSymbols= DateFormatSymbols.getInstance(Locale.getDefault())
        return dateFormatSymbols.weekdays.asList().filter { it.isNotBlank() }

    }
    val todayIndex: Int
    get(){
        val today = Calendar.getInstance(Locale.getDefault())
        val day  = today.get(Calendar.DAY_OF_WEEK)
        return day-1
    }
}