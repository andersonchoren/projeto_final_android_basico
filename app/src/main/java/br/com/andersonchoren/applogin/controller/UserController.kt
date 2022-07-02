package br.com.andersonchoren.applogin.controller

import android.annotation.SuppressLint
import android.content.Context
import br.com.andersonchoren.applogin.model.User
import br.com.andersonchoren.applogin.repository.UserRepository

class UserController(context: Context) {
    private val repository: UserRepository

    init {
        repository = UserRepository(context)
    }

    fun insert(user: User): Long {
        return repository.insert(user)
    }

    @SuppressLint("Range")
    fun findOne(user: User): User? {
        val cursor = repository.findOne(user)
        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val password = cursor.getString(cursor.getColumnIndex("password"))
            val gender = cursor.getString(cursor.getColumnIndex("gender"))
            val level = cursor.getString(cursor.getColumnIndex("level"))
            return User(id, email,password,gender, level)
        }
        return null
    }
}