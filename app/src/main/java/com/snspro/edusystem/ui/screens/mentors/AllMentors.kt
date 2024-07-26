package com.snspro.edusystem.ui.screens.mentors

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.snspro.edusystem.R
import com.snspro.edusystem.database.EducationService
import com.snspro.edusystem.model.Mentor
import com.snspro.edusystem.ui.theme.PurpleGrey80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllMentorsScreen(
   database: EducationService,
   onBackClick: () -> Unit,
   addMentorClick: () -> Unit,
   mentorDetailScreenClick: (Mentor) -> Unit
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
               text = "Mentorlar",
               textAlign = TextAlign.Center,
               style = MaterialTheme.typography.titleLarge,
               color = MaterialTheme.colorScheme.onPrimary,
               modifier = Modifier.weight(1f)
            )

            IconButton(onClick = addMentorClick) {
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
      val mentors = database.getAllMentors()
      LazyColumn(
         modifier = Modifier.padding(
            top = it.calculateTopPadding() + 10.dp,
            start = 5.dp,
            end = 5.dp
         )
      ) {
         items(mentors) {
            Card(
               modifier = Modifier
                  .fillMaxWidth()
                  .height(100.dp)
                  .padding(start = 5.dp, end = 5.dp, bottom = 10.dp)
                  .clickable { mentorDetailScreenClick(it) },
               shape = RoundedCornerShape(10.dp),
               colors = CardDefaults.cardColors(
                  containerColor = MaterialTheme.colorScheme.onPrimary,
               ),
               elevation = CardDefaults.cardElevation(
                  defaultElevation = 5.dp,
                  pressedElevation = 1.dp
               )
            ) {
               Row(
                  modifier = Modifier.fillMaxSize(),
                  verticalAlignment = Alignment.CenterVertically
               ) {
                  Spacer(modifier = Modifier.width(10.dp))
                  Icon(
                     painter = painterResource(id = R.drawable.user),
                     contentDescription = null,
                     modifier = Modifier.size(80.dp)
                  )
                  Spacer(modifier = Modifier.width(10.dp))
                  Column(
                     modifier = Modifier.fillMaxHeight()
                  ) {
                     Spacer(modifier = Modifier.height(10.dp))
                     Text(
                        text = "${it.firstName} ${it.lastName}",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black
                     )
                     Spacer(modifier = Modifier.height(2.dp))
                     Text(
                        text = it.profession,
                        style = MaterialTheme.typography.titleMedium,
                        color = PurpleGrey80
                     )
                     Spacer(modifier = Modifier.height(2.dp))
                     Text(
                        text = it.level,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.error
                     )
                  }
               }
            }
         }
      }

   }
}