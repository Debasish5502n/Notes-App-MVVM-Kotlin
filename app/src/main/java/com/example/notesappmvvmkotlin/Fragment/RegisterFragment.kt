package com.example.notesappmvvmkotlin.Fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.notesappmvvmkotlin.MainActivity
import com.example.notesappmvvmkotlin.NotesActivity
import com.example.notesappmvvmkotlin.R
import com.example.notesappmvvmkotlin.ViewModel.RegisterViewModel
import com.example.notesappmvvmkotlin.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    lateinit var frame: FrameLayout
    lateinit var registerViewModel: RegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding: FragmentRegisterBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_register, container, false)
        checkLogin()
        frame = requireActivity().findViewById<FrameLayout>(R.id.frame)

        binding.doNotHaveAccount.setOnClickListener(View.OnClickListener {
            setFragment(LoginFragment())
        })

        binding.submit.setOnClickListener(View.OnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            if (!binding.nameText.text.toString().isEmpty()) {
                if (!binding.emailText.text.toString().isEmpty()) {
                    if (!binding.passwordText.text.toString().isEmpty() && binding.passwordText.length() >= 8) {
                        if (!binding.confirmPasswordText.text.toString().isEmpty() && binding.passwordText.text.toString().equals(binding.confirmPasswordText.text.toString())) {

                            registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
                            registerViewModel.registerApiCall(binding.nameText.text.toString(), binding.emailText.text.toString(), binding.passwordText.text.toString(),requireActivity())
                        }else{
                            binding.confirmPasswordText.error = "Password Doesn't Match"
                            binding.progressBar.visibility = View.GONE
                            binding.confirmPasswordText.setText("")
                        }
                    }else{
                        binding.passwordText.error = "Enter Password"
                        binding.progressBar.visibility = View.GONE
                        binding.passwordText.setText("")
                    }
                }else{
                    binding.emailText.error = "Enter Email"
                    binding.progressBar.visibility = View.GONE
                    binding.emailText.setText("")
                }
            }else{
                binding.nameText.error = "Enter Name"
                binding.progressBar.visibility = View.GONE
                binding.nameText.setText("")
            }
        })
        return binding.root
    }
    private fun setFragment(fragment: Fragment){
        var fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(frame.id,fragment)
        fragmentTransaction.commit()
    }

    private fun checkLogin() {
        val preferences: SharedPreferences =
            requireActivity().getSharedPreferences("credential", Context.MODE_PRIVATE)
        if (preferences.contains("email")) {
            startActivity(Intent(requireActivity(), NotesActivity::class.java))
            requireActivity().finish()
        } else {
            Toast.makeText(requireActivity(), "Login first", Toast.LENGTH_SHORT).show()
        }
    }
}