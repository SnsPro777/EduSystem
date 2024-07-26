package com.snspro.edusystem

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.snspro.edusystem.database.MyDBHelper
import com.snspro.edusystem.model.Mentor
import com.snspro.edusystem.ui.Screens.HomeScreen
import com.snspro.edusystem.ui.Screens.courses.CoursesScreen
import com.snspro.edusystem.ui.Screens.groups.GroupsScreen
import com.snspro.edusystem.ui.Screens.mentors.AddMentorScreen
import com.snspro.edusystem.ui.Screens.mentors.MentorDetailScreen
import com.snspro.edusystem.ui.Screens.mentors.MentorsScreen
import com.snspro.edusystem.ui.theme.EduSystemTheme

class MainActivity : ComponentActivity() {
   private lateinit var db: MyDBHelper

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      db = MyDBHelper(this)
      setContent {
         EduSystemTheme {
            EduSystemApp()
         }
      }
   }
   @Composable
   fun EduSystemApp() {
      val navController = rememberNavController()
      NavHost(
         navController = navController,
         startDestination = D.Home(),
      ) {
         composable(D.Home()) {
            HomeScreen(
               kurslarClick = { navController.navigate(D.Cources()) },
               guruhlarClick = { navController.navigate(D.Groups()) },
               mentorlarClick = { navController.navigate(D.Mentors()) }
            )
         }

         composable(D.Cources()) {
            CoursesScreen(
               onBackClick = { navController.popBackStack() }
            )
         }

         composable(D.Groups()) {
            GroupsScreen()
         }

         composable(D.Mentors()) {
            MentorsScreen(
               database = db,
               onBackClick = {
                             navController.popBackStack()
               },
               addMentorClick = {
                  navController.navigate(D.AddMentor())
               },
               mentorDetailScreenClick = {mentor ->
                  navController.navigate("${D.MentorDetail()}/${mentor.id}")
               }
            )
         }

         composable(D.AddMentor()){
           AddMentorScreen(
              database = db,
              onBackClick = {
                 navController.popBackStack()
              }
           )
         }

         composable("${D.MentorDetail()}/{mentorId}"){
            it.arguments?.getString("mentorId")
               ?.let { it1 ->
                  MentorDetailScreen(
                     database = db,
                     id = it1.toLong()
                  )

               }
         }


      }
   }
}

