package com.example.def0zero.app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.def0zero.R
import com.example.def0zero.app.Navigate.authentication
import com.example.def0zero.databinding.FragmentSpashFragment1Binding
import com.example.def0zero.databinding.FragmentSplashFragment2Binding

class splash_fragment2 : Fragment() {

    private lateinit var progressBar: ProgressBar
    private var progressStatus = 0
    private val maxProgress = 100
    private val delayMillis = 7000 // 7 seconds in milliseconds

    private var _binding: FragmentSplashFragment2Binding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashFragment2Binding.inflate(inflater, container, false)
        val view = binding.root

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
                    binding.btnContinue.visibility = View.VISIBLE
                    try{

                    binding.btnContinue.setOnClickListener {
                        val intent = Intent(activity, authentication::class.java)

                        startActivity(intent)

                    }
                    } catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }
        }, (delayMillis / maxProgress).toLong())
    }
}
