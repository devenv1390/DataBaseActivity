package com.example.databaseactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_file.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class FileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        btn_input.setOnClickListener {
            //提取输入的文本
            val input_text = input.text.toString()
            //存储到相应的文件
            val output_file = openFileOutput("data", Context.MODE_PRIVATE)
            val write = BufferedWriter(OutputStreamWriter(output_file))
            try {
                write.use {
                    it.write(input_text)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        btn_load.setOnClickListener {
            //构建存储数据文件
            val content = StringBuilder()
            //读取相应的文件
            try {
                val input_file = openFileInput("data")
                val reader = BufferedReader(InputStreamReader(input_file))
                reader.use {
                    reader.forEachLine {
                        content.append(it)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            //展示读取到的数据到界面中
            textView.setText(content.toString())
        }
    }
}