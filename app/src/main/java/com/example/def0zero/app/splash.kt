package com.example.def0zero.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.def0zero.R

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (savedInstanceState == null) {
            // Create a new instance of the initial fragment
            val initialFragment = spash_fragment1()

            // Add the initial fragment to the fragment_container
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container1, initialFragment)
                .commit()

            // Delayed execution to replace the initial fragment with another fragment
            Handler().postDelayed({
                // Create a new instance of the replacement fragment
                val replacementFragment = splash_fragment2()

                // Replace the initial fragment with the replacement fragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container1, replacementFragment)
                    .commit()
            }, 2000) // Delay in milliseconds
        }
    }
}








