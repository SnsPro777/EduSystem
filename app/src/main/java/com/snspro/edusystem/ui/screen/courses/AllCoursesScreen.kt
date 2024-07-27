package com.snspro.edusystem.ui.screen.courses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllCoursesScreen(
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
               text = "Barcha kurslar",
               textAlign = TextAlign.Center,
               style = MaterialTheme.typography.titleLarge,
               color = MaterialTheme.colorScheme.onPrimary,
               modifier = Modifier.weight(1f)
            )
            var showDialog by remember {
               mutableStateOf(false)
            }
            if (showDialog){
               Dialog(onDismissRequest = { showDialog = false }) {

               }
            }
            IconButton(onClick = {showDialog = true}) {
               Icon(
                  imageVector = Icons.Default.Add,
                  contentDescription = null,
                  modifier = Modifier.size(25.dp),
                  tint = MaterialTheme.colorScheme.onPrimary
               )
            }
            Spacer(modifier = Modifier.width(18.dp))
         }
      }
   ) {
      Column(
         modifier = Modifier.padding(it)
      ) {

      }
   }
}