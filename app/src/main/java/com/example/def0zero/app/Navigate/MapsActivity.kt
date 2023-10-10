package com.example.def0zero.app.Navigate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.def0zero.R
import com.example.def0zero.app.models.UserMap
import com.example.def0zero.app.models.places

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.example.def0zero.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.LatLngBounds

private const val  TAG = "MapsActivity"
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var userMap:UserMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userMap = intent.getSerializableExtra(EXTRA_USER_MAP)as UserMap

        supportActionBar?.title = userMap.title
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        Log.i(TAG, "user map to render:${userMap.title}")
        val boundsBuilder = LatLngBounds.builder()
      for (place in userMap.Place){

            val LatLng = place?.let { LatLng(it.latitude,place.longitude) }
          LatLng?.let { MarkerOptions().position(it).title(place.title).snippet(place.description) }
              ?.let { mMap.addMarker(it) }
        }


        // Add a marker in Nairobi and move the camera

       mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(),1000,1000,0))
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(),1000,1000,0))
    }
}