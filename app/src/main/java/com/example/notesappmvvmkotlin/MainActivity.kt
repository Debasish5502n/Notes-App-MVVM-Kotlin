package com.example.notesappmvvmkotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.notesappmvvmkotlin.Fragment.LoginFragment
import com.example.notesappmvvmkotlin.Fragment.RegisterFragment
import com.example.notesappmvvmkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityMainBinding

    companion object {
        var register: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        checkLogin()

        if (register) {
            setFragment(RegisterFragment())
            register = false
        } else {
            setFragment(LoginFragment())
        }
    }

    private fun setFragment(fragment: Fragment) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(dataBinding.frame.id, fragment)
        fragmentTransaction.commit()
    }

    private fun checkLogin() {
        val preferences: SharedPreferences =
            getSharedPreferences("credential", Context.MODE_PRIVATE)
        if (preferences.contains("email")) {
            startActivity(Intent(this@MainActivity, NotesActivity::class.java))
            finish()
        } else {
            Toast.makeText(this@MainActivity, "Login first", Toast.LENGTH_SHORT).show()
        }
    }
}