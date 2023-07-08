package com.example.android70_ex03.adapter


import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android70_ex03.MainActivity
import com.example.android70_ex03.databinding.RowMemoBinding
import com.example.android70_ex03.db.Memo.Companion.memoList

class MemoAdapter(private val mainActivity: MainActivity) :
    RecyclerView.Adapter<MemoAdapter.CategoryViewHolderClass>() {
    inner class CategoryViewHolderClass(rowMemoBinding: RowMemoBinding) :
        RecyclerView.ViewHolder(rowMemoBinding.root) {
        var categoryName = rowMemoBinding.menuName
        init {
            rowMemoBinding.root.setOnClickListener {
                mainActivity.memoPosition = adapterPosition
                mainActivity.replaceFragment(MainActivity.MEMO_VIEW_CONTENT_FRAGMENT, true, true)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemoAdapter.CategoryViewHolderClass {
        val rowCategoryBinding =
            RowMemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        Log.i("포지션 확인", mainActivity.categoryPosition.toString())
        if (currentItem.categoryData == mainActivity.categoryPosition + 1) {
            holder.categoryName.text = currentItem.memoTitleData
        } else {
            holder.categoryName.visibility = View.GONE
        }

    }
}