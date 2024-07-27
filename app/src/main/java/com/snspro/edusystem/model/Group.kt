package com.snspro.edusystem.model

data class Group(
   val id:Long = -1,
   val name: String = "",
   val daysOfWeek: String = "",
   val timeOfDay: String = "",
   val isOpen: String = "",
   val courseId: Long = -1,
   val mentorId: Long = -1
)
