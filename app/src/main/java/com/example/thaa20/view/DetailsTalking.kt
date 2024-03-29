package com.example.thaa20.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.example.thaa20.R
import kotlinx.android.synthetic.main.talking_details.*


class DetailsTalking : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.talking_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val action =DetailsTalkingDirections.actionToList()
        toListBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(action)
        }
    }


}
