package com.example.thaa20.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.thaa20.R
import com.example.thaa20.model.Animal
import com.example.thaa20.model.Conversation
import com.example.thaa20.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.talking_list.*
import java.util.*


class ListTalking : Fragment() {
    lateinit var viewModel:ListViewModel
    private val listAdapter=ConverListAdapter(arrayListOf())

    private val animalListDataObserver=Observer<List<Conversation>>{list->
        list?.let {
            conversList.visibility=View.VISIBLE
            listAdapter.updateConverList(it)
        }

    }
    private val loadingDataObserver=Observer<Boolean>{isLoading->
        loadingView.visibility=if (isLoading) VISIBLE else GONE
        if (isLoading){
            listError.visibility= GONE
            conversList.visibility= GONE
        }

    }
    private val errorDataObserver=Observer<Boolean>{isError->
        listError.visibility=if (isError) VISIBLE else GONE
        if (isError){
            loadingView.visibility= GONE
            conversList.visibility= GONE
        }

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.talking_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.convers.observe(this,animalListDataObserver)
        viewModel.loading.observe(this,loadingDataObserver)
        viewModel.loadError.observe(this,errorDataObserver)
        viewModel.refresh()
        conversList.apply {
            layoutManager=LinearLayoutManager (context)
            adapter=listAdapter
        }
        refreshLayout.setOnRefreshListener {
            conversList.visibility= GONE
            listError.visibility= GONE
            loadingView.visibility= VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing=false
        }


    }

}


