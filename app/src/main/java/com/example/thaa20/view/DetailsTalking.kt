package com.example.thaa20.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.thaa20.R
import com.example.thaa20.util.*
import kotlinx.android.synthetic.main.talking_details.*


class DetailsTalking : Fragment() {

    var conv:Convers? = null
    lateinit var  getStoreData:GetAndStoreData
    lateinit var animationInAction :AnimationInAction
    lateinit var arrangeLayout: ArrangeLayout
    lateinit var talkList: ArrayList<Talker>
    var counterStep=1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.talking_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       arguments?.let {
           conv=DetailsTalkingArgs.fromBundle(it).currentConvers
          // tv.text=" Hi Man You SeleT Item num "+ conv!!.numC
       }

        setupParams(view)

        arrangeLayout.setPosition(1)

        arrangeLayout.prepareAllTheListViewParam()

       // talkList=getStoreData.createTalkList()




    }

    private fun setupParams(view: View) {
            getStoreData=GetAndStoreData(context!!)
            counterStep = getStoreData.getPage()
            animationInAction = AnimationInAction(context!!, mainDetailLayout)
            arrangeLayout= ArrangeLayout(view)

    }
}
