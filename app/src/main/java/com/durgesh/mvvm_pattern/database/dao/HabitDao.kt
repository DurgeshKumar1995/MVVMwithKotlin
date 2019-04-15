package com.durgesh.mvvm_pattern.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.durgesh.mvvm_pattern.database.entity.Habits
import com.durgesh.mvvm_pattern.util.CommanWord
import io.reactivex.Single


@Dao
interface HabitDao : BaseDao{



    @Query("DELETE FROM ${CommanWord.TableName}")
    fun deleteAll()

    @Query("SELECT * FROM ${CommanWord.TableName} ORDER BY ${CommanWord.ColumanName} ASC" )
    fun getAllHabits() : Single<List<Habits>>

}