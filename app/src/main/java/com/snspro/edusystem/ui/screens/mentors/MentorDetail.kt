package com.snspro.edusystem.ui.screens.mentors

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.snspro.edusystem.R
import com.snspro.edusystem.database.EducationService
import com.snspro.edusystem.ui.common.EduScaffold
import com.snspro.edusystem.ui.common.TopBarIcon
import com.snspro.edusystem.ui.theme.PurpleGrey80

@Composable
fun MentorDetailScreen(
   database: EducationService,
   id: Long,
   onBackClick: () -> Unit
) {
   val mentor = database.getMentorById(id)
   EduScaffold(
      onBackClick = onBackClick,
      title = mentor.fullName(),
      icons = {
         TopBarIcon(
            onClick = { },
            icon = Icons.Default.Edit
         )
         TopBarIcon(
            onClick = {
               database.deleteMentor(id)
               onBackClick()
            },
            icon = Icons.Default.Delete
         )
      }
   ) {
      Column(
         modifier = Modifier.fillMaxSize()
      ) {
         Card(
            modifier = Modifier
               .fillMaxWidth()
               .height(100.dp)
               .padding(start = 5.dp, end = 5.dp, bottom = 10.dp),
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
                     text = mentor.fullName(),
                     style = MaterialTheme.typography.titleLarge,
                     color = Color.Black
                  )
                  Spacer(modifier = Modifier.height(2.dp))
                  Text(
                     text = mentor.profession,
                     style = MaterialTheme.typography.titleMedium,
                     color = PurpleGrey80
                  )
                  Spacer(modifier = Modifier.height(2.dp))
                  Text(
                     text = mentor.level,
                     style = MaterialTheme.typography.labelLarge,
                     color = MaterialTheme.colorScheme.error
                  )
               }
            }
         }

         Card(
            modifier = Modifier
               .fillMaxWidth()
               .height(250.dp)
               .padding(start = 5.dp, end = 5.dp, bottom = 10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
               containerColor = MaterialTheme.colorScheme.onPrimary,
            ),
            elevation = CardDefaults.cardElevation(
               defaultElevation = 5.dp,
               pressedElevation = 1.dp
            )
         ) {
            Text(
               modifier = Modifier.fillMaxSize(),
               text = mentor.description,
               style = MaterialTheme.typography.titleMedium,
               maxLines = Int.MAX_VALUE
            )
         }
      }

   }
}