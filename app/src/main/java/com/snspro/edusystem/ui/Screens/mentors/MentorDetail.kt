package com.snspro.edusystem.ui.Screens.mentors

import androidx.compose.runtime.Composable
import com.snspro.edusystem.database.EducationService

@Composable
fun MentorDetailScreen(
   database: EducationService,
   id: Long
) {
   val mentor = database.getMentorById(id)
}