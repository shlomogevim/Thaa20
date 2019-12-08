package com.example.thaa20.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.example.thaa20.R
import com.example.thaa20.util.GetAndStoreData
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      // stam()

     // val st=  retriveDataFromFirebase()
     //   var db = FirebaseFirestore.getInstance()
       setContentView(R.layout.activity_main)


    }

    fun stam(){

         val st="courses"
         val st1="11"
         val st2="name"
       /* val st="talker1"
        val st1="3"
        val st2="main"*/
        var db=FirebaseFirestore.getInstance()
        var doc=db.collection(st).document(st1).get().addOnCompleteListener { task ->
            if (task.result!!.exists()){
                Toast.makeText(this,"exist",Toast.LENGTH_LONG).show()
                Log.d("clima","name->${task.result?.getString(st2)}")
            }else{
                Toast.makeText(this,"Its nottt ok",Toast.LENGTH_LONG).show()

            }
        }

    }


    fun retriveDataFromFirebase(): String {
        var jsonS = ""
        Log.d("clima", "befor collection")
        var db = FirebaseFirestore.getInstance()

        db.collection("talker1").document("3").get().addOnCompleteListener { task ->
            if (task.result!!.exists()) {
                jsonS = task.result!!.getString("main")!!
            } else {
                Toast.makeText(this, "Its nottt ok", Toast.LENGTH_LONG).show()
                jsonS = "no data"
            }
        }
        Log.d("clima", "jsonS->$jsonS")
        return jsonS
    }








   /* private fun retriveDataFromFirebase() {
        var jsonS = ""
        Log.d("clima","zero")


        var db = FirebaseFirestore.getInstance()

        var doc = db.collection("talker1").document("3").get().addOnCompleteListener { task ->
            if (task.result!!.exists()) {
                jsonS = task.result!!.getString("main")!!
                Log.d("clima", "jsonS->${task.result?.getString("main")}")

            } else {
                Toast.makeText(this, "Its nottt ok", Toast.LENGTH_LONG).show()

            }
        }
    }*/

    private fun getData(db: FirebaseFirestore):String {
        var jsonS = ""








        /* db.collection("talker1").document("3").get().addOnCompleteListener { task ->
             Log.d("clima","one")
             if (task.result?.exists()!!) {
                 Log.d("clima","two")

                 jsonS = task.result!!.getString("main")!!
                 Log.d("clima","$jsonS")

             } else {
                 Log.d("clima","three")

                 jsonS = "none"
                 Toast.makeText(
                     this,
                     "Not Find because ${task.exception?.message} ",
                     Toast.LENGTH_LONG
                 ).show()
             }
         }*/
        return jsonS


    }



}



/*

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //  setContentView(R.layout.activity_main)

    shar = ShareData(this)

*/
/*
         myPref = getSharedPreferences(PREFS_NAME, 0)
         val jsonString = myPref.getString(TALKLIST, null)
*//*



    var jsonS = shar.getGsonString()

    // jsonString=null


    if (jsonS != null) {
        createJustFirstTalk(jsonS)
    } else {
        jsonS=retriveDataFromFirebase()

        Handler().postDelayed(
            {
                shar.saveJsonString(jsonS)
                createJustFirstTalk(jsonS)
            }, 5000
        )
    }

    // initPara()
}

private fun createJustFirstTalk(jsonS: String?) {
    val intent = Intent(this, AnimationScreen::class.java)
    intent.putExtra(JSONSTRING, jsonS)
    startActivityForResult(intent, REQEST_CODE)
}

private fun retriveDataFromFirebase():String {
    var jsonS=""

    var db = FirebaseFirestore.getInstance()
    db.collection("talker1").document("3").get().addOnCompleteListener { task ->

        if (task.result?.exists()!!) {
            jsonS = task.result!!.getString("main")!!

        } else {
            jsonS = "none"
            Toast.makeText(
                this,
                "Not Find because ${task.exception?.message} ",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    return jsonS
}

*/
