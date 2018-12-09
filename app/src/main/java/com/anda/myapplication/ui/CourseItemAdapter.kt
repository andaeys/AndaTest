package com.anda.myapplication.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anda.myapplication.R
import com.anda.myapplication.data.Course
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_course.view.*

class CourseItemAdapter : RecyclerView.Adapter<CourseItemAdapter.CourseViewHolder>(){

   private lateinit var listCOurse:List<Course>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_course, parent, false))
    }

    override fun getItemCount(): Int {
        return if(::listCOurse.isInitialized) listCOurse.size else 0
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        var course = listCOurse[position]
        holder.bind(course)
    }

    fun updateCourseList(courseList: List<Course>){
        this.listCOurse = courseList
        notifyDataSetChanged()
    }


    class CourseViewHolder(val view: View): RecyclerView.ViewHolder(view){
        var title = view.title
        var presenter = view.presenter
        var desc = view.description
        var thumbImg = view.thumbnail
        fun bind(course: Course){
            title.text = course.title
            presenter.text = course.presenter_name
            desc.text = course.description
            Glide.with(view)
                    .load(course.thumbnail_url)
                    .into(thumbImg)
        }
    }
}