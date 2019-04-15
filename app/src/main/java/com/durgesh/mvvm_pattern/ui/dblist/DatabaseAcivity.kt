package com.durgesh.mvvm_pattern.ui.dblist

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import com.durgesh.mvvm_pattern.R
import com.durgesh.mvvm_pattern.base.BaseActivity
import com.durgesh.mvvm_pattern.database.entity.Habits
import com.durgesh.mvvm_pattern.selecter.PostItemKeyProvider
import com.durgesh.mvvm_pattern.selecter.SheetDetailsLookup
import com.durgesh.mvvm_pattern.util.ViewModelFactory
import org.jetbrains.anko.toast
import javax.inject.Inject
import android.widget.Toast
import com.google.gson.JsonElement
import android.app.ProgressDialog
import android.util.Log
import com.durgesh.mvvm_pattern.test.ApiResponse
import com.durgesh.mvvm_pattern.test.Status
import com.durgesh.mvvm_pattern.util.CommanWord


class DatabaseAcivity : BaseActivity() {

    @set:Inject
    lateinit var viewModelFactory: ViewModelFactory


    private var dbViewModel: DBViewModel? = null

    override fun layoutRes(): Int = R.layout.activity_database

    private lateinit var tracker: SelectionTracker<Habits>
    private lateinit var habits :ArrayList<Habits>

    private lateinit var progressDialog:ProgressDialog


    @SuppressLint( "CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_database)

        dbViewModel = ViewModelProviders.of(this, viewModelFactory).get(DBViewModel::class.java)


        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = HabitListAdapter(this)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter
        habits= ArrayList()
        dbViewModel!!.loadFavoriteShows()

        dbViewModel?.allWords?.observe(this,
            Observer<List<Habits>> { t ->
                habits.clear()
                habits.addAll(t!!)
                adapter.setWords(t)
            })

        dbViewModel?.isInserted!!.observe(this, Observer {
            if (it!!)
                toast(CommanWord.Success)
            else
                toast(CommanWord.Error)
        })

        findViewById<FloatingActionButton>(R.id.float_button).setOnClickListener {

            val habits = Habits("Addd value ${Math.random()}")
            dbViewModel?.insert(habits)
        }

        adapter.clickEvent.subscribe{
            toast(it.habit+" Deleted")
            dbViewModel?.delete(it)
        }

        tracker = SelectionTracker.Builder(
            "image-items-selection",
            recyclerView,
            PostItemKeyProvider(1, habits),
            SheetDetailsLookup(recyclerView),
            StorageStrategy.createParcelableStorage(Habits::class.java)
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything())
            .build()

        adapter.setSelectionTracker(tracker)

    }

    private fun consumeResponse(apiResponse: ApiResponse) {

        when (apiResponse.status) {

            Status.LOADING -> progressDialog.show()

            Status.SUCCESS -> {
                progressDialog.dismiss()
                renderSuccessResponse(apiResponse.data!!)
            }

            Status.ERROR -> {
                progressDialog.dismiss()
                Toast.makeText(this@DatabaseAcivity, resources.getString(R.string.errorString), Toast.LENGTH_SHORT).show()
            }

            else -> {
            }
        }
    }

    /*
* method to handle success response
* */
    private fun renderSuccessResponse(response: JsonElement) {
        if (!response.isJsonNull) {
            Log.d(CommanWord.Response, response.toString())
        } else {
            Toast.makeText(this@DatabaseAcivity, resources.getString(R.string.errorString), Toast.LENGTH_SHORT).show()
        }
    }

}
