package com.durgesh.mvvm_pattern.database.dbdeo

import com.durgesh.mvvm_pattern.database.entity.Habits
import com.durgesh.mvvm_pattern.database.dao.HabitDao
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database


@Database(entities = [Habits::class], version = HabitRoomDatabase.DATABASE_VERSION , exportSchema = false)
abstract class HabitRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): HabitDao

    companion object {
        const val DATABASE_NAME = "tvmaze.db"
        const val DATABASE_VERSION = 1
    }

}