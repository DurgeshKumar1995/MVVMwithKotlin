package com.durgesh.mvvm_pattern.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.durgesh.mvvm_pattern.R
import com.durgesh.mvvm_pattern.base.BaseFragment
import com.durgesh.mvvm_pattern.ui.dblist.DatabaseAcivity
import com.durgesh.mvvm_pattern.util.ViewModelFactory

import javax.inject.Inject

class DetailsFragment : BaseFragment() {

    @BindView(R.id.tv_repo_name)
    lateinit var repoNameTextView: TextView
    @BindView(R.id.tv_repo_description)
    lateinit var repoDescriptionTextView: TextView
    @BindView(R.id.tv_forks)
    lateinit var forksTextView: TextView
    @BindView(R.id.tv_stars)
    lateinit var starsTextView: TextView

    @set:Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var detailsViewModel: DetailsViewModel? = null

    override fun layoutRes(): Int {
        return R.layout.screen_details
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        detailsViewModel = ViewModelProviders.of(baseActivity!!, viewModelFactory).get(DetailsViewModel::class.java)
        detailsViewModel!!.restoreFromBundle(savedInstanceState)


        starsTextView.setOnClickListener {

            startActivity(Intent(activity, DatabaseAcivity::class.java))

        }
        repoNameTextView.setOnClickListener {

            startActivity(Intent(activity, DatabaseAcivity::class.java))

        }

        displayRepo()

    }

    private inline fun <reified T:View > bindView(view: View,@IdRes id :Int) :T = view.findViewById(id)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        detailsViewModel!!.saveToBundle(outState)
    }

    fun displayRepo() {
        detailsViewModel!!.getSelectedRepo().observe(this, Observer { repo ->
            if (repo != null) {
                repoNameTextView.text=repo.name
                repoDescriptionTextView.text=repo.description
                forksTextView.text=repo.forks.toString()
                starsTextView.text=repo.stars.toString()
            }
        })
    }
}
