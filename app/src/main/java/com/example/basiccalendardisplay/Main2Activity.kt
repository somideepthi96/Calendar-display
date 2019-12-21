package com.example.basiccalendardisplay

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //creating a file to store the values
        val file = "events.txt"

//declaring the calendar instances
        val c = Calendar.getInstance()
        val y = c.get(Calendar.YEAR)
        val m = c.get(Calendar.MONTH)
        val d = c.get(Calendar.DAY_OF_MONTH)
        //val mStorageRef: StorageReference


        val evname = findViewById<TextView>(R.id.evname)
        val dt = findViewById<EditText>(R.id.dt)

//Tried firebase connectivity by using the Firebase database
        //val dbFirestore = FirebaseFirestore.getInstance()

//Date Button declaration
        val dtbutton = findViewById<Button>(R.id.dtbutton)

        dtbutton.setOnClickListener {
            //Date picker function
            var dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    dt.setText("" + (month + 1) + "/" + dayOfMonth + "/" + year)
                }, y, m, d
            )
//To show the selected date
            dpd.show()


        }

        save.setOnClickListener {

//saving the textview and date values in file
            var fileContents = "\n" + evname.text.toString() + "-" + dt.text.toString()
//Taking the data to file
            openFileOutput(file, Context.MODE_APPEND).use {
                //writing it to the file
                it.write(fileContents.toByteArray())

                evname.text = null
                dt.text = null
                //reading the data saved
                val list = openFileInput(file).reader()
//displaying data in textview
                tvResult.text = list.readText()


//Used Hashmap to get the data into the firebase and storing it.
                //val input = dt.text.toString().trim { it <= ' ' }
                //val inputtext = evname.text.toString().trim { it <= ' ' }
                //e.text = "" + inputtext
                // d.text="" + input


                //val map: HashMap<String, String> = hashMapOf("data" to input, "data2" to inputtext)

                //dbFirestore.collection("myDB_KOTLIN").document("info").set(map).addOnSuccessListener { print("hello") }


            }
            val cancel = findViewById<Button>(R.id.cancel)
            cancel.setOnClickListener() {
                //cancel button clicked to clear the text and navigate to main page
                evname.text = null
                dt.text = null
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)


            }
            val home = findViewById<ImageView>(R.id.home)
            //Home button to navigate to home page
            home.setOnClickListener() {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }


        }
    }
}
