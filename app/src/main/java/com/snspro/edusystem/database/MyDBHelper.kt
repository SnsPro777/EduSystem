package com.snspro.edusystem.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.snspro.edusystem.model.Mentor

class MyDBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), EducationService {

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
      const val GROUP_COURSE_ID = "course_id"
      const val GROUP_MENTOR_ID = "mentor_id"

      // TODO: student table fields
      const val STUDENT_TABLE = "student"
      const val STUDENT_ID = "id"
      const val STUDENT_NAME = "name"
      const val STUDENT_GROUP_ID = "group_id"
   }


   override fun onCreate(db: SQLiteDatabase?) {
      val createCourseQuery =
         "create table $COURSE_TABLE (id integer not null primary key autoincrement, name text not null, description text not null)"
      val createMentorQuery =
         "create table $MENTOR_TABLE (id integer not null primary key autoincrement, first_name text not null, last_name text not null, profession text not null, level text not null, description text not null)"
      val createGroupQuery =
         "create table $GROUP_TABLE (id integer not null primary key autoincrement, name text not null, course_id integer not null, mentor_id integer not null, foreign key(course_id) references course(id), foreign key(mentor_id) references mentor(id))"
      val createStudentQuery =
         "create table $STUDENT_TABLE (id integer not null primary key autoincrement, name text not null, group_id integer not null, foreign key(group_id) references group1(id))"

      //db?.execSQL(createCourseQuery)
      db?.execSQL(createMentorQuery)
      //db?.execSQL(createGroupQuery)
      //db?.execSQL(createStudentQuery)
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
      if (cursor.moveToFirst()){
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
      if (cursor.moveToFirst()){
         val id = cursor.getLong(0)
         val firstName = cursor.getString(1)
         val lastName = cursor.getString(2)
         val profession = cursor.getString(3)
         val level = cursor.getString(4)
         val description = cursor.getString(5)
         return Mentor(id, firstName, lastName, profession, level, description)
      }
      return Mentor(1,"","", "", "", "error")
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
}