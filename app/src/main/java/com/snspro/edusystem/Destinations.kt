package com.snspro.edusystem

import com.snspro.edusystem.model.Mentor
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
object CourseDetail
@Serializable
object AddStudent

// todo: Groups routes
@Serializable
object CourseGroups
@Serializable
object AllGroups
@Serializable
object GroupDetail

// todo: Mentor routes
@Serializable
object AllMentors
@Serializable
data class AddMentor(val action: String, val id: Long)
@Serializable
data class MentorDetail(val mentorId: Long)





