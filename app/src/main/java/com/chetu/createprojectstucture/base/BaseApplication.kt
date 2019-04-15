package com.chetu.createprojectstucture.base

import com.chetu.createprojectstucture.di.component.ApplicationComponent
import com.chetu.createprojectstucture.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication




class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component:ApplicationComponent = DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)

        return component
    }
}
