package com.durgesh.mvvm_pattern.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.arch.persistence.room.Room
import android.content.Context
import com.durgesh.mvvm_pattern.database.dao.HabitDao
import com.durgesh.mvvm_pattern.database.dbdeo.HabitRoomDatabase


@Module(includes = [ViewModelModule::class])
object DBModule{

    @JvmStatic
    @Singleton
    @Provides
    fun provideHabitRoomDatabase(context: Context): HabitRoomDatabase {
        return Room.databaseBuilder(
            context,
            HabitRoomDatabase::class.java, HabitRoomDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideHabitDao(HabitRoomDatabase: HabitRoomDatabase): HabitDao {
        return HabitRoomDatabase.wordDao()
    }
    
}