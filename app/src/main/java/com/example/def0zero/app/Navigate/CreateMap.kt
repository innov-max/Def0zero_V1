package com.example.def0zero.app.Navigate

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.def0zero.R
import com.example.def0zero.app.models.UserMap
import com.example.def0zero.app.models.places

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.def0zero.databinding.ActivityCreateMapBinding
import com.google.android.gms.maps.model.Marker
import com.google.android.material.snackbar.Snackbar

private const val TAG = "CreateMapActivity"

class CreateMap : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityCreateMapBinding
    private var markers: MutableList<Marker> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = intent.getStringExtra(EXTRA_MAP_TITLE)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mapFragment.view?.let {
            Snackbar.make(it,"Long press to add marker", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK",{})
                .setActionTextColor(ContextCompat.getColor(this,android.R.color.white))
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_create_map, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // check that item is the save menu option
        if (item.itemId == R.id.menSave){
            Log.i(TAG,"Tapped on save!")
            if(markers.isEmpty()){
                Toast.makeText(this,"There must be atleast one maker on the map", Toast.LENGTH_LONG)
                    .show()
                return true

            }
           val places = markers.map { marker -> marker.title?.let { marker.snippet?.let { it1 ->
               places(it,
                   it1,marker.position.latitude, marker.position.longitude)
           } } }
            val userMap= intent.getStringExtra(EXTRA_MAP_TITLE)?.let { UserMap(it,places) }
            val data = Intent()

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnInfoWindowClickListener {markerToDelete ->

            Log.i(TAG,"delete this marker")
            markers.remove(markerToDelete)
            markerToDelete.remove()
        }
        mMap.setOnMapClickListener {latLng->
            Log.i(TAG, "onMapLongClickListener")
            showAlertDialog(latLng)


        }

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun showAlertDialog(latLng: LatLng) {
            val placeFormView = LayoutInflater.from(this).inflate(R.layout.dialog_create_place,null)
        val dialog =
        AlertDialog.Builder(this)
            .setTitle("Create marker")
            .setView(placeFormView)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Ok",null)
            .show()

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
            val title = placeFormView.findViewById<EditText>(R.id.edtxtTitle).text.toString()
            val description = placeFormView.findViewById<EditText>(R.id.edtxtDesc).text.toString()

            if (title.trim().isEmpty()|| description.trim().isEmpty()){

                Toast.makeText(this, "place must have non--empty title and description",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val marker = mMap.addMarker(MarkerOptions().position(latLng).title(title). snippet(description))
            markers.add(marker!!)
            dialog.dismiss()
        }

    }
}