package com.snspro.edusystem.model

data class Course(
   val id: Long,
   val name: String,
   val description: String,
){
   fun getFirstWord(): String{
      val strings = name.split(" ")
      return strings[0]
   }
}