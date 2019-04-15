package com.durgesh.mvvm_pattern.di.module

import com.durgesh.mvvm_pattern.ui.dblist.DatabaseAcivity
import com.durgesh.mvvm_pattern.ui.main.MainActivity
import com.durgesh.mvvm_pattern.ui.main.MainFragmentBindingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {


    @ContributesAndroidInjector
    fun bindDatabaseActivity() : DatabaseAcivity

    @ContributesAndroidInjector(modules = [MainFragmentBindingModule::class])
     fun bindMainActivity(): MainActivity


}
