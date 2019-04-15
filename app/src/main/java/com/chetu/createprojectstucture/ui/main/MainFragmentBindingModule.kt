package com.chetu.createprojectstucture.ui.main

import com.chetu.createprojectstucture.ui.detail.DetailsFragment
import com.chetu.createprojectstucture.ui.list.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainFragmentBindingModule {

    @ContributesAndroidInjector
    fun provideListFragment(): ListFragment

    @ContributesAndroidInjector
    fun provideDetailsFragment(): DetailsFragment
}
