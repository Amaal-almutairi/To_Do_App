package com.example.todoapp

import android.graphics.Color

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemRowBinding

class myAdapter(private val items: ArrayList<item_list>): RecyclerView.Adapter<myAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
// this function will show the check box into recycler view and display it in screen

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val i = items[position]

        holder.binding.apply {
            textv.text = i.txt
            checkId.isChecked = i.checked_list
            // create OnClickListener and activate it so we can use it , if the user check the list
            // the color will change to gray Otherwise it will change to black

            checkId.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    textv.setTextColor(Color.GRAY)
                }else{
                    textv.setTextColor(Color.BLACK)
                }
                i.checked_list = !i.checked_list
            }
        }
    }

    override fun getItemCount() = items.size
   // we create this function to delete the items

    fun deleteList(){
        items.removeAll{ i -> i.checked_list }

        // her to update the list if there is any changes
        notifyDataSetChanged()
    }

}


