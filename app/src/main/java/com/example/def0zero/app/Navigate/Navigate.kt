package com.example.def0zero.app.Navigate

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.def0zero.R
import com.example.def0zero.app.models.UserMap
import com.example.def0zero.app.models.places
import com.example.def0zero.databinding.ActivityNavigateBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutput
import java.io.ObjectOutputStream

const val EXTRA_USER_MAP = "EXTRA_USER_MAP"
const val EXTRA_MAP_TITLE = "EXTRA_MAP_TITLE"
private const val REQUEST_CODE = 123
private const val FILENAME = "UserMAps.data"

class Navigate : AppCompatActivity() {
    private lateinit var binding:ActivityNavigateBinding
    private lateinit var userMaps: MutableList<UserMap>
    private lateinit var mapAdapter:MapsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userMaps = generateSampleData().toMutableList()
        binding.rvnavigateplaces.layoutManager = LinearLayoutManager(this)
        // set adapter on the recycler view
        mapAdapter = MapsAdapter(this, userMaps, object : MapsAdapter.OnClickListener {
            override fun onItemClick(position: Int) {
               Log.i(TAG, "onitemClick $position")
                // when user taps on the view in the recyclerview
                val intent = Intent(this@Navigate,MapsActivity::class.java)
                intent.putExtra(EXTRA_USER_MAP,userMaps[position])
                startActivity(intent)
            }

        })
        binding.rvnavigateplaces.adapter = mapAdapter
        binding.fab.setOnClickListener {
            Log.i(TAG, "Tap on FAB")
            showAlertDialog()


        }



    }

    private fun showAlertDialog() {
        val mapFormView = LayoutInflater.from(this).inflate(R.layout.dialog_create_map,null)
        val dialog =
            AlertDialog.Builder(this)
                .setTitle("Map Title")
                .setView(mapFormView)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok",null)
                .show()

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
            val title = mapFormView.findViewById<EditText>(R.id.etTitle).text.toString()

            if (title.trim().isEmpty()){

                Toast.makeText(this, "Map  must have non--empty title", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Navigate to create Mpa activity
            val intent = Intent(this,CreateMap::class.java)
            intent.putExtra(EXTRA_MAP_TITLE, title)
            startActivityForResult(intent,REQUEST_CODE)
            dialog.dismiss()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            //get new map data
            val userMap = data?.getSerializableExtra(EXTRA_USER_MAP) as UserMap
            Log.i(TAG,"onActivity Result with new map title ${userMap.title}")
            userMaps.add(userMap)
            mapAdapter.notifyItemInserted(userMaps.size -1)

        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun serializeUserMaps(context: Context, userMaps:List<UserMap>){

        Log.i(TAG, "serialUserMaps")
        ObjectOutputStream(FileOutputStream(getDatafile(context))).use {it.writeObject(userMaps) }
    }

    private fun deserializeUserMaps(context: Context):List<UserMap>{

        Log.i(TAG,"desrealizeUserMaps")
        getDatafile(context)
        val dataFile = getDatafile(context)
        if (!dataFile.exists()){
            Log.i(TAG, "Data file does not exist yet")
            return emptyList()
        }
        ObjectInputStream(FileInputStream(dataFile)).use { return it.readObject() as List<UserMap> }
    }
    private fun getDatafile(context : Context): File {
        Log.i(TAG,"Getting file from directory ${context.filesDir}")
        return File(context.filesDir, FILENAME)
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