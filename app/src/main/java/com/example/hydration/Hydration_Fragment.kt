package com.example.hydration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_DayofWeek = "Day of the week"

class Hydration_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val waterViewModel: WaterViewModel by lazy {
        WaterViewModelFactory((requireActivity().application as HydrationApplication).respiratory)
            .create(WaterViewModel::class.java)
    }
    private lateinit var waterRatingBar: RatingBar
    private lateinit var addGlassButton: Button
    private lateinit var resetGlassButton: Button
    private lateinit var dayofweek:String
    private lateinit var waterRecord: WaterRecord


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
          dayofweek= it.getString("There Was no key in the bundle")?:
          throw IllegalStateException("There is none today")

        }
    }

    private fun addGlass() {
      if  (waterRecord.glasses< 5){
          waterRecord.glasses++
          waterViewModel.updateRecord(waterRecord)
      }
    }
    private fun resetGlass(){
        waterRecord.glasses = 0
        waterViewModel.updateRecord(waterRecord)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hydration, container, false)
        waterRatingBar = view.findViewById(R.id.water_rating_bar)
        addGlassButton = view.findViewById(R.id.add_glass_button)
        resetGlassButton = view.findViewById(R.id.reset_count_button)
        waterViewModel.getRecordForDay(dayofweek).observe(requireActivity()) { waterRecord ->
            if (waterRecord == null) {
                waterViewModel.insertNewRecord(WaterRecord(dayofweek, glasses = 0))
            } else {
                this.waterRecord = waterRecord
                waterRatingBar.progress = waterRecord.glasses

                addGlassButton.setOnClickListener { addGlass() }
                resetGlassButton.setOnClickListener { resetGlass() }
            }
        }
        return view


        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_hydration, container, false)
    }

    companion object {

              fun newInstance(dayOfWeek: String) =
             Hydration_Fragment().apply {
                    arguments = Bundle().apply {
                   putString(ARG_DayofWeek,dayOfWeek)
                }
            }
    }
}