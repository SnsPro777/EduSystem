package com.snspro.edusystem.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.snspro.edusystem.model.Group
import com.snspro.edusystem.model.Mentor

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EduOutlinedTextField(
   value: String,
   onValueChange: (String) -> Unit,
   label: String,
   maxLines: Int = 1,
   modifier: Modifier = Modifier
) {
   OutlinedTextField(
      modifier = modifier
         .fillMaxWidth()
         .padding(horizontal = 5.dp),
      value = value,
      onValueChange = onValueChange,
      label = {
         Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
         )
      },
      textStyle = MaterialTheme.typography.titleLarge,
      maxLines = maxLines,
      shape = RoundedCornerShape(10.dp),
      colors = TextFieldDefaults.outlinedTextFieldColors(
         containerColor = Color.Gray.copy(alpha = 0.2f),
         focusedBorderColor = Color.Blue.copy(alpha = 0.5f)
      )
   )
   Spacer(modifier = Modifier.height(6.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EduGroupSpinner(
   label: String,
   items: List<Group>,
   onItemClick: (Group) -> Unit
) {
   val isEmpty = items.isEmpty()
   var expanded by remember { mutableStateOf(false) }
   var text by remember { mutableStateOf(if (isEmpty) "Guruhlar mavjud emas" else items[0].name) }
   ExposedDropdownMenuBox(
      expanded = expanded,
      onExpandedChange = { expanded = it }
   ) {
      OutlinedTextField(
         modifier = Modifier.menuAnchor().fillMaxWidth().padding(horizontal = 5.dp),
         value = text,
         onValueChange = {},
         enabled = !isEmpty,
         readOnly = true,
         singleLine = true,
         shape = RoundedCornerShape(10.dp),
         label = { Text(text = label, style = MaterialTheme.typography.labelLarge) },
         trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
         colors = ExposedDropdownMenuDefaults.textFieldColors(
            containerColor = Color.Gray.copy(alpha = 0.2f)
         )
      )
      ExposedDropdownMenu(
         expanded = expanded,
         onDismissRequest = { expanded = false },
         modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp)
      ) {
         if (!isEmpty) {
            items.forEach { group ->
               DropdownMenuItem(
                  text = {
                     Text(
                        text = group.name,
                        style = MaterialTheme.typography.bodyLarge
                     )
                  },
                  onClick = {
                     text = group.name
                     onItemClick(group)
                     expanded = false
                  },
                  contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
               )
            }
         } else{
            DropdownMenuItem(text = { Text(
               text = ".....",
               style = MaterialTheme.typography.bodyLarge
            ) }, onClick = {
               expanded = false
            })
         }
      }
   }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EduMentorSpinner(
   label: String,
   items: List<Mentor>,
   selectedMentor: Mentor = Mentor(-1, "Unselected", "", "", "",""),
   onItemClick: (Mentor) -> Unit
) {
   val isEmpty = items.isEmpty()
   var expanded by remember { mutableStateOf(false) }
   var text by remember { mutableStateOf(if (isEmpty) "Mentorlar mavjud emas" else selectedMentor.fullName()) }
   ExposedDropdownMenuBox(
      expanded = expanded,
      onExpandedChange = { expanded = it }
   ) {
      OutlinedTextField(
         modifier = Modifier.menuAnchor().fillMaxWidth().padding(horizontal = 5.dp),
         value = text,
         onValueChange = {},
         enabled = !isEmpty,
         readOnly = true,
         singleLine = true,
         shape = RoundedCornerShape(10.dp),
         label = { Text(text = label, style = MaterialTheme.typography.labelLarge) },
         trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
         colors = ExposedDropdownMenuDefaults.textFieldColors(
            containerColor = Color.Gray.copy(alpha = 0.2f)
         )
      )
      ExposedDropdownMenu(
         expanded = expanded,
         onDismissRequest = { expanded = false },
         modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp)
      ) {
         if (!isEmpty) {
            items.forEach { group ->
               DropdownMenuItem(
                  text = {
                     Text(
                        text = group.fullName(),
                        style = MaterialTheme.typography.bodyLarge
                     )
                  },
                  onClick = {
                     text = group.fullName()
                     onItemClick(group)
                     expanded = false
                  },
                  contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
               )
            }
         } else{
            DropdownMenuItem(text = { Text(
               text = ".....",
               style = MaterialTheme.typography.bodyLarge
            ) }, onClick = {
               expanded = false
            })
         }
      }
   }
}
