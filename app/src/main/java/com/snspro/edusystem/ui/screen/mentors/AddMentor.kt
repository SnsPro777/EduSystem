package com.snspro.edusystem.ui.screen.mentors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.snspro.edusystem.database.EducationService
import com.snspro.edusystem.model.Mentor
import com.snspro.edusystem.ui.common.EduOutlinedTextField
import com.snspro.edusystem.ui.common.TopBarIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMentorScreen(
   database: EducationService,
   onBackClick: () -> Unit,
   actionClick: (Mentor) -> Unit,
   id: Long,
   actionType: String
) {
   val oldMentor = database.getMentorById(id)
   Scaffold(
      modifier = Modifier.fillMaxSize(),
      topBar = {
         Row(
            modifier = Modifier
               .fillMaxWidth()
               .height(56.dp)
               .background(MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically,
         ) {
            Spacer(modifier = Modifier.width(18.dp))
            TopBarIcon(onClick = onBackClick, icon =Icons.Default.ArrowBack)
            Text(
               text = if (actionType == "add") "Yangi Mentor" else "Ma'lumotlarni yangilash",
               textAlign = TextAlign.Center,
               style = MaterialTheme.typography.titleLarge,
               color = MaterialTheme.colorScheme.onPrimary,
               modifier = Modifier.weight(1f)
            )
         }
      }
   ) { innerPadding ->
      Column(
         modifier = Modifier
            .fillMaxHeight()
            .padding(innerPadding)
      ) {
         var firstName by remember {
            mutableStateOf(if(actionType == "add") "" else oldMentor.firstName)
         }
         var lastName by remember {
            mutableStateOf(if(actionType == "add") "" else oldMentor.lastName)
         }
         var profession by remember {
            mutableStateOf(if(actionType == "add") "" else oldMentor.profession)
         }
         var level by remember {
            mutableStateOf(if(actionType == "add") "" else oldMentor.level)
         }
         var description by remember {
            mutableStateOf(if(actionType == "add") "" else oldMentor.description)
         }
         EduOutlinedTextField(
            value = firstName,
            onValueChange = {
               firstName = it
            },
            label = "Familiyasi",
            modifier = Modifier.height(66.dp)
         )
         EduOutlinedTextField(
            value = lastName,
            onValueChange = {
               lastName = it
            },
            label = "Ismi",
            modifier = Modifier.height(66.dp)
         )
         EduOutlinedTextField(
            value = profession,
            onValueChange = {
               profession = it
            },
            label = "Ilmiy sohasi",
            modifier = Modifier.height(66.dp)
         )
         EduOutlinedTextField(
            value = level,
            onValueChange = {
               level = it
            },
            label = "Tajriba darajasi",
            modifier = Modifier.height(66.dp)
         )
         EduOutlinedTextField(
            value = description,
            onValueChange = {
               description = it
            },
            label = "Tarjimai hol",
            maxLines = 100,
            modifier = Modifier.weight(1f)
         )
         Spacer(modifier = Modifier.height(10.dp))
         Button(
            onClick = {
               actionClick(Mentor(
                  firstName = firstName,
                  lastName = lastName,
                  profession = profession,
                  level = level,
                  description = description))
            },
            modifier = Modifier
               .align(Alignment.End)
               .padding(end = 20.dp),
            shape = RoundedCornerShape(5.dp)
         ) {
            Text(text = "Saqlash", style = MaterialTheme.typography.titleLarge)
         }
         Spacer(modifier = Modifier.height(10.dp))

      }
   }
}


