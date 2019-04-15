package com.durgesh.mvvm_pattern.database.dao

import com.durgesh.mvvm_pattern.database.entity.Habits
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HabitRepository @Inject constructor(private val habitDao: HabitDao) {

    fun getAllFavoriteShows() : Single<List<Habits>>{
        return habitDao.getAllHabits()
    }

    fun insert(habits: Habits): Long {
       return habitDao.insert(habits)
    }
    fun delete(habits: Habits):Int{
        return habitDao.delete(habits)
    }

}