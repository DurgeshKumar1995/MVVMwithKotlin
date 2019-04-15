package com.durgesh.mvvm_pattern.ui.list

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.durgesh.mvvm_pattern.R
import com.durgesh.mvvm_pattern.base.BaseFragment
import com.durgesh.mvvm_pattern.data.model.Repo
import com.durgesh.mvvm_pattern.ui.dblist.DatabaseAcivity
import com.durgesh.mvvm_pattern.ui.detail.DetailsFragment
import com.durgesh.mvvm_pattern.ui.detail.DetailsViewModel
import com.durgesh.mvvm_pattern.util.CommanWord
import com.durgesh.mvvm_pattern.util.ViewModelFactory

import javax.inject.Inject

class ListFragment : BaseFragment(), RepoSelectedListener {

    @BindView(R.id.recyclerView)
    lateinit var listView: RecyclerView

    @BindView(R.id.tv_error)
    lateinit var errorTextView: TextView

    @BindView(R.id.loading_view)
    lateinit var loadingView: View

    @set:Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var viewModel: ListViewModel? = null

    @LayoutRes
    override fun layoutRes(): Int {
        return R.layout.screen_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)

        listView.addItemDecoration(DividerItemDecoration(baseActivity!!, DividerItemDecoration.VERTICAL))
        listView.adapter = RepoListAdapter(viewModel!!, this, this)
        listView.layoutManager = LinearLayoutManager(context)

        observableViewModel()

        errorTextView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {

                startActivity(Intent(activity,DatabaseAcivity::class.java))

            }
        })
    }

    override fun onRepoSelected(repo: Repo) {
        val detailsViewModel = ViewModelProviders.of(baseActivity!!, viewModelFactory).get(DetailsViewModel::class.java)
        detailsViewModel.setSelectedRepo(repo)
        baseActivity!!.supportFragmentManager.beginTransaction().replace(R.id.screenContainer, DetailsFragment())
            .addToBackStack(null).commit()
    }

     @SuppressLint(CommanWord.HARDCODED_EXCEPETIN)
     fun observableViewModel() {
        viewModel!!.getRepos().observe(this, Observer{ repos -> if (repos != null) listView.visibility = View.VISIBLE })

        viewModel!!.error.observe(this, Observer { isError ->
            if (isError != null)
                if (isError) {
                    errorTextView.visibility = View.VISIBLE
                    listView.visibility = View.GONE
                    errorTextView.text = "An Error Occurred While Loading Data!"
                } else {
                    errorTextView.visibility = View.GONE
                    errorTextView.text = null
                }
        })

        viewModel!!.getLoading().observe(this, Observer{ isLoading ->
            if (isLoading != null) {
                loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
                if (isLoading) {
                    errorTextView.visibility = View.GONE
                    listView.visibility = View.GONE
                }
            }
        })
    }
}
