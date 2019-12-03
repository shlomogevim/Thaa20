package com.example.thaa20.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.thaa20.util.Conversation

class ListViewModel(application: Application): AndroidViewModel(application) {
    val convers by lazy { MutableLiveData<List<Conversation>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    fun refresh(){
        getConvers()
    }

    private fun getConvers() {

        val convesList=ArrayList<Conversation>()
        for (index in 1..20){
            convesList.add(Conversation(index,"Conversation no $index","Some explanation about conversation number : $index"))
        }
        convers.value=convesList
        loadError.value=false
        loading.value=false
    }

}