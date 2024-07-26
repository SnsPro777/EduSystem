package com.snspro.edusystem.ui.screens.mentors

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMentorScreen(
   database: EducationService,
   onBackClick: () -> Unit
) {
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
            IconButton(onClick = onBackClick) {
               Icon(
                  imageVector = Icons.Default.ArrowBack,
                  contentDescription = null,
                  modifier = Modifier.size(25.dp),
                  tint = MaterialTheme.colorScheme.onPrimary
               )
            }
            Text(
               text = "Yangi Mentor",
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
            mutableStateOf("")
         }
         var lastName by remember {
            mutableStateOf("")
         }
         var profession by remember {
            mutableStateOf("")
         }
         var level by remember {
            mutableStateOf("")
         }
         var description by remember {
            mutableStateOf("")
         }
         Field(
            value = firstName,
            onValueChange = {
               firstName = it
            },
            label = "Familiyasi"
         )
         Field(
            value = lastName,
            onValueChange = {
               lastName = it
            },
            label = "Ismi"
         )
         Field(
            value = profession,
            onValueChange = {
               profession = it
            },
            label = "Ilmiy sohasi"
         )
         Field(
            value = level,
            onValueChange = {
               level = it
            },
            label = "Tajriba darajasi"
         )
         Field(
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
               database.addMentor(Mentor(
                  firstName = firstName,
                  lastName = lastName,
                  profession = profession,
                  level = level,
                  description = description
               ))
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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Field(
   value: String,
   onValueChange: (String) -> Unit,
   label: String,
   maxLines: Int = 1,
   modifier: Modifier = Modifier
) {
   OutlinedTextField(
      modifier = modifier
         .fillMaxWidth()
         .height(56.dp)
         .padding(horizontal = 5.dp),
      value = value,
      onValueChange = onValueChange,
      label = {
         Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.background(Color.Transparent)
         )
      },
      textStyle = MaterialTheme.typography.titleLarge,
      maxLines = maxLines,
      shape = RoundedCornerShape(10.dp),
      colors = TextFieldDefaults.outlinedTextFieldColors(
         containerColor = Color.Gray.copy(alpha = 0.5f)
      )
   )
   Spacer(modifier = Modifier.height(6.dp))
}
