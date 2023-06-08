package com.example.notesappmvvmkotlin.Response

import com.example.notesappmvvmkotlin.Model.NotesModel

class GetNotesResponse(var status: String,var message: String,var data: List<NotesModel>) {
}