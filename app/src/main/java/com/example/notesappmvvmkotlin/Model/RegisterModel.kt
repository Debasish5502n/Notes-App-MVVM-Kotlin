package com.example.notesappmvvmkotlin.Model

class RegisterModel(var name: String, var email: String, var password: String) {

    constructor(): this("","","")
}