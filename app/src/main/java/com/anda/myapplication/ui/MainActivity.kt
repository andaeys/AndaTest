package com.anda.myapplication.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.anda.myapplication.R
import com.anda.myapplication.data.Course
import com.anda.myapplication.data.viewmodel.MainActivityViewModel
import com.uber.autodispose.lifecycle.autoDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: MainActivityViewModel.MainActivityViewModelFactory

    private lateinit var viewModel: MainActivityViewModel
    private var adapter = CourseItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
       recyclerView.adapter = adapter
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        observeCourseList()
        viewModel.loadCourseList()
    }

    private fun observeCourseList() {
        viewModel.getCOurseListItemObservable()
                .doOnSubscribe { onLoadCourseStarted() }
                .doOnTerminate { onLoadCourseTerminated() }
                .autoDisposable(androidLifecycleScopeProvider)
                .subscribe(
                        {list -> onLoadCourseSuccess(list)},
                        {error -> onLoadCourseFailed(error)}
                )
    }

    private fun onLoadCourseSuccess(list: List<Course>?) {
        onLoadCourseTerminated()
        adapter.updateCourseList(list!!)
    }

    private fun onLoadCourseFailed(error: Throwable?) {
        onLoadCourseTerminated()
        Toast.makeText(applicationContext, error!!.message, Toast.LENGTH_SHORT).show()
    }

    private fun onLoadCourseStarted() {
        progressBar.visibility = View.VISIBLE
    }

    private fun onLoadCourseTerminated() {
        progressBar.visibility = View.GONE
    }
}
