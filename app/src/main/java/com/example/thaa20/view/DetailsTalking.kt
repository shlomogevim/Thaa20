package com.example.thaa20.view


import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.thaa20.R
import com.example.thaa20.util.*
import com.github.florent37.viewanimator.ViewAnimator
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.talking_details.*
import kotlinx.android.synthetic.main.talking_details.view.*


class DetailsTalking : Fragment() {

    var conv: Convers? = null
    lateinit var getStoreData: GetAndStoreData
    lateinit var animationInAction: AnimationInAction
    lateinit var getAndStoreData: GetAndStoreData
    lateinit var arrangeLayout: ArrangeLayout
    lateinit var buttonSpace: ButtonSpace
    lateinit var talkList: ArrayList<Talker>
    var showPosition = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.talking_details, container, false)
    }

    fun talkC() = talkList[getAndStoreData.getCurrentPage()]


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            conv = DetailsTalkingArgs.fromBundle(it).currentConvers
            // tv.text=" Hi Man You SeleT Item num "+ conv!!.numC
        }



        getStoreData = GetAndStoreData(view)
        animationInAction = AnimationInAction(view)
        getAndStoreData = GetAndStoreData(view)

        getAndStoreData.saveCurrentFile(20)

        getTalkList()

        arrangeLayout = ArrangeLayout(view)
        buttonSpace = ButtonSpace(view)

        backGroundConfigration()

        arrangeLayout.drawListView()

        arrangeLayout.operateListView()

        buttonSpace.initButton()

        showPosition = 3

        getAndStoreData.saveShowPosition(showPosition)

        //  getAndStoreData.saveCurrentPage(1)

        arrangeLayout.setLayoutShowMode()
        waitToAnnimateEnded()

        animationInAction.excuteTalker(talkC())

    }

    @SuppressLint("RestrictedApi")
    private fun waitToAnnimateEnded() {
        Utile.listener1 = { it1, _ ->

            fab.visibility = VISIBLE
            fab1.visibility = VISIBLE
            ViewAnimator
                .animate(view?.fab)
                .alpha(0f, 1f)
                .andAnimate(view?.fab1)
                .alpha(0f, 1f)
                .duration(3500)
                .start()
        }

    }

    fun backGroundConfigration() {
        val animationDrawable = imageView.background as? AnimationDrawable
        animationDrawable?.setEnterFadeDuration(2000)
        animationDrawable?.setExitFadeDuration(4000)
        animationDrawable?.start()
    }

    private fun getTalkList() {
        talkList = getStoreData.createTalkListFromPref()

        if (talkList.size == 0) {
            createTalkingListFromFirestore()  //open tool->firebase->firestore see if all depen. ok
            //rebuilt project
            // run and wait for result
        }
        if (talkList.size == 0) {
            // !! must be in remarked becaseus it inteferring to the firebase
            //  talkList=getStoreData.createTalkListFromTheStart()

        }
        getAndStoreData.saveTalkingListInPref(talkList)
    }

    fun createTalkingListFromFirestore(): ArrayList<Talker> {
        var talkList1 = ArrayList<Talker>()
        var jsonS: String
        /*val st="courses"
        val st1="11"
        val st2="name"*/
        val st = "talker1"
        val st1 = "3"
        val st2 = "main"
        var db = FirebaseFirestore.getInstance()
        db.collection(st).document(st1).get().addOnCompleteListener { task ->
            if (task.result!!.exists()) {
                jsonS = task.result?.getString(st2)!!
                val gson = Gson()
                val type = object : TypeToken<ArrayList<Talker>>() {}.type
                talkList1 = gson.fromJson(jsonS, type)
                getStoreData.saveTalkingListInPref(talkList1)
                Log.d("clima", " $jsonS")
            }
        }
        return talkList1
    }


    fun storeTalkingListFromFirestore(talkList: ArrayList<Talker>, index: Int) {

        // must transfer value with  key-value format
        val st = "talker1"
        val st1 = index.toString()
        val gson = Gson()
        val jsonS = gson.toJson(talkList)
        var db = FirebaseFirestore.getInstance()
        var talker = HashMap<String, Any>()
        talker.put("index", st1)
        jsonS?.let { talker.put("main", it) }
        db.collection(st).document(st1).set(talker)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Saving is succsses", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        context,
                        "Not Save because \${task.exception?.message",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }


    }


    /*   db.collection("talker1").document(versia.toString()).set(talker)
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


   } */


    /* db.collection("courses").document("11").set(course).addOnCompleteListener { task ->
           if (task.isSuccessful){
               Toast.makeText(this,"Its ok",Toast.LENGTH_LONG).show()
           }else{
               Toast.makeText(this,"Its nottt ok",Toast.LENGTH_LONG).show()

           }
        }*/


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



