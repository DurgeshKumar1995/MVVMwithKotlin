package com.chetu.createprojectstucture.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.chetu.createprojectstucture.database.entity.Habits
import com.chetu.createprojectstucture.util.CommanWord
import io.reactivex.Single


@Dao
interface HabitDao : BaseDao{



    @Query("DELETE FROM ${CommanWord.TableName}")
    fun deleteAll()

    @Query("SELECT * FROM ${CommanWord.TableName} ORDER BY ${CommanWord.ColumanName} ASC" )
    fun getAllHabits() : Single<List<Habits>>

}