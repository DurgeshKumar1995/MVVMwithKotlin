package com.durgesh.mvvm_pattern.ui.main

import com.durgesh.mvvm_pattern.ui.detail.DetailsFragment
import com.durgesh.mvvm_pattern.ui.list.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainFragmentBindingModule {

    @ContributesAndroidInjector
    fun provideListFragment(): ListFragment

    @ContributesAndroidInjector
    fun provideDetailsFragment(): DetailsFragment
}
