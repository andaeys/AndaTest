package com.anda.myapplication.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.anda.myapplication.data.Course
import io.reactivex.Single

@Dao
interface CourseDao{

    @Query("SELECT * FROM   COURSES")
    fun getCourseList(): Single<List<Course>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(course: Course)
}