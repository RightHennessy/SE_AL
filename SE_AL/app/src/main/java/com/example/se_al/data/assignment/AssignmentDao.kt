package com.example.se_al.data.assignment

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AssignmentDao {
    // 과제를 직접 추가할 때
    @Insert
    suspend fun insert(assignment: Assignment)

    @Query("SELECT * FROM Assignment")
    fun getAll(): LiveData<List<Assignment>>

    @Query("select * from Assignment where course_id = :courseId")
    fun getAssignmentsInCourse(courseId: String): LiveData<List<Assignment>>
}