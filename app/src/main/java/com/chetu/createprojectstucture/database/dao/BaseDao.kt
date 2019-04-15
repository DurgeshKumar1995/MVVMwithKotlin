package com.chetu.createprojectstucture.database.dao

import android.arch.persistence.room.*
import com.chetu.createprojectstucture.database.entity.Habits
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface BaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Habits) :Long

    @Insert
    fun insert(vararg obj: Habits) :List<Long>

    @Update
    fun update(obj: Habits):Int

    @Delete
    fun delete(obj: Habits):Int
}