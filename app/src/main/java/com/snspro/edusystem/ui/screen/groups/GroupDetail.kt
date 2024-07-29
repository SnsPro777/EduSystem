package com.snspro.edusystem.ui.screen.groups

import androidx.compose.runtime.Composable
import com.snspro.edusystem.database.EducationService
import com.snspro.edusystem.ui.common.EduScaffold

@Composable
fun GroupDetailScreen(
   database: EducationService,
   onBackClick: () -> Unit
) {
   EduScaffold(
      onBackClick = onBackClick,
      title = "Guruh malumoti",
      icons = { /*TODO*/ }
   ) {

   }
}