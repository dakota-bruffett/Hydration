package com.example.hydration

import android.app.Application

class HydrationApplication: Application() {

    private val database by lazy { WaterDatabase.getDataBase(this) }
    val respiratory by lazy { WaterRespiratory(database.waterDao()) }
}