package br.com.andersonchoren.applogin.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import br.com.andersonchoren.applogin.model.AppDatabase
import br.com.andersonchoren.applogin.model.User

class UserRepository(private val context: Context) {
    private var db: SQLiteDatabase = AppDatabase(this.context, null).readableDatabase

    fun insert(user: User): Long {
        val values = ContentValues()
        values.put("email", user.email)
        values.put("password", user.password)
        values.put("gender", user.gender)
        values.put("level", user.level)

        return db.insert("users", null, values)
    }

    fun findOne(user:User): Cursor? {
        val sql = "select * from users where email=? and password= ?"
        return db.rawQuery(sql, arrayOf(user.email,user.password));
    }
}