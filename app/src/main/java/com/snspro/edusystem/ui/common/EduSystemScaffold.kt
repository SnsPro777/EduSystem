package com.snspro.edusystem.ui.common

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EduScaffold(
   onBackClick: () -> Unit,
   title: String,
   icons: @Composable () -> Unit,
   content: @Composable () -> Unit
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
            TopBarIcon(onClick = onBackClick, icon = Icons.Default.ArrowBack)
            Text(
               text = title,
               textAlign = TextAlign.Center,
               style = MaterialTheme.typography.titleLarge,
               color = MaterialTheme.colorScheme.onPrimary,
               modifier = Modifier.weight(1f)
            )
            icons()
            Spacer(modifier = Modifier.width(10.dp))
         }
      }
   ) { innerPadding ->
      Column(modifier = Modifier
         .fillMaxSize()
         .padding(innerPadding)) {
         content()
      }
   }
}

@Composable
fun TopBarIcon(
   onClick: () -> Unit,
   icon: ImageVector
) {
   Spacer(modifier = Modifier.width(10.dp))
   IconButton(onClick = onClick) {
      Icon(
         imageVector = icon,
         contentDescription = null,
         modifier = Modifier.size(25.dp),
         tint = MaterialTheme.colorScheme.onPrimary
      )
   }
}