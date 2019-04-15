package com.durgesh.mvvm_pattern.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log.d
import com.durgesh.mvvm_pattern.data.model.Repo
import com.durgesh.mvvm_pattern.data.rest.RepoRepository
import com.durgesh.mvvm_pattern.util.CommanWord
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListViewModel @Inject
constructor(private val repoRepository: RepoRepository) : ViewModel() {
     var disposable: CompositeDisposable? = null

     val repos = MutableLiveData<List<Repo>>()
     val repoLoadError = MutableLiveData<Boolean>()
     val loading = MutableLiveData<Boolean>()
     val error: LiveData<Boolean>
        get() = repoLoadError

    init {
        disposable = CompositeDisposable()
        fetchRepos()
    }

     fun getRepos(): LiveData<List<Repo>> {
        return repos
    }

     fun getLoading(): LiveData<Boolean> {
        return loading
    }



    fun fetchRepos() {
        loading.value = true
        disposable!!.add(repoRepository.repositories.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<List<Repo>>() {
                override fun onSuccess(value: List<Repo>) {
                    repoLoadError.value = false
                    repos.value = value
                    loading.value = false
                }

                override fun onError(e: Throwable) {
                    d(CommanWord.MY_APP_TAG, "${e.localizedMessage} ${e.message}")
                    repoLoadError.value = true
                    loading.value = false
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
