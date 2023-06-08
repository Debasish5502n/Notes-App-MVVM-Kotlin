package com.example.notesappmvvmkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.notesappmvvmkotlin.ViewModel.AddNotesViewModel
import com.example.notesappmvvmkotlin.ViewModel.UpdateNotesViewModel
import com.example.notesappmvvmkotlin.databinding.ActivityAddNotesBinding

class AddNotesActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityAddNotesBinding
    lateinit var addNotesViewModel: AddNotesViewModel
    lateinit var updateNotesViewModel: UpdateNotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_notes)

        if (intent.getIntExtra("viewType",0) == 1){
            var notes: String? = intent.getStringExtra("notes")
            var notes_title: String? = intent.getStringExtra("notes_title")

            dataBinding.notes.setText(notes)
            dataBinding.notesTitle.setText(notes_title)
            var id: String? = intent.getStringExtra("id")
            dataBinding.submit.setText("Update")
            dataBinding.submit.setOnClickListener(View.OnClickListener {
                if (id != null) {
                    updateNotes(id)
                }
            })
        }else{
            dataBinding.blue.setOnClickListener(View.OnClickListener {
                dataBinding.colorCode.setText("")
                dataBinding.colorCode.setText("#2b45a1")
            })
            dataBinding.purple.setOnClickListener(View.OnClickListener {
                dataBinding.colorCode.setText("")
                dataBinding.colorCode.setText("#892b9e")
            })
            dataBinding.red.setOnClickListener(View.OnClickListener {
                dataBinding.colorCode.setText("")
                dataBinding.colorCode.setText("#9e2b2f")
            })
            dataBinding.yellow.setOnClickListener(View.OnClickListener {
                dataBinding.colorCode.setText("")
                dataBinding.colorCode.setText("#9e942b")
            })
            dataBinding.green.setOnClickListener(View.OnClickListener {
                dataBinding.colorCode.setText("")
                dataBinding.colorCode.setText("#2b9e40")
            })
            dataBinding.submit.setOnClickListener(View.OnClickListener {
                addNotes()
            })
        }

    }

    private fun addNotes(){
        dataBinding.progressBar.visibility = View.VISIBLE
        dataBinding.submit.visibility = View.GONE
        if (!dataBinding.notesTitle.text.isEmpty()){
            if (!dataBinding.notes.text.isEmpty()){
            if (!dataBinding.colorCode.text.isEmpty()) {
                var date = System.currentTimeMillis()
                addNotesViewModel = ViewModelProviders.of(this).get(AddNotesViewModel::class.java)
                addNotesViewModel.addNotesApiCall(
                    dataBinding.notesTitle.text.toString(),
                    dataBinding.notes.text.toString(),
                    date.toString(),
                    dataBinding.colorCode.text.toString(),
                    this@AddNotesActivity
                )
            }else{
                Toast.makeText(this@AddNotesActivity, "Select Color", Toast.LENGTH_SHORT).show()
            }
            }else{
                dataBinding.notesTitle.error = "Enter Email"
                dataBinding.progressBar.visibility = View.GONE
                dataBinding.submit.visibility = View.VISIBLE
                dataBinding.notesTitle.setText("")
            }
        }else{
            dataBinding.notes.error = "Enter Email"
            dataBinding.progressBar.visibility = View.GONE
            dataBinding.submit.visibility = View.VISIBLE
            dataBinding.notes.setText("")
        }
    }

    fun updateNotes(id: String){
        dataBinding.progressBar.visibility = View.VISIBLE
        dataBinding.submit.visibility = View.GONE
        if (!dataBinding.notesTitle.text.isEmpty()){
            if (!dataBinding.notes.text.isEmpty()){
                var date = System.currentTimeMillis()
                updateNotesViewModel = ViewModelProviders.of(this).get(UpdateNotesViewModel::class.java)
                updateNotesViewModel.updateNotesApiCall(id,dataBinding.notes.text.toString(),this@AddNotesActivity)
            }else{
                dataBinding.notesTitle.error = "Enter Email"
                dataBinding.progressBar.visibility = View.GONE
                dataBinding.submit.visibility = View.VISIBLE
                dataBinding.notesTitle.setText("")
            }
        }else{
            dataBinding.notes.error = "Enter Email"
            dataBinding.progressBar.visibility = View.GONE
            dataBinding.submit.visibility = View.VISIBLE
            dataBinding.notes.setText("")
        }
    }
}