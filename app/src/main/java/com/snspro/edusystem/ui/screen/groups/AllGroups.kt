package com.snspro.edusystem.ui.screen.groups

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.snspro.edusystem.database.EducationService
import com.snspro.edusystem.model.Course
import com.snspro.edusystem.model.Group
import com.snspro.edusystem.model.Mentor
import com.snspro.edusystem.ui.common.EduMentorSpinner
import com.snspro.edusystem.ui.common.EduOutlinedTextField
import com.snspro.edusystem.ui.common.EduScaffold
import com.snspro.edusystem.ui.common.TopBarIcon
import com.snspro.edusystem.ui.screen.mentors.GroupsList

@Composable
fun AllGroupsScreen(
   database: EducationService,
   courseId: Long,
   onBackClick: () -> Unit,
   onGroupClick: (Long) -> Unit,
   onGroupViewClick: (Long) -> Unit,
   onGroupEditClick: (Long) -> Unit
) {
   val course = database.getCourseById(courseId)
   var groupType by remember {
      mutableStateOf("opened")
   }
   var refreshCount by remember {
      mutableStateOf(2)
   }
   EduScaffold(
      onBackClick = onBackClick,
      title = course.name,
      icons = {
         if (groupType == "opening") {
            var showDialog by remember {
               mutableStateOf(false)
            }
            if (showDialog) {
               AddGroupDialog(
                  onDismissClick = { showDialog = false },
                  database = database,
                  course = course
               )
            }
            TopBarIcon(
               onClick = { showDialog = true },
               icon = Icons.Default.Add
            )
         }
      }
   ) {
      var tabIndex by remember {
         mutableStateOf(0)
      }

      val tabs = listOf("Ochilgan guruhlar", "Ochilayotgan guruhlar")
      Column(modifier = Modifier.fillMaxWidth()) {
         TabRow(
            selectedTabIndex = tabIndex,
            containerColor = MaterialTheme.colorScheme.primary,
         ) {
            tabs.forEachIndexed { index: Int, title: String ->
               Tab(
                  text = { Text(text = title, color = MaterialTheme.colorScheme.onPrimary) },
                  selectedContentColor = Color.Green.copy(alpha = 0.5f),
                  selected = tabIndex == index,
                  onClick = {
                     groupType = if (index == 0) "opened" else "opening"
                     tabIndex = index
                  }
               )
            }
         }
         when (tabIndex) {
            0 -> OpenedGroups(
               database = database,
               courseId = course.id,
               onGroupClick = onGroupClick,
               onGroupViewClick = onGroupViewClick,
               onGroupEditClick = onGroupEditClick
            )

            1 -> {
               if (refreshCount != 1) {
                  OpeningGroups(
                     database = database,
                     courseId = course.id,
                     onGroupClick = onGroupClick,
                     onGroupViewClick = onGroupViewClick,
                     onGroupEditClick = {

                     }
                  )
               }
            }
         }
      }
   }
}

@Composable
fun AddGroupDialog(
   onDismissClick: () -> Unit,
   database: EducationService,
   course: Course,
   group: Group = Group(-1, "", "", "", "",-1,-1)
) {
   var groupNull = false
   if (group.id != -1L){
            groupNull = true
   }
   Dialog(onDismissRequest = { onDismissClick() }) {
      var name by remember {
         mutableStateOf(if (groupNull) "" else group.name)
      }
      var daysOfWeek by remember {
         mutableStateOf(if (groupNull) "" else group.daysOfWeek)
      }
      var timeOfDays by remember {
         mutableStateOf(if (groupNull) "" else group.timeOfDay)
      }
      var mentorId: Long by remember {
         mutableStateOf(if (groupNull) -1 else group.mentorId)
      }
      Card {
         Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally
         ) {
            Text(
               text = "Yangi Guruh ochish",
               style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            EduOutlinedTextField(
               value = name,
               onValueChange = { name = it },
               label = "Nomi",
               modifier = Modifier.height(66.dp)
            )
            EduOutlinedTextField(
               value = daysOfWeek,
               onValueChange = { daysOfWeek = it },
               label = "Hafta kunlari",
               modifier = Modifier.height(66.dp)
            )
            EduOutlinedTextField(
               value = timeOfDays,
               onValueChange = { timeOfDays = it },
               label = "Soat: ",
               modifier = Modifier.height(66.dp)
            )
            EduMentorSpinner(
               label = "Mentorni tanlang",
               items = database.getAllMentors(),
               onItemClick = {
                  mentorId = it.id
               },
               selectedMentor = if (groupNull) Mentor() else database.getMentorById(group.mentorId)
            )
            Row(modifier = Modifier.align(Alignment.End)) {
               TextButton(onClick = { onDismissClick() }) {
                  Icon(imageVector = Icons.Default.Close, contentDescription = null)
                  Spacer(modifier = Modifier.width(15.dp))
                  Text(text = "Yopish", color = MaterialTheme.colorScheme.secondary)
               }
               TextButton(onClick = {
                  database.addGroup(
                     Group(
                        1,
                        name,
                        daysOfWeek,
                        timeOfDays,
                        "opening",
                        course.id,
                        mentorId
                     )
                  )
                  onDismissClick()
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

@Composable
fun OpenedGroups(
   database: EducationService,
   courseId: Long,
   onGroupClick: (Long) -> Unit,
   onGroupViewClick: (Long) -> Unit,
   onGroupEditClick: (Long) -> Unit
) {
   GroupsList(
      database = database,
      groups = database.getOpenedGroupsByCourseId(courseId),
      onGroupClick = onGroupClick,
      onGroupViewClick = onGroupViewClick,
      onGroupEditClick = onGroupEditClick
   )
}

@Composable
fun OpeningGroups(
   database: EducationService,
   courseId: Long,
   onGroupClick: (Long) -> Unit,
   onGroupViewClick: (Long) -> Unit,
   onGroupEditClick: (Long) -> Unit
) {
   GroupsList(
      database = database,
      groups = database.getOPeningGroupsByCourseId(courseId),
      onGroupClick = onGroupClick,
      onGroupViewClick = onGroupViewClick,
      onGroupEditClick = onGroupEditClick
   )
}