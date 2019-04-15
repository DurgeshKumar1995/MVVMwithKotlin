package com.durgesh.mvvm_pattern.data.rest


import com.durgesh.mvvm_pattern.data.model.Repo
import io.reactivex.Single

import javax.inject.Inject

class RepoRepository @Inject
constructor(private val repoService: RepoService) {

    val repositories: Single<List<Repo>>
        get() = repoService.repositories

    fun getRepo(owner: String, name: String): Single<Repo> {
        return repoService.getRepo(owner, name)
    }
}
