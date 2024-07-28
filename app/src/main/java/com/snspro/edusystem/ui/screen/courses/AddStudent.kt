package com.snspro.edusystem.ui.screen.courses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.snspro.edusystem.database.EducationService
import com.snspro.edusystem.model.Student
import com.snspro.edusystem.ui.common.EduGroupSpinner
import com.snspro.edusystem.ui.common.EduOutlinedTextField
import com.snspro.edusystem.ui.common.EduScaffold

@Composable
fun AddStudentScreen(
   database: EducationService,
   courseId: Long,
   onBackClick: () -> Unit,
) {
   EduScaffold(
      onBackClick = onBackClick,
      title = "Talaba qo'shish",
      icons = { }
   ) {
      val groups = database.getGroupsByCourseId(courseId)
      Column(
         modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
         verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
         var first_name by remember {
            mutableStateOf("")
         }
         var last_name by remember {
            mutableStateOf("")
         }
         var groupId: Long by remember {
            mutableStateOf(-1)
         }

         EduOutlinedTextField(
            value = first_name,
            onValueChange = { first_name = it },
            label = "Ismi"
         )
         EduOutlinedTextField(
            value = last_name,
            onValueChange = { last_name = it },
            label = "Familiyasi"
         )
         EduGroupSpinner(label = "Guruhni tanlang", items = groups, onItemClick = {
            groupId = it.id
         })
         Spacer(modifier = Modifier.weight(1f))
         Button(
            onClick = {
               database.addStudent(Student(1, first_name, last_name, groupId))
               onBackClick()
            },
            shape = RoundedCornerShape(5.dp)
         ) {
            Text(text = "Qo'shish", color = MaterialTheme.colorScheme.onPrimary)
         }
      }
   }
}