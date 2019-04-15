package com.chetu.createprojectstucture.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.chetu.createprojectstucture.data.model.Repo
import com.chetu.createprojectstucture.data.rest.RepoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject
import java.util.Objects
import javax.inject.Singleton

@Singleton
class DetailsViewModel @Inject
constructor(private val repoRepository: RepoRepository) : ViewModel() {
     var disposable: CompositeDisposable? = null

     val selectedRepo = MutableLiveData<Repo>()

    fun getSelectedRepo(): LiveData<Repo> {
        return selectedRepo
    }

    init {
        disposable = CompositeDisposable()
    }

    fun setSelectedRepo(repo: Repo) {
        selectedRepo.value = repo
    }

    fun saveToBundle(outState: Bundle) {
        if (selectedRepo.value != null) {
            outState.putStringArray(
                "repo_details",
                arrayOf(selectedRepo.value!!.owner.login, selectedRepo.value!!.name)
            )
        }
    }

    fun restoreFromBundle(savedInstanceState: Bundle?) {
        if (selectedRepo.value == null) {
            if (savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
                loadRepo(savedInstanceState.getStringArray("repo_details")!!)
            }
        }
    }

     fun loadRepo(repo_details: Array<String>) {
        disposable!!.add(repoRepository.getRepo(repo_details[0], repo_details[1]).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<Repo>() {
                override fun onSuccess(value: Repo) {
                    selectedRepo.value = value
                }

                override fun onError(e: Throwable) {

                }
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable!!.clear()
            disposable = null
        }
    }

}
