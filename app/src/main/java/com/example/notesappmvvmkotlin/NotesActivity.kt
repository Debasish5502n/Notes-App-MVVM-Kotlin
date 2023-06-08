package com.example.notesappmvvmkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesappmvvmkotlin.Adapter.NotesAdapter
import com.example.notesappmvvmkotlin.Api.ApiClint
import com.example.notesappmvvmkotlin.Api.ApiInterface
import com.example.notesappmvvmkotlin.Model.NotesModel
import com.example.notesappmvvmkotlin.ViewModel.GetNotesViewModelViewModel
import com.example.notesappmvvmkotlin.databinding.ActivityNotesBinding
import retrofit2.Retrofit

class NotesActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityNotesBinding
    lateinit var getNotesViewModelViewModel: GetNotesViewModelViewModel
    lateinit var apiInterface: ApiInterface
    var productModel: List<NotesModel>? = null
    lateinit var notesAdapter: NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_notes)

        dataBinding.add.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@NotesActivity,AddNotesActivity::class.java))
        })
        initialize()
        getData()
    }

    private fun initialize() {
        var retrofit: Retrofit? = ApiClint.getClint()
        apiInterface = retrofit!!.create(ApiInterface::class.java)
    }
    private fun setAdapter(models: List<NotesModel>) {
        notesAdapter = NotesAdapter(this, models)
        val layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        dataBinding.notesRecyclerView.layoutManager = layoutManager
        dataBinding.notesRecyclerView.adapter = notesAdapter
    }

    private fun getData() {
        val pModel = ArrayList<NotesModel>()
        getNotesViewModelViewModel = ViewModelProviders.of(this).get(GetNotesViewModelViewModel::class.java)
        getNotesViewModelViewModel.getModelObserver.observe(this,
            Observer<List<Any?>?> { movieModels ->
                if (movieModels != null) {
                    productModel = movieModels as List<NotesModel>
                    setAdapter(productModel!!)
//                    for (model in productModel!!) {
//                        if (model.notes_title.equals("Debasish sahoo")) {
//                            pModel.add(model)
//                            pModel.let { setAdapter(it) }
//
//                        }
//                    }
                    //  noresfound.setVisibility(View.GONE)
                }
                if (movieModels == null) {
//                    recview.setVisibility(View.GONE)
//                    noresfound.setVisibility(View.VISIBLE)
                }
            })
        getNotesViewModelViewModel.makeApiCall()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}