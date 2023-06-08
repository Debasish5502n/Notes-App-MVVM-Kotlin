package com.example.notesappmvvmkotlin.ViewModel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.notesappmvvmkotlin.Api.ApiClint
import com.example.notesappmvvmkotlin.Api.ApiInterface
import com.example.notesappmvvmkotlin.NotesActivity
import com.example.notesappmvvmkotlin.Response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RegisterViewModel : ViewModel() {

    fun registerApiCall(name: String, email: String, password: String, context: Context) {
        lateinit var apiInterface: ApiInterface
        var retrofit: Retrofit? = ApiClint.getClint()
        apiInterface = retrofit!!.create(ApiInterface::class.java)
        apiInterface.registerNotes(name, email, password)
            ?.enqueue(object : Callback<RegisterResponse?> {
                override fun onResponse(
                    call: Call<RegisterResponse?>,
                    response: Response<RegisterResponse?>
                ) {
                    try {
                        if (response != null) {
                            if (response.body()?.status.equals("1")) {
                                Toast.makeText(context, response.body()?.message, Toast.LENGTH_SHORT).show()

                                val preferences  =
                                    context.getSharedPreferences("credential", Context.MODE_PRIVATE)
                                val editor = preferences.edit()
                                editor.putString("email", email)
                                editor.putString("password", password)
                                editor.commit()
                                editor.apply()
                                val intent = Intent(context,NotesActivity::class.java)
                                context.startActivity(intent)

                            } else {

                            }
                        } else {
                        }
                    } catch (e: Exception) {
                        Log.e("Failed", e.localizedMessage)
                    }
                }

                override fun onFailure(call: Call<RegisterResponse?>, t: Throwable) {
                    Log.e("Failed", t.localizedMessage)
                }
            })
    }
}
