package com.example.basiccalendardisplay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //declaring the buttons, textviews

        val cal=findViewById<ImageView>(R.id.cal)
        val evn=findViewById<ImageView>(R.id.evn)
        val home=findViewById<ImageView>(R.id.home1)
        val diary=findViewById<ImageView>(R.id.diary)
        val pic=findViewById<ImageView>(R.id.pic)

        //button click action specifier
        cal.setOnClickListener(){

            //to navigate to new activity

            val intent= Intent(this,Main5Activity::class.java)
            startActivity(intent)
        }

        evn.setOnClickListener(){
            //to navigate to events page
            val intent= Intent(this,Main2Activity::class.java)
            startActivity(intent)
        }

        diary.setOnClickListener(){
            //to navigate to diary page
            val intent= Intent(this,Main3Activity::class.java)
            startActivity(intent)
        }

        pic.setOnClickListener(){
            //to navigate to pic of the page
            val intent= Intent(this,Main4Activity::class.java)
            startActivity(intent)
        }


    }
}
