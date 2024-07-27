package com.snspro.edusystem.database

import com.snspro.edusystem.model.Course
import com.snspro.edusystem.model.Group
import com.snspro.edusystem.model.Mentor
import com.snspro.edusystem.model.Student

interface EducationService {
   // todo: mentor actions
   fun addMentor(mentor: Mentor): Long

   fun getAllMentors(): List<Mentor>

   fun getMentorById(id: Long): Mentor

   fun editMentor(id: Long, mentor: Mentor)

   fun deleteMentor(id: Long)

   // todo: course actions
   fun addCourse(course: Course): Long

   fun getAllCourse(): List<Course>

   fun getCourseById(id: Long): Course

   fun deleteCourse(id: Long)

   // todo: group actions
   fun addGroup(group: Group)

   fun getOpenedGroupsByCourseId(courseId: Long): List<Group>

   fun getOPeningGroupsByCourseId(courseId: Long): List<Group>

   fun getGroupById(id: Long): Group

   fun getGroupsByCourseId(courseId: Long): List<Group>

   fun getGroupsByMentorId(mentorId: Long): List<Group>

   fun editGroup(id: Long, newGroup: Group)

   fun deleteGroup(id: Long)

   // todo: student actions
   fun addStudent(student: Student)

   fun deleteStudent(id: Long)

   fun getStudentCountByGroupId(groupId: Long): Int

   fun getStudentsByGroupId(groupId: Long): List<Student>


}