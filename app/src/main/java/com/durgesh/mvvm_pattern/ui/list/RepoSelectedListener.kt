package com.durgesh.mvvm_pattern.ui.list


import com.durgesh.mvvm_pattern.data.model.Repo

interface RepoSelectedListener {

    fun onRepoSelected(repo: Repo)
}
