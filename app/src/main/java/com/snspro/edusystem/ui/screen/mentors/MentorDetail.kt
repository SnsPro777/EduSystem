package com.snspro.edusystem.ui.screen.mentors

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snspro.edusystem.R
import com.snspro.edusystem.database.EducationService
import com.snspro.edusystem.model.Group
import com.snspro.edusystem.model.Mentor
import com.snspro.edusystem.ui.common.EduScaffold
import com.snspro.edusystem.ui.common.TopBarIcon
import com.snspro.edusystem.ui.theme.PurpleGrey80

@Composable
fun MentorDetailScreen(
   database: EducationService,
   mentorId: Long,
   onBackClick: () -> Unit,
   onEditClick: (Mentor) -> Unit,
   onGroupClick: (Long) -> Unit,
   onGroupViewClick: (Long) -> Unit,
   onGroupEditClick: (Long) -> Unit
) {
   val mentor = database.getMentorById(mentorId)
   EduScaffold(
      onBackClick = onBackClick,
      title = mentor.fullName(),
      icons = {
         TopBarIcon(
            onClick = {
               onEditClick(mentor)
            },
            icon = Icons.Default.Edit
         )
         TopBarIcon(
            onClick = {
               database.deleteMentor(mentorId)
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
               modifier = Modifier
                  .fillMaxSize()
                  .padding(15.dp),
               text = mentor.description,
               style = MaterialTheme.typography.titleMedium,
               maxLines = Int.MAX_VALUE
            )
         }

         GroupsList(
            database,
            groups = database.getGroupsByMentorId(mentorId),
            onGroupClick,
            onGroupViewClick,
            onGroupEditClick
         )

      }

   }
}

@Composable
fun GroupsList(
   database: EducationService,
   groups: List<Group>,
   onGroupClick: (Long) -> Unit,
   onGroupViewClick: (Long) -> Unit,
   onGroupEditClick: (Long) -> Unit
) {
   Column {
      Card(
         modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(start = 5.dp, end = 5.dp, bottom = 10.dp)
            .verticalScroll(rememberScrollState()),
         shape = RoundedCornerShape(10.dp),
         colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
         ),
         elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
            pressedElevation = 1.dp
         )
      ) {
         var refreshCount by remember {
            mutableStateOf(2)
         }
         if (refreshCount != 1) {
            if (groups.isEmpty()) {
               Text(
                  text = "Guruhlar mavjud emas",
                  style = MaterialTheme.typography.titleLarge,
                  modifier = Modifier.align(Alignment.CenterHorizontally)
               )
            } else {
               for (group in groups) {
                  Row(
                     modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp)
                        .padding(vertical = 10.dp, horizontal = 5.dp)
                        .clip(shape = RoundedCornerShape(5.dp))
                        .background(Color(0xFFC1BFBF))
                        .clickable {
                           onGroupClick(group.id)
                        },
                     verticalAlignment = Alignment.CenterVertically
                  ) {
                     Spacer(modifier = Modifier.width(10.dp))
                     Column {
                        val count = database.getStudentCountByGroupId(group.id)
                        Text(text = group.name, style = MaterialTheme.typography.titleMedium)
                        Text(
                           text = "o'quvchilar soni: $count",
                           style = MaterialTheme.typography.labelMedium,
                           color = Color(0xFF1D192B)
                        )
                     }
                     Spacer(modifier = Modifier.weight(1f))
                     Row(
                        modifier = Modifier
                           .fillMaxHeight()
                           .padding(end = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                     ) {
                        IconBox(
                           background = Color.Green.copy(alpha = 0.7f),
                           icon = Icons.Default.RemoveRedEye
                        ) {
                           onGroupViewClick(group.id)
                        }
                        IconBox(
                           background = Color.Yellow.copy(alpha = 0.7f),
                           icon = Icons.Default.Edit
                        ) {
                           onGroupEditClick(group.id)
                        }
                        var showDialog by remember {
                           mutableStateOf(false)
                        }
                        if (showDialog) {
                           AlertDialog(
                              onDismissRequest = { showDialog = false },
                              confirmButton = {
                                 TextButton(onClick = {
                                    showDialog = false
                                    database.deleteGroup(group.id)
                                    refreshCount += 1
                                 }) {
                                    Icon(
                                       imageVector = Icons.Default.Check,
                                       contentDescription = null,
                                       Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = "Ok", fontSize = 18.sp)
                                 }
                              },
                              dismissButton = {
                                 TextButton(onClick = {
                                    showDialog = false
                                 }) {
                                    Icon(
                                       imageVector = Icons.Default.Close,
                                       contentDescription = null,
                                       Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = "Cancel", fontSize = 18.sp)
                                 }
                              },
                              title = {
                                 Text(text = "${group.name} guruhuni o'chirmoqchimisz")
                                 Divider()
                              }
                           )
                        }
                        IconBox(
                           background = Color.Red.copy(alpha = 0.7f),
                           icon = Icons.Default.Delete
                        ) {
                           showDialog = true
                        }
                     }
                  }
               }
            }
         }
      }
   }

}

@Composable
fun IconBox(
   background: Color,
   icon: ImageVector,
   action: () -> Unit
) {
   Box(
      modifier = Modifier
         .width(30.dp)
         .height(30.dp)
         .clip(shape = RoundedCornerShape(2.dp))
         .background(background.copy(alpha = 0.5f))
         .clickable { action() },
      contentAlignment = Alignment.Center
   ) {
      Icon(
         imageVector = icon,
         contentDescription = null,
         tint = Color.Black,
         modifier = Modifier.size(20.dp)
      )
   }
}