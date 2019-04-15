package com.durgesh.mvvm_pattern.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.durgesh.mvvm_pattern.di.util.ViewModelKey
import com.durgesh.mvvm_pattern.ui.dblist.DBViewModel
import com.durgesh.mvvm_pattern.ui.detail.DetailsViewModel
import com.durgesh.mvvm_pattern.ui.list.ListViewModel
import com.durgesh.mvvm_pattern.util.ViewModelFactory
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
