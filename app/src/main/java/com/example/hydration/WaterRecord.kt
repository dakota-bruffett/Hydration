package com.example.hydration

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 class WaterRecord(
    @PrimaryKey
    @NonNull
    var day: String,
    @NonNull
     glasses: Int) {
    companion object{
        const val MAX_GLASSES = 5

    }
    var glasses: Int = glasses
    set(value){
        if (value < 0 || value >  MAX_GLASSES){
            throw IllegalStateException("Glasses must be between 0 and $MAX_GLASSES")
        }
        field = value
    }

    override fun toString(): String {
        return "Day$day, Glasses$glasses"
    }
}
//here is How i did the Record code for the project