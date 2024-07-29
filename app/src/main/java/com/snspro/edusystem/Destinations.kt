package com.snspro.edusystem

import kotlinx.serialization.Serializable


@Serializable
object Home

@Serializable
object Courses
@Serializable
object Groups
@Serializable
object Mentors

// todo: course routes
@Serializable
object AllCourses
@Serializable
data class CourseDetail(val courseId: Long)
@Serializable
data class AddStudent(val courseId: Long)

// todo: Groups routes
@Serializable
object CourseGroups
@Serializable
data class AllGroups(val courseId: Long)
@Serializable
object GroupDetail

// todo: Mentor routes
@Serializable
object AllMentors
@Serializable
data class AddMentor(val action: String, val id: Long)
@Serializable
data class MentorDetail(val mentorId: Long)





