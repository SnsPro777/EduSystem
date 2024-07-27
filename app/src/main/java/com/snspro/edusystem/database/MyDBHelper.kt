package com.snspro.edusystem.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.snspro.edusystem.model.Course
import com.snspro.edusystem.model.Group
import com.snspro.edusystem.model.Mentor
import com.snspro.edusystem.model.Student

class MyDBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
   EducationService {

   companion object {
      const val DB_NAME = "edusystemdb"
      const val DB_VERSION = 1

      // TODO: course table fields
      const val COURSE_TABLE = "course"
      const val COURSE_ID = "id"
      const val COURSE_NAME = "name"
      const val COURSE_DESCRIPTION = "description"

      // TODO: mentor table fields
      const val MENTOR_TABLE = "mentor"
      const val MENTOR_ID = "id"
      const val MENTOR_FIRST_NAME = "first_name"
      const val MENTOR_LAST_NAME = "last_name"
      const val MENTOR_LEVEL = "level"
      const val MENTOR_PROFESSION = "profession"
      const val MENTOR_DESCRIPTION = "description"

      // TODO: group table fields
      const val GROUP_TABLE = "group1"
      const val GROUP_ID = "id"
      const val GROUP_NAME = "name"
      const val GROUP_DAY_OF_WEEK = "day_of_week"
      const val GROUP_TIME_OF_DAY = "time_of_day"
      const val GROUP_IS_OPEN = "is_open"
      const val GROUP_COURSE_ID = "course_id"
      const val GROUP_MENTOR_ID = "mentor_id"

      // TODO: student table fields
      const val STUDENT_TABLE = "student"
      const val STUDENT_ID = "id"
      const val STUDENT_FIRST_NAME = "first_name"
      const val STUDENT_LAST_NAME = "last_name"
      const val STUDENT_GROUP_ID = "group_id"
   }


   override fun onCreate(db: SQLiteDatabase?) {
      val createCourseQuery =
         "create table $COURSE_TABLE (id integer not null primary key autoincrement, name text not null, description text not null)"
      val createMentorQuery =
         "create table $MENTOR_TABLE (id integer not null primary key autoincrement, first_name text not null, last_name text not null, profession text not null, level text not null, description text not null)"
      val createGroupQuery =
         "create table $GROUP_TABLE (id integer not null primary key autoincrement, name text not null, day_of_week text not null, time_of_day text not null, is_open text not null, course_id integer not null, mentor_id integer not null, foreign key(course_id) references course(id), foreign key(mentor_id) references mentor(id))"
      val createStudentQuery =
         "create table $STUDENT_TABLE (id integer not null primary key autoincrement, first_name text not null,last_name text not null, group_id integer not null, foreign key(group_id) references group1(id))"

      db?.execSQL(createCourseQuery)
      db?.execSQL(createMentorQuery)
      db?.execSQL(createGroupQuery)
      db?.execSQL(createStudentQuery)
   }

   override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

   }

   override fun addMentor(mentor: Mentor): Long {
      val database = this.writableDatabase
      val contentValues = ContentValues()
      contentValues.put(MENTOR_FIRST_NAME, mentor.firstName)
      contentValues.put(MENTOR_LAST_NAME, mentor.lastName)
      contentValues.put(MENTOR_PROFESSION, mentor.profession)
      contentValues.put(MENTOR_LEVEL, mentor.level)
      contentValues.put(MENTOR_DESCRIPTION, mentor.description)
      return database.insert(MENTOR_TABLE, null, contentValues)
   }

   override fun getAllMentors(): List<Mentor> {
      val result = ArrayList<Mentor>()
      val database = this.readableDatabase
      val cursor = database.rawQuery("select * from mentor", null)
      if (cursor.moveToFirst()) {
         do {
            val id = cursor.getLong(0)
            val firstName = cursor.getString(1)
            val lastName = cursor.getString(2)
            val profession = cursor.getString(3)
            val level = cursor.getString(4)
            val description = cursor.getString(5)
            result.add(Mentor(id, firstName, lastName, profession, level, description))
         } while (cursor.moveToNext())
      }
      database.close()
      return result
   }

   override fun getMentorById(id: Long): Mentor {
      val database = this.readableDatabase
      val cursor = database.rawQuery("select * from mentor where id = $id", null)
      if (cursor.moveToFirst()) {
         val id = cursor.getLong(0)
         val firstName = cursor.getString(1)
         val lastName = cursor.getString(2)
         val profession = cursor.getString(3)
         val level = cursor.getString(4)
         val description = cursor.getString(5)
         return Mentor(id, firstName, lastName, profession, level, description)
      }
      return Mentor(1, "", "", "", "", "error")
   }

   override fun editMentor(id: Long, mentor: Mentor) {
      val database = this.writableDatabase
      val contentValues = ContentValues()
      contentValues.put(MENTOR_FIRST_NAME, mentor.firstName)
      contentValues.put(MENTOR_LAST_NAME, mentor.lastName)
      contentValues.put(MENTOR_PROFESSION, mentor.profession)
      contentValues.put(MENTOR_LEVEL, mentor.level)
      contentValues.put(MENTOR_DESCRIPTION, mentor.description)
      database.update(
         MENTOR_TABLE,
         contentValues,
         "$MENTOR_ID = ?",
         arrayOf(id.toString())
      )
   }

   override fun deleteMentor(id: Long) {
      val database = this.writableDatabase
      database.delete(MENTOR_TABLE, "$MENTOR_ID = ?", arrayOf(id.toString()))
   }

   override fun addCourse(course: Course): Long {
      val database = this.writableDatabase
      val contentValues = ContentValues()
      contentValues.put(COURSE_NAME, course.name)
      contentValues.put(COURSE_DESCRIPTION, course.id)
      return database.insert(COURSE_TABLE, null, contentValues)
   }

   override fun getAllCourse(): List<Course> {
      val result = ArrayList<Course>()
      val database = this.readableDatabase
      val cursor = database.rawQuery("select * from $COURSE_TABLE", null, null)
      if (cursor.moveToFirst()) {
         do {
            val id = cursor.getLong(0)
            val name = cursor.getString(1)
            val description = cursor.getString(2)
            result.add(Course(id, name, description))
         } while (cursor.moveToNext())
      }
      cursor.close()
      return result
   }

   override fun getCourseById(id: Long): Course {
      val database = this.readableDatabase
      val cursor = database.rawQuery("select * from $COURSE_TABLE where id == $id", null)
      if (cursor.moveToFirst()) {
         val id = cursor.getLong(0)
         val name = cursor.getString(1)
         val description = cursor.getString(2)
         return Course(id, name, description)
      }
      cursor.close()
      return Course(-1, "error", "error")
   }

   override fun deleteCourse(id: Long) {
      val database = this.writableDatabase
      database.delete(COURSE_TABLE, "$COURSE_ID == ?", arrayOf(id.toString()))
   }

   override fun addGroup(group: Group) {
      val database = this.writableDatabase
      val contentValues = ContentValues()
      contentValues.put(GROUP_NAME, group.name)
      contentValues.put(GROUP_DAY_OF_WEEK, group.daysOfWeek)
      contentValues.put(GROUP_TIME_OF_DAY, group.timeOfDay)
      contentValues.put(GROUP_IS_OPEN, group.isOpen)
      contentValues.put(GROUP_COURSE_ID, group.courseId)
      contentValues.put(GROUP_MENTOR_ID, group.mentorId)
      database.insert(GROUP_TABLE, null, contentValues)
   }

   override fun getOpenedGroupsByCourseId(courseId: Long): List<Group> {
      val database = this.readableDatabase
      val result = ArrayList<Group>()
      val cursor = database.rawQuery(
         "select * from $GROUP_TABLE, $COURSE_TABLE where $GROUP_IS_OPEN = 'opened' and $GROUP_COURSE_ID = $courseId",
         null
      )
      if (cursor.moveToFirst()){
         val id = cursor.getLong(0)
         val name = cursor.getString(1)
         val dayOfWeek = cursor.getString(2)
         val timeOfDay = cursor.getString(3)
         val isOpen = cursor.getString(4)
         val courseId = cursor.getLong(5)
         val mentorId = cursor.getLong(6)
         result.add(Group(id, name, dayOfWeek, timeOfDay, isOpen, courseId, mentorId))
      }
      cursor.close()
      return result
   }

   override fun getOPeningGroupsByCourseId(courseId: Long): List<Group> {
      val database = this.readableDatabase
      val result = ArrayList<Group>()
      val cursor = database.rawQuery(
         "select * from $GROUP_TABLE, $COURSE_TABLE where $GROUP_IS_OPEN = 'opening' and $GROUP_COURSE_ID = $courseId",
         null
      )
      if (cursor.moveToFirst()){
         val id = cursor.getLong(0)
         val name = cursor.getString(1)
         val dayOfWeek = cursor.getString(2)
         val timeOfDay = cursor.getString(3)
         val isOpen = cursor.getString(4)
         val courseId1 = cursor.getLong(5)
         val mentorId = cursor.getLong(6)
         result.add(Group(id, name, dayOfWeek, timeOfDay, isOpen, courseId1, mentorId))
      }
      cursor.close()
      return result
   }

   override fun getGroupById(id: Long): Group {
      val database = this.readableDatabase
      val cursor = database.rawQuery(
         "select * from $GROUP_TABLE where $GROUP_ID = $id",
         null
      )
      if (cursor.moveToFirst()){
         val id1 = cursor.getLong(0)
         val name = cursor.getString(1)
         val dayOfWeek = cursor.getString(2)
         val timeOfDay = cursor.getString(3)
         val isOpen = cursor.getString(4)
         val courseId = cursor.getLong(5)
         val mentorId = cursor.getLong(6)
         cursor.close()
         return Group(id1, name, dayOfWeek, timeOfDay, isOpen, courseId, mentorId)
      }
      cursor.close()
      return Group()
   }

   override fun getGroupsByCourseId(courseId: Long): List<Group> {
      val database = this.readableDatabase
      val result = ArrayList<Group>()
      val cursor =
         database.rawQuery("select * from $GROUP_TABLE where $GROUP_COURSE_ID = $courseId", null)
      if (cursor.moveToFirst()){
         val id = cursor.getLong(0)
         val name = cursor.getString(1)
         val dayOfWeek = cursor.getString(2)
         val timeOfDay = cursor.getString(3)
         val isOpen = cursor.getString(4)
         val courseId1 = cursor.getLong(5)
         val mentorId = cursor.getLong(6)
         result.add(Group(id, name, dayOfWeek, timeOfDay, isOpen, courseId1, mentorId))
      }
      cursor.close()
      return result
   }

   override fun getGroupsByMentorId(mentorId: Long): List<Group> {
      val database = this.readableDatabase
      val result = ArrayList<Group>()
      val cursor =
         database.rawQuery("select * from $GROUP_TABLE where $GROUP_MENTOR_ID = $mentorId", null)
      if (cursor.moveToFirst()){
         val id = cursor.getLong(0)
         val name = cursor.getString(1)
         val dayOfWeek = cursor.getString(2)
         val timeOfDay = cursor.getString(3)
         val isOpen = cursor.getString(4)
         val courseId1 = cursor.getLong(5)
         val mentorId1 = cursor.getLong(6)
         result.add(Group(id, name, dayOfWeek, timeOfDay, isOpen, courseId1, mentorId1))
      }
      cursor.close()
      return result
   }

   override fun editGroup(id: Long, newGroup: Group) {
      val database = this.writableDatabase
      val contentValues = ContentValues()
      contentValues.put(GROUP_NAME, newGroup.name)
      contentValues.put(GROUP_DAY_OF_WEEK, newGroup.daysOfWeek)
      contentValues.put(GROUP_TIME_OF_DAY, newGroup.timeOfDay)
      contentValues.put(GROUP_IS_OPEN, newGroup.isOpen)
      contentValues.put(GROUP_COURSE_ID, newGroup.courseId)
      contentValues.put(GROUP_MENTOR_ID, newGroup.mentorId)
      database.update(
         GROUP_TABLE,
         contentValues,
         "$GROUP_ID = ?",
         arrayOf(id.toString())
      )
   }

   override fun deleteGroup(id: Long) {
      val database = this.writableDatabase
      database.delete(
         GROUP_TABLE,
         "$GROUP_ID = ?",
         arrayOf(id.toString())
      )
   }

   override fun addStudent(student: Student) {
      val database = this.writableDatabase
      val contentValues = ContentValues()
      contentValues.put(STUDENT_FIRST_NAME, student.firstName)
      contentValues.put(STUDENT_LAST_NAME, student.lastName)
      contentValues.put(STUDENT_GROUP_ID, student.groupId)
      database.insert(STUDENT_TABLE, null, contentValues)
   }

   override fun deleteStudent(id: Long) {
      val database = this.writableDatabase
      database.delete(STUDENT_TABLE, "$STUDENT_ID = ?", arrayOf(id.toString()))
   }

   override fun getStudentCountByGroupId(groupId: Long): Int {
      val database = this.readableDatabase
      val cursor = database.rawQuery("select count(*) from $STUDENT_TABLE where $STUDENT_GROUP_ID = $groupId", null)
      if (cursor.moveToFirst()){
         return cursor.getInt(0)
      }
      cursor.close()
      return 0
   }

   override fun getStudentsByGroupId(groupId: Long): List<Student> {
      val database = this.readableDatabase
      val result = ArrayList<Student>()
      val cursor =
         database.rawQuery("select * from $STUDENT_TABLE where $STUDENT_GROUP_ID = $groupId", null)
      if (cursor.moveToFirst()){
         do {
            val id = cursor.getLong(0)
            val firsName = cursor.getString(1)
            val lastName = cursor.getString(2)
            val groupId1 = cursor.getLong(3)
            result.add(Student(id, firsName, lastName, groupId1))
         }while (cursor.moveToNext())
      }
      return result
   }
}