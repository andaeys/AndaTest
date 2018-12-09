package com.anda.myapplication.injection.module

import android.app.Application
import android.arch.persistence.room.Room
import com.anda.myapplication.data.local.CourseDao
import com.anda.myapplication.data.local.CourseDatabase
import com.anda.myapplication.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app:Application){

    @Provides
    @Singleton
    fun provideApplication():Application = app

    @Provides
    @Singleton
    fun provideCourseDatabase(app: Application):CourseDatabase =
            Room.databaseBuilder(app, CourseDatabase::class.java, "course_db")
                    .build()

    @Provides
    @Singleton
    fun provideCourseDao(courseDatabase: CourseDatabase):CourseDao =
            courseDatabase.courseDao()

    @Provides
    @Singleton
    fun provideUtils(app: Application) : Utils = Utils(app.applicationContext)
}