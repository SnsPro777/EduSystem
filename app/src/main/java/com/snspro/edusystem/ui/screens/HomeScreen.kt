package com.snspro.edusystem.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AssignmentInd
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.snspro.edusystem.R

@Composable
fun HomeScreen(
   kurslarClick: () -> Unit,
   guruhlarClick: () -> Unit,
   mentorlarClick: () -> Unit,
) {
   Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.fillMaxSize()
   ) {
      Row(
         horizontalArrangement = Arrangement.Center,
         modifier = Modifier.fillMaxWidth()
      ) {
         MenuItem(
            icon = ImageVector.vectorResource(R.drawable.hugeicons_course),
            title = "Kurslar",
            onClick = kurslarClick
         )
         Spacer(modifier = Modifier.width(40.dp))
         MenuItem(
            icon = Icons.Outlined.Groups,
            title = "Guruhlar",
            onClick = guruhlarClick
         )
      }
      Spacer(modifier = Modifier.height(45.dp))
      Row(
         horizontalArrangement = Arrangement.Center,
         modifier = Modifier.fillMaxWidth()
      ) {
         MenuItem(
            icon = Icons.Outlined.AssignmentInd,
            title = "Mentorlar",
            onClick = mentorlarClick
         )
      }
   }
}

@Composable
fun MenuItem(
   icon: ImageVector,
   title: String,
   onClick: () -> Unit
) {

   Column(
      modifier = Modifier
         .width(130.dp)
         .height(120.dp)
         .clip(shape = RoundedCornerShape(10.dp))
         .background(MaterialTheme.colorScheme.primary)
         .padding(12.dp)
         .clickable { onClick() }
   ) {
      Icon(
         imageVector = icon,
         contentDescription = null,
         modifier = Modifier.size(50.dp),
         tint = MaterialTheme.colorScheme.onPrimary
      )
      Spacer(modifier = Modifier.height(10.dp))
      Text(
         text = title,
         style = MaterialTheme.typography.titleLarge,
         color = MaterialTheme.colorScheme.onPrimary
      )
   }
}
