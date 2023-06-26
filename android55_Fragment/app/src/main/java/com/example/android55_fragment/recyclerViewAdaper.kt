package com.example.android55_fragment

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android55_fragment.databinding.ItemInfoBinding
import com.example.android55_fragment.infoList.dataList


class recyclerViewAdaper:RecyclerView.Adapter<recyclerViewAdaper.ViewHolderClass>() {
    inner class ViewHolderClass(itemBinding: ItemInfoBinding):RecyclerView.ViewHolder(itemBinding.root){
        var nameText: TextView =itemBinding.nameTextView
        var ageText:TextView=itemBinding.ageTextView
        var korText:TextView=itemBinding.korTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val binding = ItemInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolderClass(binding)
    }

    override fun getItemCount()=dataList.size

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        holder.nameText.text = "${dataList[position].name}"
        holder.ageText.text = "${dataList[position].age}"
        holder.korText.text = "${dataList[position].kor}"
    }
}