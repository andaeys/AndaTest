package com.anda.myapplication.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "courses")
data class Course(
        @PrimaryKey
        val title: String,
        val presenter_name: String,
        val description: String,
        val thumbnail_url: String,
        val video_url: String,
        val video_duration: Int
)