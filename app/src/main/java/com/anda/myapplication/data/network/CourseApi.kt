package com.anda.myapplication.data.network

import com.anda.myapplication.data.Course
import io.reactivex.Observable
import retrofit2.http.GET

interface CourseApi{

    @GET("playlist.json")
    fun getCourseList() : Observable<List<Course>>
}