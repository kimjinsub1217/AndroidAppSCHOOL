package com.example.android60_ex01

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android60_ex01.DataClass.Companion.studentList
import com.example.android60_ex01.databinding.ItemInfoBinding


class recyclerViewAdaper(private val mainActivity: MainActivity):RecyclerView.Adapter<recyclerViewAdaper.ViewHolderClass>() {
    inner class ViewHolderClass(itemBinding: ItemInfoBinding):RecyclerView.ViewHolder(itemBinding.root){
        var name: TextView =itemBinding.name
        init{
            itemBinding.root.setOnClickListener {

                // 터치한 항목의 위치값을 변수에 담아준다.
                mainActivity.rowPosition = adapterPosition
                // ResultFragment로 변경한다.
                mainActivity.replaceFragment(FragmentName.FRAGMENT_INFO, true, true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val binding = ItemInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        val params = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        binding.root.layoutParams = params

        return ViewHolderClass(binding)
    }

    override fun getItemCount()=studentList.size

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        holder.name.append(studentList[position].name)

    }
}