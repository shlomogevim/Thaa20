package com.example.thaa20.util

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.helper_view_layout.view.*

class ManipulateTalker( val view: View,var talker: Talker) {

    val contex = view.context
    var styleList = arrayListOf<String>()
    var paraList = arrayListOf<String>()
    var ttParaList = arrayListOf<String>()
    var actionList = arrayListOf<String>()
    var lastTalker = Talker()

    fun initListsView() {
        initStyleListView(view, talker)


    }

    private fun initStyleListView(view: View, talker: Talker) {
        view.style_ListView.setOnItemClickListener { _, _, position, _ ->
            tranferTalkItem(0)
            if (position == 16) {     // ther is NB
                talker.backExist = false
            } else {
                talker.backExist = true
                talker.styleNum = styleList[position].toInt()
            }
            upgradeTalker()
        }
    }

    private fun tranferTalkItem(ind: Int) {
        if (ind == 0) {
            lastTalker = talker.copy()
        } else {
            talker = lastTalker.copy()
        }
    }

    private fun upgradeTalker() {

        var bo = true
        if (talker.textSize < 3) {
            talker.textSize = 3f
            Toast.makeText(contex, "Text Size too small", Toast.LENGTH_SHORT).show()
            bo = false
        }
        if (talker.dur < 100) {
            talker.textSize = 100f
            Toast.makeText(contex, "Duration too small", Toast.LENGTH_SHORT).show()
            bo = false
        }
          if (bo) {
            trasferStyle()
            updateTitleTalkerSituation()
            moveTheAnimation()
        }

    }



    private fun moveTheAnimation() {


        /*var counterStep=talker.numTalker
        if (counterStep > 84) counterStep = 84
        if ((counterStep == 84 && SHOW_POSITION) || (counterStep == 84 && PUBLISH_POSITION)) {
            counterStep = 1
            // finish()
        }
        updateTitleTalkerSituation()
        if (counterStep < 1) counterStep = 1

        //  counterStep = 1

        manMode = counterStep % 2 != 0*/



      //  animationInAction.excuteTalker(talker)
      //  getAndStoreData.savePage(talker.numTalker)
    }

    private fun trasferStyle() {
        val style = findStyleObject(talker.styleNum)
        talker.colorBack = style.colorBack
        talker.colorText = style.colorText
    }

    private fun updateTitleTalkerSituation() {
        with(talker) {
            val text =
                "l=${takingArray.size}sty=$styleNum anim=$animNum size=${textSize.toInt()}" +
                        " bord=$borderWidth dur=$dur sw=$swingRepeat"
            view.tvAnimatinKind.text = text
            view.tvPage.text = numTalker.toString()
        }
    }

    private fun findStyleObject(index: Int): StyleObject {
        var style1 = StyleObject()
        var bo = true
        var i = 0
        while (bo && i < Helper.Page.styleArray.size) {

            if (Helper.Page.styleArray[i].numStyleObject == index) {
                style1 = Helper.Page.styleArray[i]
                bo = false
            }
            i++
        }
        if (bo) style1 = Helper.Page.styleArray[10]
        return style1
    }

}


