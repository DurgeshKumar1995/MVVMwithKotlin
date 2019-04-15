package com.durgesh.mvvm_pattern.di.component

import com.durgesh.mvvm_pattern.base.BaseApplication
import com.durgesh.mvvm_pattern.di.module.ActivityBindingModule
import com.durgesh.mvvm_pattern.di.module.ApplicationModule
import com.durgesh.mvvm_pattern.di.module.ContextModule
import com.durgesh.mvvm_pattern.di.module.DBModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        DBModule::class,
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<BaseApplication> {

    override fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApplication): Builder

        fun build(): ApplicationComponent
    }
}