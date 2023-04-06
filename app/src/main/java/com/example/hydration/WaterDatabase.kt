package com.example.hydration

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WaterRecord::class], version = 1)
abstract class WaterDatabase: RoomDatabase() {

    abstract fun waterDao(): WaterDao

    companion object{

        @Volatile
        private var INSTANCE: WaterDatabase? = null

        fun getDataBase(context:Context): WaterDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context, WaterDatabase::class.java, "Water_Base")
                        .build()
                INSTANCE = instance
                return instance

            }
        }
    }

}