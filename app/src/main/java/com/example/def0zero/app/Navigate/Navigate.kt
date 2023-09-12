package com.example.def0zero.app.Navigate

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.def0zero.app.models.UserMap
import com.example.def0zero.app.models.places
import com.example.def0zero.databinding.ActivityNavigateBinding
const val EXTRA_USER_MAP = "EXTRA_USER_MAP"
class Navigate : AppCompatActivity() {
    private lateinit var binding:ActivityNavigateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userMaps = generateSampleData()
        binding.rvnavigateplaces.layoutManager = LinearLayoutManager(this)
        // set adapter on the recycler view
        binding.rvnavigateplaces.adapter = MapsAdapter(this, userMaps, object : MapsAdapter.OnClickListener {
            override fun onItemClick(position: Int) {
               Log.i(TAG, "onitemClick $position")
                // when user taps on the view in the recyclerview
                val intent = Intent(this@Navigate,MapsActivity::class.java)
                intent.putExtra(EXTRA_USER_MAP,userMaps[position])
                startActivity(intent)
            }

        })
        binding.fab.setOnClickListener {
            Log.i(TAG, "Tap on FAB")

            val intent = Intent(this,)
        }



    }

        private fun generateSampleData(): List<UserMap> {
            return listOf(
                UserMap(
                    "Memories from University",
                    listOf(
                        places("Branner Hall", "Best dorm at Stanford", 37.426, -122.163),
                        places("Gates CS building", "Many long nights in this basement", 37.430, -122.173),
                        places("Pinkberry", "First date with my wife", 37.444, -122.170)
                    )
                ),
                UserMap("January vacation planning!",
                    listOf(
                        places("Tokyo", "Overnight layover", 35.67, 139.65),
                        places("Ranchi", "Family visit + wedding!", 23.34, 85.31),
                        places("Singapore", "Inspired by \"Crazy Rich Asians\"", 1.35, 103.82)
                    )),
                UserMap("Singapore travel itinerary",
                    listOf(
                        places("Gardens by the Bay", "Amazing urban nature park", 1.282, 103.864),
                        places("Jurong Bird Park", "Family-friendly park with many varieties of birds", 1.319, 103.706),
                        places("Sentosa", "Island resort with panoramic views", 1.249, 103.830),
                        places("Botanic Gardens", "One of the world's greatest tropical gardens", 1.3138, 103.8159)
                    )
                ),
                UserMap("My favorite places in the Midwest",
                    listOf(
                        places("Chicago", "Urban center of the midwest, the \"Windy City\"", 41.878, -87.630),
                        places("Rochester, Michigan", "The best of Detroit suburbia", 42.681, -83.134),
                        places("Mackinaw City", "The entrance into the Upper Peninsula", 45.777, -84.727),
                        places("Michigan State University", "Home to the Spartans", 42.701, -84.482),
                        places("University of Michigan", "Home to the Wolverines", 42.278, -83.738)
                    )
                ),
                UserMap("Restaurants to try",
                    listOf(
                        places("Champ's Diner", "Retro diner in Brooklyn", 40.709, -73.941),
                        places("Althea", "Chicago upscale dining with an amazing view", 41.895, -87.625),
                        places("Shizen", "Elegant sushi in San Francisco", 37.768, -122.422),
                        places("Citizen Eatery", "Bright cafe in Austin with a pink rabbit", 30.322, -97.739),
                        places("Kati Thai", "Authentic Portland Thai food, served with love", 45.505, -122.635)
                    )
                )
            )
        }




}