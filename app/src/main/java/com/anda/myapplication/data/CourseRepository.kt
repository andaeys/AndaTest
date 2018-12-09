package com.anda.myapplication.data

import com.anda.myapplication.data.local.CourseDao
import com.anda.myapplication.data.network.CourseApi
import com.anda.myapplication.utils.Utils
import com.jakewharton.rx.replayingShare
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class CourseRepository @Inject constructor(private val courseApi: CourseApi,
                                           private val courseDao: CourseDao,
                                           private val utils: Utils){

    //hot observable
    private var subject:PublishSubject<Unit> = PublishSubject.create()

    //fetch course list observable
    fun getCourseListObservable():Observable<List<Course>> =
            subject.concatMap { return@concatMap getCourseList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }.replayingShare()

    fun loadCourseList(){
        subject.onNext(Unit)
    }

    private fun getCourseListFromApi() : Observable<List<Course>>{
        return courseApi.getCourseList()
                .doOnNext { list-> list.forEach { courseDao.insertCourse(it) } }
    }

    private fun getCourseListFromDB(): Observable<List<Course>>{
        return courseDao.getCourseList().toObservable()
    }

    private fun getCourseList():Observable<List<Course>>{
        var observableFromApi:Observable<List<Course>>? = null
        if(utils.isOnline()){
            observableFromApi = getCourseListFromApi()
        }
        var observableFromDB = getCourseListFromDB()

        return if(utils.isOnline()){
            //concat from db and api into a single observable
            Observable.concatArrayEager(observableFromApi, observableFromDB)
        }else{
            observableFromDB
        }
    }
}