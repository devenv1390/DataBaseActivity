package com.example.databaseactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_file.setOnClickListener{
            val intent = Intent(this,FileActivity::class.java)
            startActivity(intent)
        }
        btn_share.setOnClickListener{
            val intent = Intent(this,SPrefActivity::class.java)
            startActivity(intent)
        }
        btn_sql.setOnClickListener{
            val intent = Intent(this,SQLActivity::class.java)
            startActivity(intent)
        }
    }
}