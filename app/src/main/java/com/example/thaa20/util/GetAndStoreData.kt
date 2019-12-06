package com.example.thaa20.util

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.thaa20.util.Const.Companion.ASSEETS_FILE
import com.example.thaa20.util.Const.Companion.CURRENT_SPEAKER
import com.example.thaa20.util.Const.Companion.JSONSTRING
import com.example.thaa20.util.Const.Companion.PREFS_NAME
import com.example.thaa20.util.Const.Companion.TALKLIST
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GetAndStoreData(val context: Context):  AppCompatActivity() {



     fun createTalkList(): ArrayList<Talker> {
       var talkList: ArrayList<Talker>
        talkList = arrayListOf()

        val jsonString = intent.getStringExtra(JSONSTRING)
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


    var myPref = context.getSharedPreferences(PREFS_NAME, 0)

    fun savePage(page: Int) {
        myPref.edit().putInt(CURRENT_SPEAKER, page).apply()
    }

    fun getPage(): Int = myPref.getInt(CURRENT_SPEAKER, 1)

    fun getGsonString() = myPref.getString(TALKLIST, null)


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