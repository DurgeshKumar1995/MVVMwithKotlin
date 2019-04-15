package com.chetu.createprojectstucture.di.component

import com.chetu.createprojectstucture.base.BaseApplication
import com.chetu.createprojectstucture.di.module.ActivityBindingModule
import com.chetu.createprojectstucture.di.module.ApplicationModule
import com.chetu.createprojectstucture.di.module.ContextModule
import com.chetu.createprojectstucture.di.module.DBModule
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