package com.chetu.createprojectstucture.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.chetu.createprojectstucture.di.util.ViewModelKey
import com.chetu.createprojectstucture.ui.dblist.DBViewModel
import com.chetu.createprojectstucture.ui.detail.DetailsViewModel
import com.chetu.createprojectstucture.ui.list.ListViewModel
import com.chetu.createprojectstucture.util.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

import javax.inject.Singleton


@Module
interface ViewModelModule {

    @Singleton
    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    fun bindListViewModel(listViewModel: ListViewModel): ViewModel

    @Singleton
    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel

    @Singleton
    @Binds
    @IntoMap
    @ViewModelKey(DBViewModel::class)
    fun bindDBViewModel(dbViewModel: DBViewModel):ViewModel

    @Singleton
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
