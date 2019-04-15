package com.durgesh.mvvm_pattern.base

import com.durgesh.mvvm_pattern.di.component.ApplicationComponent
import com.durgesh.mvvm_pattern.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication




class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component:ApplicationComponent = DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)

        return component
    }
}
