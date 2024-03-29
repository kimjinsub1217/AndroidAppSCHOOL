package com.example.mini02_boardproject01

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mini02_boardproject01.SearchList.Companion.ListSearch
import com.example.mini02_boardproject01.databinding.ItemViewBinding
import com.example.mini02_boardproject01.vm.PostViewModel

//class ResultAdapter(val postViewModel: PostViewModel) :
//    RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {
//
//    inner class ResultViewHolder(itemView: ItemViewBinding) : RecyclerView.ViewHolder(itemView.root) {
//        val textView: TextView = itemView.itemName
//        val nickNameView: TextView=itemView.rowPostListNickName
//
//        init {
//
//
//            itemView.root.setOnClickListener {
//                // 사용자가 선택한 메모의 인덱스값을 전달한다.
////                val idx = ListSearch[adapterPosition].idx
////                val newBundle = Bundle()
////                newBundle.putInt("memo_idx", idx)
////                mainActivity.replaceFragment(MainActivity.POST_READ_FRAGMENT, true,  null)
//
//                val readPostIdx = postViewModel.postDataList.value?.get()
//
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
//        val view = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val viewItem = ResultViewHolder(view)
//
//        view.root.layoutParams = ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//
//        return viewItem
//    }
//
//    override fun getItemCount(): Int {
//        return ListSearch.size
//    }
//
//    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
//        holder.textView.text = "제목 : ${ListSearch[position].title}"
//        holder.nickNameView.text = "작성자 : ${ListSearch[position].details}"
//    }
//
//}