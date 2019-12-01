package com.example.thaa20.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.example.thaa20.R
import kotlinx.android.synthetic.main.talking_list.*


class ListTalking : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.talking_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val action=ListTalkingDirections.actionToDetails()
        toDetailsTalikingBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(action)
        }

    }


}
