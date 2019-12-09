package com.example.thaa20.view


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.thaa20.R
import com.example.thaa20.util.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.talking_details.*


class DetailsTalking : Fragment() {

    var conv: Convers? = null
    lateinit var getStoreData: GetAndStoreData
    lateinit var animationInAction: AnimationInAction
    lateinit var getAndStoreData:GetAndStoreData
    lateinit var arrangeLayout: ArrangeLayout
    lateinit var talkList: ArrayList<Talker>
    var counterStep = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.talking_details, container, false)
    }
    fun talkC()=talkList[getAndStoreData.getCurrentPage()]


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            conv = DetailsTalkingArgs.fromBundle(it).currentConvers
            // tv.text=" Hi Man You SeleT Item num "+ conv!!.numC
        }
        setupParams(view)

        getTalkList()

        arrangeLayout = ArrangeLayout(view,talkList)


        arrangeLayout.drawListView()


        arrangeLayout.operateListView()

        animationInAction.excuteTalker(talkC())
       // arrangeLayout.updateTitleTalkerSituation(talker)


        arrangeLayout.setPosition(1)

    }

    private fun getTalkList() {
        talkList=getStoreData.createTalkListFromPref()
        if (talkList.size==0){
            createTalkingListFromFirestore()  //open tool->firebase->firestore see if all depen. ok
                                              //rebuilt project
                                              // run and wait for result
        }
        if (talkList.size==0){
            // !! must be in remarked becaseus it inteferring to the firebase
            //  talkList=getStoreData.createTalkListFromTheStart()

        }
    }

    fun createTalkingListFromFirestore():ArrayList<Talker>{
        var talkList1 = ArrayList<Talker>()
        var jsonS=""
        /*val st="courses"
        val st1="11"
        val st2="name"*/
         val st="talker1"
         val st1="3"
         val st2="main"
        var db=FirebaseFirestore.getInstance()
        var doc=db.collection(st).document(st1).get().addOnCompleteListener { task ->
            if (task.result!!.exists()){
                jsonS=task.result?.getString(st2)!!
                val gson = Gson()
                val type = object : TypeToken<ArrayList<Talker>>() {}.type
                talkList1 = gson.fromJson(jsonS, type)
                getStoreData.saveTalkingListInPref(talkList1)
                Log.d("clima"," $jsonS")
            }
        }
        return talkList1
    }






    private fun setupParams(view: View) {
        val cont=view.context
        getStoreData = GetAndStoreData(cont)
        counterStep = getStoreData.getCurrentPage()
        animationInAction = AnimationInAction(view)
        getAndStoreData = GetAndStoreData(cont)

    }
}


/*fun stam(){
    *//* val st="courses"
     val st1="11"
     val st2="name"*//*
    val st="talker1"
    val st1="3"
    val st2="main"
    var db=FirebaseFirestore.getInstance()
    var doc=db.collection(st).document(st1).get().addOnCompleteListener { task ->
        if (task.result!!.exists()){
            Log.d("clima","name->${task.result?.getString(st2)}")
        }else{

        }
    }
}*/

/*fun retriveDataFromFirebase():String{
    val st="courses"
    val st1="12"
    val st2="name"

    *//*val st="talker1"
    val st1="3"
    val st2="main"*//*

    var jsonS:String?=null
    var db=FirebaseFirestore.getInstance()
    var doc=db.collection(st).document(st1).get().addOnCompleteListener { task ->
        if (task.result!!.exists()){
            jsonS=task.result?.getString(st2)
            Log.d("clima","name->${task.result?.getString(st2)}")
        }else{
            jsonS="no data"
            Log.d("clima","name-> no name")

        }
    }
    return jsonS!!
}*/


/*
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



