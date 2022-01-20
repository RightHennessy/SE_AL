package com.example.se_al.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.se_al.data.announcement.Announcement
import com.example.se_al.data.announcement.AnnouncementDao
import com.example.se_al.data.assignment.Assignment
import com.example.se_al.data.assignment.AssignmentDao
import com.example.se_al.data.calendar.Calendar
import com.example.se_al.data.calendar.CalendarDao
import com.example.se_al.data.children_content.ChildrenContent
import com.example.se_al.data.children_content.ChildrenContentDao
import com.example.se_al.data.content.Content
import com.example.se_al.data.content.ContentDao
import com.example.se_al.data.course.Course
import com.example.se_al.data.course.CourseDao
import com.example.se_al.data.exam.Exam
import com.example.se_al.data.exam.ExamDao
import com.example.se_al.data.lecture.Lecture
import com.example.se_al.data.lecture.LectureDao
import com.example.se_al.data.menu.Menu
import com.example.se_al.data.menu.MenuDao
import com.example.se_al.data.sub_lecture.SubLecture
import com.example.se_al.data.sub_lecture.SubLectureDao
import com.example.se_al.data.user.DAO
import com.example.se_al.data.user.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Database

@Database(entities = arrayOf(User::class, Course::class, Menu::class ,Content::class, ChildrenContent::class, Announcement::class, Calendar::class, Assignment::class, Exam::class, Lecture::class, SubLecture::class), version = 1) //
@TypeConverters(Converters::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): DAO
    abstract fun courseDao(): CourseDao
    abstract fun menuDao(): MenuDao
    abstract fun contentDao(): ContentDao
    abstract fun childrenDao(): ChildrenContentDao
    abstract fun announcementDao(): AnnouncementDao
    abstract fun calendarDao(): CalendarDao
    abstract fun assignmentDao(): AssignmentDao
    abstract fun examDao(): ExamDao
    abstract fun lectureDao(): LectureDao
    abstract fun subLectureDao(): SubLectureDao

    // 싱글톤 DB
    companion object {
        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context/*, scope:CoroutineScope*/): UserDatabase? {
            // null이면 database 생성
            if (instance == null) {
                synchronized(UserDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user-database"
                    )//.addCallback(UserDatabaseCallback(scope))
                     .fallbackToDestructiveMigration()
                     .build()
                }
            }
            return instance
        }

        /*
        // DataBase 첫 생성 시 행동
        private class UserDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                instance?.let { database ->
                    scope.launch {
                        populateDatabase(database.assignmentDao())
                    }
                }
            }
            suspend fun populateDatabase(assignmentDao: AssignmentDao) {
                //userDao.deleteAll()
                // Add User
                assignmentDao.insert(Assignment("ㅇㄹ","ㅇㄹ","ㅇㄹ","ㅇㄹ","ㅇㄹ","ㅇㄹ","ㅇㄹ","ㅇㄹ","ㅇㄹ","ㅇㄹ"))
            }
        }
        */
    }

}