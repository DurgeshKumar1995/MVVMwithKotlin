package com.durgesh.mvvm_pattern.di.module

import android.content.Context
import com.durgesh.mvvm_pattern.base.BaseApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ContextModule {


    @Binds
    @Singleton
    fun provideContext(application: BaseApplication): Context
}
