package com.example.def0zero.app

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.def0zero.R

class splash_fragment2 : Fragment() {

    private lateinit var progressBar: ProgressBar
    private var progressStatus = 0
    private val maxProgress = 100
    private val delayMillis = 7000 // 7 seconds in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash_fragment2, container, false)
        progressBar = view.findViewById(R.id.progressBar1)

        // Start the progress update
        updateProgressBarWithDelay() // calls the update progress bar function

        return view
    }

    private fun updateProgressBarWithDelay() {
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                progressStatus += 1
                progressBar.progress = progressStatus

                if (progressStatus < maxProgress) {
                    handler.postDelayed(this, (delayMillis / maxProgress).toLong())
                } else {
                    // Progress bar is full, navigate to Fragment3 using Navigation

                }
            }
        }, (delayMillis / maxProgress).toLong())
    }
}
