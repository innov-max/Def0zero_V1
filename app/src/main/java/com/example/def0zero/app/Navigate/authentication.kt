package com.example.def0zero.app.Navigate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.def0zero.R
import com.example.def0zero.app.spash_fragment1

class authentication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        val register_fragment = register_fragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container,register_fragment)
            .commit()
    }
}
