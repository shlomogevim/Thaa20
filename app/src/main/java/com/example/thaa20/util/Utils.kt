package com.example.thaa20.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.thaa20.R

data class Conversation (
    val numC:Int?=null,
    val title:String?=null,
    val explanation:String?=null
)

class SharePrepfrencesHelpere(context: Context){

    private val PREF_API_KEY="pref api key"
    private val prefs=androidx.preference.PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    fun saveApiKey(key:String?){
        prefs.edit().putString(PREF_API_KEY,key).apply()
    }
    fun  getApiKey()=prefs.getString(PREF_API_KEY,null)
}

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val option = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(uri)
        .into(this)
}