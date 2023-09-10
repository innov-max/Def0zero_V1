package com.example.def0zero.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.def0zero.R
import com.example.def0zero.databinding.FragmentSpashFragment1Binding
import com.example.def0zero.databinding.FragmentSplashFragment2Binding


class spash_fragment1 : Fragment() {

    private var _binding: FragmentSpashFragment1Binding? = null
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
            _binding = FragmentSpashFragment1Binding.inflate(inflater, container, false)
            val view = binding.root





            return view
        }
    }
