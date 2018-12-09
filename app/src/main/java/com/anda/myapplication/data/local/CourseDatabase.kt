package com.anda.myapplication.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.anda.myapplication.data.Course

@Database(entities = arrayOf(Course::class), version = 1)
abstract class CourseDatabase: RoomDatabase(){

    abstract fun courseDao() : CourseDao
}