package com.snspro.edusystem

enum class D(val route: String){
   Home("home"),
   Cources("courses"),
   Groups("groups"),
   Mentors("mentors"),
   AddMentor("add_mentor"),
   MentorDetail("mentor_detail_screen");

   operator fun invoke(): String{
      return route
   }
}
