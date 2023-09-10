package com.example.def0zero.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.def0zero.R
import com.example.def0zero.app.Navigate.authentication
import com.example.def0zero.databinding.ActivitySplashBinding

class splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val first_fragment = spash_fragment1()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, first_fragment)
                .commit()

            Handler().postDelayed({
                fragmentManager.popBackStack()
                val replacementFragment = splash_fragment2()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, replacementFragment)
                    .commit()

                // Start the authentication activity after the second delay

            }, 5000) // Delay in milliseconds
        }
    }

    private fun ToAnotherActivity() {
        val intent = Intent(this@splash, authentication::class.java)
        startActivity(intent)
        finish() // Finish the current activity if needed
    }
}











