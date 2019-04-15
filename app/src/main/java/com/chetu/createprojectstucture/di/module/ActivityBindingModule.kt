package com.chetu.createprojectstucture.di.module

import com.chetu.createprojectstucture.ui.dblist.DatabaseAcivity
import com.chetu.createprojectstucture.ui.main.MainActivity
import com.chetu.createprojectstucture.ui.main.MainFragmentBindingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {


    @ContributesAndroidInjector
    fun bindDatabaseActivity() : DatabaseAcivity

    @ContributesAndroidInjector(modules = [MainFragmentBindingModule::class])
     fun bindMainActivity(): MainActivity


}
