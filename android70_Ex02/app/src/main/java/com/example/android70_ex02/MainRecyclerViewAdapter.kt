package com.example.android70_ex02

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android70_ex01.MemoClass
import com.example.android70_ex01.student.Companion.memoList
import com.example.android70_ex02.databinding.RowMainBinding

class MainRecyclerViewAdapter(private val mainActivity: MainActivity, private val memoList: MutableList<MemoClass>) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolderClass>() {

    inner class MainViewHolderClass(rowMainBinding: RowMainBinding) :
        RecyclerView.ViewHolder(rowMainBinding.root) {
        var textViewMainRowDate: TextView
        var textViewMainRowName: TextView

        init {
            textViewMainRowDate = rowMainBinding.textViewMainRowDate
            textViewMainRowName = rowMainBinding.textViewMainRowName

            rowMainBinding.root.setOnClickListener {
                mainActivity.rowPosition = adapterPosition
                mainActivity.replaceFragment(MainActivity.RESULT_FRAGMENT, true, true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolderClass {
        val rowMainBinding = RowMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val mainViewHolderClass = MainViewHolderClass(rowMainBinding)

        rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return mainViewHolderClass
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

    override fun onBindViewHolder(holder: MainViewHolderClass, position: Int) {
        val currentItem = memoList[position]
        holder.textViewMainRowDate.text = currentItem.dateData
        holder.textViewMainRowName.text = currentItem.titleData
    }
}