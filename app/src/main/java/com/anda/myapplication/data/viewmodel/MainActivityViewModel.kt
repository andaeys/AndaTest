package com.anda.myapplication.data.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.anda.myapplication.data.Course
import com.anda.myapplication.data.CourseRepository
import io.reactivex.Observable
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(val repository: CourseRepository) : ViewModel(){

    fun getCOurseListItemObservable() : Observable<List<Course>> = repository.getCourseListObservable()

    fun loadCourseList(){
        //doing costly operation
        repository.loadCourseList()
    }

    class MainActivityViewModelFactory @Inject constructor(val viewModel: MainActivityViewModel) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
                return viewModel as T
            }
            throw Exception("Unknown class")
        }
    }
}