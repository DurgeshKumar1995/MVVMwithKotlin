package com.durgesh.mvvm_pattern.ui.dblist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.durgesh.mvvm_pattern.database.dao.HabitRepository
import com.durgesh.mvvm_pattern.database.entity.Habits
import com.durgesh.mvvm_pattern.util.CommanWord
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton


@Singleton
class DBViewModel @Inject constructor(private val habitRepository: HabitRepository) : ViewModel() {

    val allWords: MutableLiveData<List<Habits>> =  MutableLiveData()
    var isInserted = MutableLiveData<Boolean>()
    var  loading  = MutableLiveData<Boolean>()
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    var removedFromFavoriteShows: List<Habits>? = null


    fun insert(habits: Habits) {
        val habit = habitRepository.insert(habits)

       // Log.d("DDDDDDDDDDDDDDDDD inser", habit.toString())

        loadFavoriteShows()
    }

    fun delete(habits: Habits) {
        val duck=habitRepository.delete(habits)

       // Log.d("DDDDDDDDDDDDDDDDD delet", duck.toString())
        loadFavoriteShows()
    }


    fun loadFavoriteShows() {
        val favoriteShowsDisposable = habitRepository.getAllFavoriteShows()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onFavoritesFetched, ::onError)
        compositeDisposable.add(favoriteShowsDisposable)
    }

    private fun onError(throwable: Throwable) {
      //  Log.d("DDDDDDDDDDDDDDDDD error", throwable.message)
    }

    private fun onFavoritesFetched(favoriteShows: List<Habits>) {
        Log.d(CommanWord.MY_APP_TAG, "Success")
        removedFromFavoriteShows = ArrayList(favoriteShows.size)
        allWords.value = favoriteShows
//        favoriteShows.forEach {
//            Log.d("DDDDDDDDDDDDDDDDD scess", it.habit)
//        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        removedFromFavoriteShows = null
    }


}