package com.example.notesappmvvmkotlin.ViewModel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.notesappmvvmkotlin.Api.ApiClint
import com.example.notesappmvvmkotlin.Api.ApiInterface
import com.example.notesappmvvmkotlin.NotesActivity
import com.example.notesappmvvmkotlin.Response.DeleteNotesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DeleteNotesViewModel : ViewModel() {

    companion object {
        fun deleteNotesApiCall(id: String, context: Context) {
            lateinit var apiInterface: ApiInterface
            var retrofit: Retrofit? = ApiClint.getClint()
            apiInterface = retrofit!!.create(ApiInterface::class.java)
            apiInterface.deleteNotes(id)
                ?.enqueue(object : Callback<DeleteNotesResponse?> {
                    override fun onResponse(
                        call: Call<DeleteNotesResponse?>,
                        response: Response<DeleteNotesResponse?>
                    ) {
                        try {
                            if (response != null) {
                                if (response.body()?.status.equals("1")) {
                                    Toast.makeText(
                                        context,
                                        response.body()?.message,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent = Intent(context, NotesActivity::class.java)
                                    context.startActivity(intent)

                                } else {

                                }
                            } else {
                            }
                        } catch (e: Exception) {
                            Log.e("Failed", e.localizedMessage)
                        }
                    }

                    override fun onFailure(call: Call<DeleteNotesResponse?>, t: Throwable) {
                        Log.e("Failed", t.localizedMessage)
                    }
                })
        }
    }
}