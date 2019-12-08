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
    lateinit var arrangeLayout: ArrangeLayout
    lateinit var talkList: ArrayList<Talker>
    var counterStep = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.talking_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            conv = DetailsTalkingArgs.fromBundle(it).currentConvers
            // tv.text=" Hi Man You SeleT Item num "+ conv!!.numC
        }


        setupParams(view)
        arrangeLayout.drawListView()


       // stam()
        val jso=stam1()
        Log.d("clima","out out side $jso")



        //  val jsonS = retriveDataFromFirebase()


      //  talkList=getTalkList1()


       // arrangeLayout.prepareAllTheListViewParam()



        arrangeLayout.setPosition(1)
    }

    fun stam1():String{

        var jso=""

        val st="courses"
        val st1="11"
        val st2="name"
         /*val st="talker1"
         val st1="3"
         val st2="main"*/
        var db=FirebaseFirestore.getInstance()
        var doc=db.collection(st).document(st1).get().addOnCompleteListener { task ->
            if (task.result!!.exists()){
                Log.d("clima","name->${task.result?.getString(st2)}")
                jso=task.result?.getString(st2)!!
                Log.d("clima","inside $jso")
            }else{

            }
        }
        Log.d("clima","out side $jso")
        return jso
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

            }
        }
    }
    fun retriveDataFromFirebase():String{
        val st="courses"
        val st1="12"
        val st2="name"

        /*val st="talker1"
        val st1="3"
        val st2="main"*/

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
    }

    fun getTalkList1(): ArrayList<Talker> {
        var jsonS:String?
        var talkList1 = ArrayList<Talker>()

        jsonS = getStoreData.getGsonString()

        if (jsonS == null) {
            jsonS = retriveDataFromFirebase()
        }
        if (jsonS == null) {
            talkList1 = getStoreData.createTalkListFromTheStart()
        } else {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Talker>>() {}.type
            talkList1 = gson.fromJson(jsonS, type)
        }
        getStoreData.saveTalkingListInPref(talkList1)

      /*        Handler().postDelayed(
                  {
                      saveJsonString(jsonS)
                  }, 5000
              )
          }*/

        return talkList1
    }






    private fun setupParams(view: View) {
        getStoreData = GetAndStoreData(context!!)
        counterStep = getStoreData.getPage()
        animationInAction = AnimationInAction(context!!, mainDetailLayout)
        arrangeLayout = ArrangeLayout(view)

    }
}

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



