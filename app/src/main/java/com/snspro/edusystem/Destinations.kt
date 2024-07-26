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
object AddMentor
@Serializable
data class MentorDetail(val mentorId: Long)





