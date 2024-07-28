package com.snspro.edusystem.ui.screen.courses

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.snspro.edusystem.database.EducationService
import com.snspro.edusystem.model.Course
import com.snspro.edusystem.ui.common.EduOutlinedTextField
import com.snspro.edusystem.ui.common.EduScaffold
import com.snspro.edusystem.ui.common.TopBarIcon
import com.snspro.edusystem.ui.theme.PurpleGrey80

@Composable
fun AllCoursesScreen(
   database: EducationService,
   onBackClick: () -> Unit,
   onCourseItemClick: (Course) -> Unit
) {
   var refreshCount by remember {
      mutableStateOf(2)
   }
   EduScaffold(
      onBackClick = onBackClick,
      title = "Barcha Kurslar",
      icons = {
         var showDialog by remember {
            mutableStateOf(false)
         }
         if (showDialog) {
            AddCourseDialog(database) {
               showDialog = false
               refreshCount += 1
            }
         }
         TopBarIcon(onClick = { showDialog = true }, icon = Icons.Default.Add)
      }
   ) {
      if (refreshCount != 1) {

         val courses = database.getAllCourse()
         if (courses.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
               Text(text = "Kurslar mavjud emas", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold))
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


}

@Composable
fun CourseItem(
   it: Course,
   onCourseItemClick: (Course) -> Unit
) {
   Row(
      modifier = Modifier
         .fillMaxWidth()
         .height(60.dp)
         .padding(horizontal = 7.dp)
         .clip(shape = RoundedCornerShape(5.dp))
         .background(MaterialTheme.colorScheme.primary)
         .clickable {
            onCourseItemClick(it)
         },
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
   ) {
      Column(
         modifier = Modifier.padding(start = 15.dp)
      ) {
         Text(
            text = it.getFirstWord(),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
         )
         Text(
            text = "Development",
            style = MaterialTheme.typography.labelLarge,
            color = PurpleGrey80
         )
      }
      Row {
         Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(15.dp)
         )
         Spacer(modifier = Modifier.width(10.dp))
      }

   }
}


@Composable
private fun AddCourseDialog(
   database: EducationService,
   onCloseRequest: () -> Unit
) {
   Dialog(onDismissRequest = onCloseRequest) {
      Card {
         var name by remember {
            mutableStateOf("")
         }
         var description by remember {
            mutableStateOf("")
         }
         Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally
         ) {
            Text(
               text = "Yangi Kurs qo'shish",
               style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            EduOutlinedTextField(
               value = name,
               onValueChange = { name = it },
               label = "name",
               modifier = Modifier.height(66.dp)
            )
            EduOutlinedTextField(
               value = description,
               onValueChange = { description = it },
               label = "description",
               maxLines = Int.MAX_VALUE,
               modifier = Modifier.height(200.dp)
            )
            Row(modifier = Modifier.align(Alignment.End)) {
               TextButton(onClick = onCloseRequest) {
                  Icon(imageVector = Icons.Default.Close, contentDescription = null)
                  Spacer(modifier = Modifier.width(15.dp))
                  Text(text = "Yopish", color = MaterialTheme.colorScheme.secondary)
               }
               TextButton(onClick = {
                  database.addCourse(Course(1, name, description))
                  onCloseRequest()
               }) {
                  Icon(imageVector = Icons.Default.Add, contentDescription = null)
                  Spacer(modifier = Modifier.width(15.dp))
                  Text(text = "Qo'shish", color = MaterialTheme.colorScheme.secondary)
               }
            }
         }

      }
   }
}