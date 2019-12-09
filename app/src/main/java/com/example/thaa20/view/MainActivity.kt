package com.example.thaa20.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.thaa20.R
import com.example.thaa20.util.GetAndStoreData
import com.example.thaa20.util.Talker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    lateinit var getAndStore: GetAndStoreData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getAndStore = GetAndStoreData(this)
        getAndStore.saveCurrentFile(20)          // its the current text file that we working on

       // storeTalkingListInFirestore()

         setContentView(R.layout.activity_main)
    }











    fun storeTalkingListInFirestore() {
        val getAndStoreData = GetAndStoreData(this)
        var talkList = ArrayList<Talker>()

        talkList = getAndStoreData.createTalkListFromPref()

        val st = "talker1"
        val st1 = "8"

        val gson = Gson()
        val jsonS = gson.toJson(talkList)


        var db = FirebaseFirestore.getInstance()
        var talker = HashMap<String, Any>()
        talker.put("index", st1)
        jsonS?.let { talker.put("main", it) }
        db.collection(st).document(st1).set(talker)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Saving is succsses", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        this,
                        "Not Save because \${task.exception?.message",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

    }














    /* fun stam(){

          val st="courses"
          val st1="11"
          val st2="name"
        *//* val st="talker1"
        val st1="3"
        val st2="main"*//*
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
    }*/


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

    /*  private fun getData(db: FirebaseFirestore):String {
          var jsonS = ""

           db.collection("talker1").document("3").get().addOnCompleteListener { task ->
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
           }
          return jsonS
      }*/


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
