package com.snspro.edusystem.database

import com.snspro.edusystem.model.Mentor

interface EducationService {
   fun addMentor(mentor: Mentor): Long

   fun getAllMentors(): List<Mentor>

   fun getMentorById(id: Long): Mentor

   fun editMentor(id: Long, mentor: Mentor)

   fun deleteMentor(id: Long)
}