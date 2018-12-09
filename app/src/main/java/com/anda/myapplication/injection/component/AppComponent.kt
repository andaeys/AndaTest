package com.anda.myapplication.injection.component

import com.anda.myapplication.CourseApplication
import com.anda.myapplication.injection.module.ActivityBuilder
import com.anda.myapplication.injection.module.AppModule
import com.anda.myapplication.injection.module.FactoryModule
import com.anda.myapplication.injection.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(modules = arrayOf(AndroidInjectionModule::class, NetworkModule::class, AppModule::class, ActivityBuilder::class, FactoryModule::class))
@Singleton
interface AppComponent {

    fun inject(app:CourseApplication)
}