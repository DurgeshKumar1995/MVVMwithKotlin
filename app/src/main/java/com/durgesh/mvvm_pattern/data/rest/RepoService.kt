package com.durgesh.mvvm_pattern.data.rest

import com.durgesh.mvvm_pattern.data.model.Repo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoService {

    @get:GET("orgs/Google/repos")
    val repositories: Single<List<Repo>>

    @GET("repos/{owner}/{name}")
    fun getRepo(@Path("owner") owner: String, @Path("name") name: String): Single<Repo>
}
