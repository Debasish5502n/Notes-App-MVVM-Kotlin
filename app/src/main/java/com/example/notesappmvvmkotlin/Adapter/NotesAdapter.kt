package com.example.notesappmvvmkotlin.Adapter

import android.accounts.AccountManager.get
import android.app.AlertDialog
import android.app.Dialog
import android.app.SearchManager.OnCancelListener
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.nfc.tech.IsoDep.get
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappmvvmkotlin.AddNotesActivity
import com.example.notesappmvvmkotlin.Api.ApiInterface
import com.example.notesappmvvmkotlin.Model.NotesModel
import com.example.notesappmvvmkotlin.R
import com.example.notesappmvvmkotlin.Response.DeleteNotesResponse
import com.example.notesappmvvmkotlin.ViewModel.AddNotesViewModel
import com.example.notesappmvvmkotlin.ViewModel.DeleteNotesViewModel

class NotesAdapter(var context: Context, var mode: List<NotesModel>) : RecyclerView.Adapter<NotesAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.notes_single_item,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.notes_title.text = mode[position].notes_title
        holder.notes.text = mode[position].notes
        holder.notes_bg.backgroundTintList = ColorStateList.valueOf(Color.parseColor(mode[position].notes_bg))

        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Are you sure to delete this dada ?")

        dialog.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->

        })

        dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
            DeleteNotesViewModel.deleteNotesApiCall(mode[position].id,context)
        })

        holder.itemView.setOnLongClickListener {
            dialog.show()

            return@setOnLongClickListener true
        }

        holder.itemView.setOnClickListener({
            val intent = Intent(context, AddNotesActivity::class.java)
            intent.putExtra("viewType",1)
            intent.putExtra("id",mode[position].id)
            intent.putExtra("notes",mode[position].notes)
            intent.putExtra("notes_title",mode[position].notes_title)
            context.startActivity(intent)
        })

    }

    override fun getItemCount(): Int {
        return mode.size
    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var notes_title: TextView = itemView.findViewById<TextView?>(R.id.notes_title)
        var notes: TextView = itemView.findViewById<TextView?>(R.id.notes)
        var notes_bg: LinearLayout  = itemView.findViewById<LinearLayout?>(R.id.notes_bg)
    }
}