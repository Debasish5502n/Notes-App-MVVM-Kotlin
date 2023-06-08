package com.example.notesappmvvmkotlin.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesappmvvmkotlin.R

import android.widget.FrameLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.notesappmvvmkotlin.ViewModel.LoginViewModel
import com.example.notesappmvvmkotlin.ViewModel.RegisterViewModel
import com.example.notesappmvvmkotlin.databinding.ActivityMainBinding
import com.example.notesappmvvmkotlin.databinding.FragmentLoginBinding
import com.example.notesappmvvmkotlin.databinding.FragmentRegisterBinding

class LoginFragment : Fragment() {

    lateinit var frame: FrameLayout
    lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)
        frame = requireActivity().findViewById<FrameLayout>(R.id.frame)

        binding.haveAccount.setOnClickListener(View.OnClickListener {
            setFragment(RegisterFragment())
        })

        binding.login.setOnClickListener(View.OnClickListener {
            binding.progressBar.visibility = View.VISIBLE
        if (!binding.emailText.text.isEmpty()){
            if (!binding.passwordText.text.isEmpty() && binding.passwordText.length() >= 8){
                loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
                loginViewModel.loginApiCall(binding.emailText.text.toString(), binding.passwordText.text.toString(),requireActivity())
            }else{
                binding.emailText.error = "Enter Email"
                binding.progressBar.visibility = View.GONE
                binding.emailText.setText("")
            }
        }else{
            binding.emailText.error = "Enter Email"
            binding.progressBar.visibility = View.GONE
            binding.emailText.setText("")
        }
        })
        return binding.root
    }
    private fun setFragment(fragment: Fragment){
        var fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(frame.id,fragment)
        fragmentTransaction.commit()
    }
}