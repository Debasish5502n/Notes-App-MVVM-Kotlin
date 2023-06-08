package com.example.notesappmvvmkotlin.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesappmvvmkotlin.Api.ApiClint
import com.example.notesappmvvmkotlin.Api.ApiInterface
import com.example.notesappmvvmkotlin.Model.NotesModel
import com.example.notesappmvvmkotlin.Response.GetNotesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class GetNotesViewModelViewModel : ViewModel() {
    private val notesList: MutableLiveData<List<NotesModel>?>

    init {
        notesList = MutableLiveData<List<NotesModel>?>()
    }

    val getModelObserver: MutableLiveData<List<NotesModel>?>
        get() = notesList

    fun makeApiCall() {
        lateinit var apiInterface: ApiInterface
        var retrofit: Retrofit? = ApiClint.getClint()
        apiInterface = retrofit!!.create(ApiInterface::class.java)
        apiInterface.getNotes().enqueue(object : Callback<GetNotesResponse> {
            override fun onResponse(
                call: Call<GetNotesResponse>,
                response: Response<GetNotesResponse>
            ) {
                try {
                    if (response != null) {
                        if (response.body()?.status.equals("1")) {

                            notesList.postValue(response.body()?.data)

                        } else {

                        }
                    } else {
                    }
                } catch (e: Exception) {
                    Log.e("Failed", e.localizedMessage)
                }
            }

            override fun onFailure(call: Call<GetNotesResponse>, t: Throwable) {
                Log.e("Failed", t.localizedMessage)
            }
        })
    }
}