package br.com.andersonchoren.applogin.model

data class User(
    val id:Int? = null,
    val email:String,
    val password:String,
    val gender:String? = null,
    val level:String? = null
)
