package com.snspro.edusystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.snspro.edusystem.database.MyDBHelper
import com.snspro.edusystem.ui.screen.HomeScreen
import com.snspro.edusystem.ui.screen.courses.AddStudentScreen
import com.snspro.edusystem.ui.screen.courses.AllCoursesScreen
import com.snspro.edusystem.ui.screen.courses.CourseDetailScreen
import com.snspro.edusystem.ui.screen.groups.AllGroupsScreen
import com.snspro.edusystem.ui.screen.groups.CourseGroupsScreen
import com.snspro.edusystem.ui.screen.mentors.AddMentorScreen
import com.snspro.edusystem.ui.screen.mentors.AllMentorsScreen
import com.snspro.edusystem.ui.screen.mentors.MentorDetailScreen
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

      NavHost(navController = navController, startDestination = Home) {
         composable<Home> {
            HomeScreen(
               kurslarClick = { navController.navigate(route = Courses) },
               guruhlarClick = { navController.navigate(route = Groups) },
               mentorlarClick = { navController.navigate(route = Mentors) }
            )
         }

         navigation<Courses>(startDestination = AllCourses) {

            composable<AllCourses> {
               AllCoursesScreen(
                  database = db,
                  onBackClick = { navController.popBackStack() },
                  onCourseItemClick = {
                     navController.navigate(CourseDetail(it.id))
                  }
               )
            }

            composable<CourseDetail> {
               val courseId = it.toRoute<CourseDetail>().courseId
               CourseDetailScreen(
                  database = db,
                  courseId = courseId,
                  onBackClick = { navController.popBackStack() },
                  addStudentClick = { course ->
                     navController.navigate(AddStudent(course.id))
                  }
               )
            }
            composable<AddStudent> {
               val courseId = it.toRoute<AddStudent>().courseId
               AddStudentScreen(
                  database = db,
                  courseId = courseId,
                  onBackClick = { navController.popBackStack() })
            }

         }



         navigation<Groups>(startDestination = CourseGroups) {
            composable<CourseGroups> {
               CourseGroupsScreen(
                  database =db,
                  onBackClick = { navController.popBackStack() },
                  onCourseItemClick ={
                     navController.navigate(AllGroups(it.id))
                  }

               )
            }
            composable<AllGroups> {
               val courseId = it.toRoute<AllGroups>().courseId
               AllGroupsScreen(
                  database = db,
                  courseId = courseId,
                  onBackClick = { navController.popBackStack() },
                  onGroupClick = {

                  },
                  onGroupViewClick = {

                  },
                  onGroupEditClick = {

                  }
               )

            }
            composable<GroupDetail> { }
         }


         navigation<Mentors>(startDestination = AllMentors) {
            composable<AllMentors> {
               AllMentorsScreen(
                  database = db,
                  onBackClick = {
                     navController.popBackStack()
                  },
                  addMentorClick = {
                     navController.navigate(route = AddMentor(action = "add", id = 1))
                  },
                  mentorDetailScreenClick = {
                     navController.navigate(MentorDetail(it.id))
                  }
               )
            }
            composable<AddMentor> {
               val action = it.toRoute<AddMentor>().action
               val id = it.toRoute<AddMentor>().id
               if (action == "add") {
                  AddMentorScreen(
                     database = db,
                     onBackClick = { navController.popBackStack() },
                     actionClick = { mentor ->
                        db.addMentor(mentor)
                        navController.popBackStack()
                     },
                     id = 1,
                     actionType = action
                  )
               } else if (action == "edit") {
                  AddMentorScreen(
                     database = db,
                     onBackClick = { navController.popBackStack() },
                     actionClick = { mentor ->
                        db.editMentor(id, mentor)
                        navController.popBackStack()
                     },
                     id = id,
                     actionType = action
                  )
               }

            }
            composable<MentorDetail> {
               val mentorId = it.toRoute<MentorDetail>().mentorId
               MentorDetailScreen(
                  database = db,
                  mentorId = mentorId,
                  onBackClick = { navController.popBackStack() },
                  onEditClick = { mentor ->
                     navController.navigate(route = AddMentor(action = "edit", mentor.id))
                  },
                  onGroupClick = {},
                  onGroupViewClick = {},
                  onGroupEditClick = {}
               )
            }

         }
      }


   }
}

