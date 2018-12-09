package com.anda.myapplication

import android.app.Activity
import android.app.Application
import com.anda.myapplication.injection.component.DaggerAppComponent
import com.anda.myapplication.injection.module.AppModule
import com.anda.myapplication.injection.module.FactoryModule
import com.anda.myapplication.injection.module.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CourseApplication: Application(), HasActivityInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .networkModule(NetworkModule(BuildConfig.BASE_URL))
                .appModule(AppModule(this))
                .factoryModule(FactoryModule())
                .build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}