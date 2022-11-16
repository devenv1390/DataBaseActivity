package com.example.databaseactivity

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(val context:Context, name:String, version:Int):SQLiteOpenHelper(context,name,null,version) {
    private val createBookStore = "Create table Book( id integer primary key autoincrement,author text,price real,pages integer,name text)"
    private val createCategory = "create table Category (id integer primary key autoincrement,category_name text,category_code integer)"

    override fun onCreate(db: SQLiteDatabase?) {
        //创建数据库
        db?.execSQL(createBookStore)
        db?.execSQL(createCategory)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        //升级数据库
        db?.execSQL("drop table if exists Book")
        db?.execSQL("drop table if exists Category")
        onCreate(db)
    }

}