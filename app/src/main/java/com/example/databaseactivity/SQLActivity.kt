package com.example.databaseactivity

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sqlactivity.*

class SQLActivity : AppCompatActivity() {
    @SuppressLint("Range", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlactivity)

        val dbHelper = MyDatabaseHelper(this,"BookStore.db", 1)

        btn_create.setOnClickListener {
            //创建数据库和数据表
            dbHelper.writableDatabase
            Toast.makeText(this, "Create Succeeded", Toast.LENGTH_SHORT).show()
        }

        btn_insert.setOnClickListener {
            //向数据表添加一条数据
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, values1) // 插入第一条数据
            val values2 = ContentValues().apply {
                // 开始组装第二条数据
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2) // 插入第二条数据
        }

        btn_delete.setOnClickListener {
            //删除一条数据
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))
        }

        btn_updata.setOnClickListener {
            //更新一条数据
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
        }

        btn_select.setOnClickListener {
            //查询一条数据
            val db = dbHelper.writableDatabase
            // 查询Book表中所有的数据
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    // 遍历Cursor对象，取出数据并打印
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    if(name == "The Da Vinci Code"){
                        textViewSQL.text = "book name is $name,book author is $author,book pages is $pages,book price is $price"
                    }
//                    Log.d("MainActivity", "book name is $name")
//                    Log.d("MainActivity", "book author is $author")
//                    Log.d("MainActivity", "book pages is $pages")
//                    Log.d("MainActivity", "book price is $price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
    }
}