package com.example.mini02_boardproject01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mini02_boardproject01.databinding.ItemViewBinding

class PostAdapter(private val postList: List<String>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: ItemViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        val textView: TextView = itemView.itemName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val viewItem = PostViewHolder(view)

        view.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return viewItem
    }

    override fun getItemCount(): Int {
        return postList.size
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.textView.text = postList[position]
    }

}