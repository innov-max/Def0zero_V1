package com.example.def0zero.app.Navigate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.def0zero.R
import com.example.def0zero.databinding.FragmentRegisterBinding
import com.example.def0zero.databinding.FragmentSpashFragment1Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class register_fragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    private var _binding: FragmentRegisterBinding? = null
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
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        database = FirebaseDatabase.getInstance("https://def0zero-default-rtdb.firebaseio.com") //  to replace with the link to rtdb link

        // sign up part
        binding.signUp.setOnClickListener {

            binding.signUp.background = resources.getDrawable(R.drawable.switch_tricks, null)
            binding.signUp.setTextColor(resources.getColor(R.color.black, null))
            binding.login.background = null
            binding.SignUpLayout.visibility = View.VISIBLE
            binding.loginLayout.visibility = View.GONE
            binding.login.setTextColor(resources.getColor(R.color.green, null))
        }
        binding.btnsignUp.setOnClickListener {

            binding.SignUpProgressBar.visibility = View.GONE

            if (binding.UserName.text?.trim().toString()
                    .isNotEmpty() || binding.emailSignUp.text?.trim().toString()
                    .isNotEmpty() || binding.emailSignUp.text?.trim().toString()
                    .isNotEmpty() || binding.PasswordSignUp.text?.trim().toString()
                    .isNotEmpty() || binding.Passwordconfirm.text?.trim()
                    .toString().isNotEmpty()
            ) {

                if (binding.PasswordSignUp.text?.trim()
                        .toString() != binding.Passwordconfirm.text?.trim()
                        .toString()
                ) {
                    Toast.makeText(
                        requireActivity(),
                        "The passwords are not matching try again",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    auth.createUserWithEmailAndPassword(
                        binding.emailSignUp.text?.trim().toString(),
                        binding.emailSignUp.text?.trim().toString()
                    )
                        .addOnCompleteListener(requireActivity()) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    requireActivity(),
                                    "Registration Successfull WELCOME",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.e("Task Message", "Successful")
                                //val intent = Intent(this, send_otp::class.java)
                                //startActivity(intent)
                            } else {
                                Log.e("Task Message", "Unsuccessful")
                                Toast.makeText(
                                    requireActivity(),
                                    "Something went wrong try again, make sure your connected to the internet",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }


                        }
                }
            } else {
                Toast.makeText(requireActivity(), "Input Required", Toast.LENGTH_LONG).show()

            }
        }


            //SWITCH TO LOGIN FRAGMENT
            binding.login.setOnClickListener {
                binding.signUp.background = null
                binding.signUp.setTextColor(resources.getColor(R.color.green, null))
                binding.login.background = resources.getDrawable(R.drawable.switch_tricks, null)
                binding.SignUpLayout.visibility = View.GONE
                binding.loginLayout.visibility = View.VISIBLE
                binding.login.setTextColor(resources.getColor(R.color.black, null))
            }
        //LOGIN BUTTON ON CLICK
        binding.btnLogin.setOnClickListener {
            if (binding.idemail.text?.trim().toString().isNotEmpty()||binding.idPassword.text?.trim().toString().isNotEmpty()) {
                if (auth.currentUser == null) {
                    Toast.makeText(
                        requireActivity(),
                        "You are not registered, kindly register",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (auth.currentUser != null) {
                    Toast.makeText(requireContext(), "Login Successfull", Toast.LENGTH_SHORT)
                        .show()
                   /* val intent = Intent(requireActivity(), landingActivity::class.java)
                    startActivity(intent)*/
                    Log.e("login Message", "login successful")
                }
            }else{
                Toast.makeText(requireActivity(),"Fill all the fields",Toast.LENGTH_SHORT).show()
            }
        }



    }


        }




