package com.example.notesappmvvmkotlin.Api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClint {

    companion object{
        var RETROFIT: Retrofit? = null

        fun getClint(): Retrofit?{
            if (RETROFIT == null) {
                val gson: Gson = GsonBuilder().create()
                val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

                RETROFIT = Retrofit.Builder()
                    .baseUrl("http://192.168.97.142/Notes Api/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build()

            }
            return RETROFIT
        }
    }
}