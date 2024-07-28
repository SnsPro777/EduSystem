package com.snspro.edusystem.ui.screen.courses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.snspro.edusystem.database.EducationService
import com.snspro.edusystem.model.Course
import com.snspro.edusystem.ui.common.EduScaffold
import com.snspro.edusystem.ui.common.TopBarIcon

@Composable
fun CourseDetailScreen(
   database: EducationService,
   courseId: Long,
   onBackClick: () -> Unit,
   addStudentClick: (Course) -> Unit
) {
   val course = database.getCourseById(courseId)
   EduScaffold(
      onBackClick = onBackClick,
      title = course.getFirstWord() + " development",
      icons = {
         TopBarIcon(onClick = {
            database.deleteCourse(courseId)
            onBackClick()
         }, icon = Icons.Default.Delete)
      }
   ) {
      Column {
         Column(
            modifier = Modifier
               .fillMaxWidth()
               .padding(15.dp)
               .weight(1f)
               .clip(shape = RoundedCornerShape(5.dp))
               .background(Color.Gray.copy(alpha = 0.2f))
         ) {
            Text(
               text = course.name,
               style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
               modifier = Modifier.padding(10.dp)
            )
            Divider(
               modifier = Modifier
                  .height(2.dp)
                  .fillMaxWidth()
                  .padding(horizontal = 10.dp),
               thickness = 2.dp,
               color = Color.Gray
            )
            Text(
               text = course.description,
               style = MaterialTheme.typography.titleMedium,
               modifier = Modifier.padding(10.dp)
            )
         }

         Button(
            onClick = {
               addStudentClick(course)
            },
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
               .padding(end = 20.dp, bottom = 25.dp)
               .align(Alignment.End)
         ) {
            Text(text = "Talaba qo'shish")
         }
      }


   }
}