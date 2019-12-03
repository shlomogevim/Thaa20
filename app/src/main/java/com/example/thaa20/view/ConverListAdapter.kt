package com.example.thaa20.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thaa20.R
import com.example.thaa20.util.Conversation
import kotlinx.android.synthetic.main.item_talking1.view.*

class ConverListAdapter(private val converList: ArrayList<Conversation>) :
    RecyclerView.Adapter<ConverListAdapter.ConverHolder>() {

     val PRIFEX="שיחה מספר: "

    fun updateConverList(newConverList: List<Conversation>) {
        converList.clear()
        converList.addAll(newConverList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConverHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_talking1, parent, false)
        return ConverHolder(view)

    }

    override fun getItemCount() = converList.size


    override fun onBindViewHolder(holder: ConverListAdapter.ConverHolder, position: Int) {
        holder.view.tv_talkTitle.text = PRIFEX+ converList[position].numC.toString()
        holder.view.tv_talkDiscription.text=converList[position].explanation
        holder.view.layout_ConversItem.setOnClickListener {
           val action=ListTalkingDirections.actionToDetails()
            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    class ConverHolder(val view: View) : RecyclerView.ViewHolder(view)

}

/*


    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.view.animalName1.text=animalList[position].name
        holder.view.animalImage.loadImage(animalList[position]
            .imageUrl, getProgressDrawable(holder.view.context))


        holder.view.animalLayout.setOnClickListener {
            val action=ListFragmentDirections.
                actionDetails(animalList[position])
            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    class AnimalViewHolder(val view: View):RecyclerView.ViewHolder(view)

}*/
