package com.durgesh.mvvm_pattern.ui.list

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.durgesh.mvvm_pattern.R
import com.durgesh.mvvm_pattern.data.model.Repo

import java.util.ArrayList

class RepoListAdapter internal constructor(
    viewModel: ListViewModel,
    lifecycleOwner: LifecycleOwner,
    private val repoSelectedListener: RepoSelectedListener
) : RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {
    private val data = ArrayList<Repo>()

    init {
        viewModel.repos.observe(lifecycleOwner, Observer { repos ->
            data.clear()
            if (repos != null) {
                data.addAll(repos)
                notifyDataSetChanged()
            }
        })
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_repo_list_item, parent, false)
        return RepoViewHolder(view, repoSelectedListener)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return data[position].id
    }

    class RepoViewHolder(itemView: View, repoSelectedListener: RepoSelectedListener) :
        RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tv_repo_name)
        lateinit var repoNameTextView: TextView
        @BindView(R.id.tv_repo_description)
        lateinit var repoDescriptionTextView: TextView
        @BindView(R.id.tv_forks)
        lateinit var forksTextView: TextView
        @BindView(R.id.tv_stars)
        lateinit var starsTextView: TextView

        private var repo: Repo? = null

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener { _ ->
                if (repo != null) {
                    repoSelectedListener.onRepoSelected(repo!!)
                }
            }
        }

        fun bind(repo: Repo) {
            this.repo = repo
            repoNameTextView.text = repo.name
            repoDescriptionTextView.text = repo.description
            forksTextView.text = repo.forks.toString()
            starsTextView.text = repo.stars.toString()
        }
    }
}
