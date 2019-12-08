package com.example.thaa20.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.thaa20.util.Const.Companion.ASSEETS_FILE
import com.example.thaa20.util.Const.Companion.CURRENT_SPEAKER
import com.example.thaa20.util.Const.Companion.PREFS_NAME
import com.example.thaa20.util.Const.Companion.TALKLIST
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GetAndStoreData(val context: Context) : AppCompatActivity() {


    var myPref = context.getSharedPreferences(PREFS_NAME, 0)

    fun getTalkList(): ArrayList<Talker> {
        var talkList1 = ArrayList<Talker>()

        var jsonS = getGsonString()

        if (jsonS == null) {
            jsonS = retriveDataFromFirebase()
        }
        if (jsonS == null) {
            talkList1 = createTalkListFromTheStart()
        } else {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Talker>>() {}.type
            talkList1 = gson.fromJson(jsonS, type)
        }
        saveTalkingListInPref(talkList1)

        /*      Handler().postDelayed(
                  {
                      saveJsonString(jsonS)
                  }, 5000
              )
          }*/


        return talkList1
    }

    fun stam(){

        /* val st="courses"
         val st1="11"
         val st2="name"*/
        val st="talker1"
        val st1="3"
        val st2="main"
        var db=FirebaseFirestore.getInstance()
        var doc=db.collection(st).document(st1).get().addOnCompleteListener { task ->
            if (task.result!!.exists()){
                Log.d("clima","name->${task.result?.getString(st2)}")
            }else{
                Log.d("clima","name-> no name")

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


    private fun getData(db: FirebaseFirestore): String {
        var jsonS = ""

        var doc = db.collection("talker1").document("3").get().addOnCompleteListener { task ->
            if (task.result!!.exists()) {
                jsonS = task.result!!.getString("main")!!
                Log.d("clima", "jsonS->${task.result?.getString("main")}")

            } else {
                Toast.makeText(this, "Its nottt ok", Toast.LENGTH_LONG).show()

            }
        }


        return jsonS


    }


    /*
    *   var jsonS = shar.getGsonString()

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


    fun savePage(page: Int) {
        myPref.edit().putInt(CURRENT_SPEAKER, page).apply()
    }

    fun getPage(): Int = myPref.getInt(CURRENT_SPEAKER, 1)


    fun getGsonString() = myPref.getString(TALKLIST, null)


    fun createTalkList(): ArrayList<Talker> {
        var talkList: ArrayList<Talker>
        talkList = arrayListOf()


        val jsonString = myPref.getString(TALKLIST, null)

        // val jsonString = intent.getStringExtra(JSONSTRING)
        // val jsonString = intent.getStringExtra(JSONSTRING)
        if (jsonString == "none" || jsonString == "") {
            talkList = getTalkingListFromPref(0)
            saveTalkingListInPref(talkList)

        } else {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Talker>>() {}.type
            talkList = gson.fromJson(jsonString, type)
            saveTalkingListInPref(talkList)
        }
        return talkList
    }


    fun saveTalkingListInPref(talkingList: ArrayList<Talker>) {
        val gson = Gson()
        val jsonString = gson.toJson(talkingList)
        myPref.edit().putString(TALKLIST, jsonString).apply()
    }

    fun saveJsonString(jsonS: String?) {
        myPref.edit().putString(TALKLIST, jsonS).apply()
    }


    fun getTalkingListFromPref(ind: Int): ArrayList<Talker> {
        val talkList1: ArrayList<Talker>
        val gson = Gson()
        val jsonString = myPref.getString(TALKLIST, null)

        if (ind == 0 || jsonString == null) {
            talkList1 = createTalkListFromTheStart()
            saveTalkingListInPref(talkList1)

        } else {
            val type = object : TypeToken<ArrayList<Talker>>() {}.type
            talkList1 = gson.fromJson(jsonString, type)
        }
        return talkList1
    }

    fun createTalkListFromTheStart(): ArrayList<Talker> {
        var talkList1 = arrayListOf<Talker>()
        val ADAM = "-אדם-"
        val GOD = "-אלוהים-"
        val currenteFile = "text/text" + ASSEETS_FILE.toString() + ".txt"

        var countItem = 0
        var text = context.assets.open(currenteFile).bufferedReader().use {
            it.readText()
        }
        text = text.replace("\r", "")
        var list1 = text.split(ADAM)

        var talker = Talker()

        talkList1.add(countItem, talker)
        var i = 0

        for (element in list1) {
            //  if (element != "" && element.length > 25) {
            if (element != "") {
                i++
                var list2 = element.split(GOD)
                var st1 = improveString(list2[0])
                var st2 = improveString(list2[1])
                if (st1.isNullOrEmpty() || st2.isNullOrEmpty()) {
                    return talkList1
                }
                countItem++
                talker = Talker()
                with(talker) {
                    whoSpeake = "man"
                    taking = st1.trim()
                    numTalker = countItem
                    var arr = st1.split("\n")
                    for (item in arr) {
                        if (item != "") {
                            takingArray.add(item)
                        }
                    }
                    colorText = "#000000"
                    colorBack = "#ffffff"
                    animNum = 10
                }

                talkList1.add(talker)

                countItem++
                talker = Talker()
                with(talker) {
                    whoSpeake = "god"
                    talker.taking = st2.trim()
                    talker.numTalker = countItem
                    var arr = st2.split("\n")
                    for (item in arr) {
                        if (item != "") {
                            takingArray.add(item)
                        }
                    }
                    colorText = "#000000"
                    colorBack = "#ffffff"
                    animNum = 10
                }
                talkList1.add(talker)
            }
        }
        return talkList1
    }

    private fun improveString(st: String) = st.substring(1, st.length - 1)

    private fun createTalkArray(jsonString: String?) {
        var talkList: ArrayList<Talker>
        //  Log.d("clima",jsonString)
        talkList = arrayListOf()
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Talker>>() {}.type
        talkList = gson.fromJson(jsonString, type)
        Log.d("clima", "${talkList[19].taking}")
    }

}