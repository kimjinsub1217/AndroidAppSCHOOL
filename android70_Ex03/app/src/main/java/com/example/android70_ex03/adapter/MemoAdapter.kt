package com.example.android70_ex03.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android70_ex03.MainActivity
import com.example.android70_ex03.databinding.RowMenuBinding
import com.example.android70_ex03.db.Category.Companion.categoryList
import com.example.android70_ex03.db.Memo.Companion.memoList

class MemoAdapter(private val mainActivity: MainActivity) :
    RecyclerView.Adapter<MemoAdapter.CategoryViewHolderClass>() {
    inner class CategoryViewHolderClass(rowMemoBinding: RowMenuBinding) :
        RecyclerView.ViewHolder(rowMemoBinding.root){

        var categoryName: TextView = rowMemoBinding.menuName

        init {
            rowMemoBinding.root.setOnClickListener {
                mainActivity.rowPosition = adapterPosition
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemoAdapter.CategoryViewHolderClass {
        val rowCategoryBinding =
            RowMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val categoryViewHolderClass = CategoryViewHolderClass(rowCategoryBinding)

        rowCategoryBinding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, 
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return categoryViewHolderClass
    }

    override fun getItemCount() = memoList.size

    override fun onBindViewHolder(holder: MemoAdapter.CategoryViewHolderClass, position: Int) {
        val currentItem = memoList[position]
        Log.i("포지션 확인",mainActivity.rowPosition.toString())
        if (currentItem.categoryData == mainActivity.rowPosition){
            holder.categoryName.text = currentItem.memoTitleData
        }

    }
}