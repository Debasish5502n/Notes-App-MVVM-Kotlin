package com.example.notesappmvvmkotlin.Api

import com.example.notesappmvvmkotlin.Response.DeleteNotesResponse
import com.example.notesappmvvmkotlin.Response.GetNotesResponse
import com.example.notesappmvvmkotlin.Response.LoginResponse
import com.example.notesappmvvmkotlin.Response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("notesRegister.php")
    fun registerNotes(@Field("name") name: String?,@Field("email") email: String?, @Field("password") password: String?): Call<RegisterResponse?>?

    @FormUrlEncoded
    @POST("notesLogin.php")
    fun loginNotes(@Field("email") email: String?, @Field("password") password: String?): Call<LoginResponse?>?

    @FormUrlEncoded
    @POST("addNotes.php")
    fun addNotes(@Field("notes_title") name: String?,@Field("notes") email: String?, @Field("date") password: String?, @Field("notes_bg") notes_bg: String?): Call<RegisterResponse?>?

    @GET("getNotes.php")
    fun getNotes(): Call<GetNotesResponse>

    @FormUrlEncoded
    @POST("deleteNotes.php")
    fun deleteNotes(@Field("id") poetry: String): Call<DeleteNotesResponse>

    @FormUrlEncoded
    @POST("updateNotes.php")
    fun updateNotes(@Field("notes") notes: String, @Field("id") poetry: String): Call<DeleteNotesResponse>

}