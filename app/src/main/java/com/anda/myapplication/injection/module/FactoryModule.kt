package com.anda.myapplication.injection.module

import android.arch.lifecycle.ViewModelProvider
import com.anda.myapplication.data.viewmodel.MainActivityViewModel
import dagger.Module

@Module
class FactoryModule{

    fun provideMainActivityViewModelFactory(factory:MainActivityViewModel.MainActivityViewModelFactory)
    : ViewModelProvider.Factory = factory
}