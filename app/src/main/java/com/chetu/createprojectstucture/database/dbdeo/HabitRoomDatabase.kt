package com.chetu.createprojectstucture.database.dbdeo

import com.chetu.createprojectstucture.database.entity.Habits
import com.chetu.createprojectstucture.database.dao.HabitDao
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