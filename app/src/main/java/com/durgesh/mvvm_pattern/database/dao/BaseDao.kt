package com.durgesh.mvvm_pattern.database.dao

import android.arch.persistence.room.*
import com.durgesh.mvvm_pattern.database.entity.Habits

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