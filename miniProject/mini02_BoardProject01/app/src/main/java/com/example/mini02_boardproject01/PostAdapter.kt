package com.example.mini02_boardproject01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mini02_boardproject01.MainActivity.Companion.mainActivity
import com.example.mini02_boardproject01.databinding.ItemViewBinding
import com.example.mini02_boardproject01.vm.PostViewModel

class PostAdapter(val postViewModel: PostViewModel) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: ItemViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        val textView: TextView = itemView.itemName
        val nickNameView: TextView=itemView.rowPostListNickName

        init {
            itemView.root.setOnClickListener {
                // 사용자가 선택한 메모의 인덱스값을 전달한다.
//                val idx = ListSearch[adapterPosition].idx
//                val newBundle = Bundle()
//                newBundle.putInt("memo_idx", idx)
//                mainActivity.replaceFragment(MainActivity.POST_READ_FRAGMENT,true,null)

                itemView.root.setOnClickListener {
                    // 항목 번째 글 번호를 가져온다.
                    val readPostIdx = postViewModel.postDataList.value?.get(adapterPosition)?.postIdx
                    val newBundle = Bundle()
                    newBundle.putLong("readPostIdx", readPostIdx!!)
                    mainActivity.replaceFragment(MainActivity.POST_READ_FRAGMENT, true, newBundle)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewItem = PostViewHolder(view)

        view.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return viewItem
    }

    override fun getItemCount(): Int {
        return postViewModel.postDataList.value?.size!!
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.textView.text = "제목 : ${postViewModel.postDataList.value?.get(position)?.postSubject}"
//        holder.nickNameView.text = "작성자 : ${viewModel.postDataList.value?.get(position)}"
    }

}