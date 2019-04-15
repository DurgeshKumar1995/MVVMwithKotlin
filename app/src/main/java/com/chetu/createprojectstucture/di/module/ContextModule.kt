package com.chetu.createprojectstucture.di.module

import android.content.Context
import com.chetu.createprojectstucture.base.BaseApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ContextModule {


    @Binds
    @Singleton
    fun provideContext(application: BaseApplication): Context
}
