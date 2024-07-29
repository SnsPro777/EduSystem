package com.snspro.edusystem.ui.screen.groups

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.snspro.edusystem.database.EducationService
import com.snspro.edusystem.model.Course
import com.snspro.edusystem.ui.common.EduScaffold
import com.snspro.edusystem.ui.screen.courses.CourseItem

@Composable
fun CourseGroupsScreen(
   database: EducationService,
   onBackClick: () -> Unit,
   onCourseItemClick: (Course) -> Unit
) {
   EduScaffold(
      onBackClick = onBackClick,
      title = "Barcha Kurslar",
      icons = { }
   ) {
      val courses = database.getAllCourse()
      if (courses.isEmpty()) {
         Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
               text = "Kurslar mavjud emas",
               style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold)
            )
         }
      } else {
         LazyColumn(
            modifier = Modifier
               .fillMaxSize()
               .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
         ) {
            items(courses) {
               CourseItem(it, onCourseItemClick)
            }
         }
      }
   }
}