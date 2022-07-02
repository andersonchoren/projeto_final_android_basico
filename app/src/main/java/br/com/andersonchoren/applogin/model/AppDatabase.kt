package br.com.andersonchoren.applogin.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context,factory:SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(
    context,DATABASE_NAME,factory,DATABASE_VERSION
) {
    companion object{
        private const val DATABASE_NAME = "users.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table users(id INTEGER PRIMARY KEY, email TEXT NOT NULL, password TEXT NOT NULL, gender TEXT NOT NULL, level TEXT NOT NULL)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}