package com.anda.myapplication.injection.module

import com.anda.myapplication.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder{

    @ContributesAndroidInjector
    abstract fun contributeToMainActivity(): MainActivity
}