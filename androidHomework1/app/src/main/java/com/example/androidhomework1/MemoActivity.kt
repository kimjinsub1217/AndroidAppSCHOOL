package com.example.androidhomework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework1.databinding.ActivityMemoBinding
import com.example.androidhomework1.databinding.MemoItemBinding
import com.example.androidhomework1.memeoList.myMemoList

class MemoActivity : AppCompatActivity() {

    lateinit var binding: ActivityMemoBinding
    lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var modifiyMemoActivityResultLauncher: ActivityResultLauncher<Intent>
    var memoList = mutableListOf<memeoList.MemoClass>()
    var modifiyMemoPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val memoName = intent.getStringExtra("name")



        if (memoList.isEmpty()) {
            val memoListFromPosition = myMemoList["$memoName"]
            memoList = memoListFromPosition?.toMutableList() ?: mutableListOf()
        }

        val c1 = ActivityResultContracts.StartActivityForResult()
        addActivityResultLauncher = registerForActivityResult(c1) {
            if (it.resultCode == RESULT_OK) {
                val name = it.data?.getStringExtra("memoName")
                val detail = it.data?.getStringExtra("memoDetail")
                val t1 = memeoList.MemoClass(name!!, detail!!)
                memoList.add(t1)

                val adapter = binding.recyclerView.adapter as MemoActivity.RecyclerAdapterClass
                adapter.notifyDataSetChanged()

            }
        }

        modifiyMemoActivityResultLauncher = registerForActivityResult(c1) {
            if (it.resultCode == RESULT_OK) {
                val modifyMemoName = it.data?.getStringExtra("modifyName")
                val modifyMemoDetail = it.data?.getStringExtra("modifyDetail")

                if (modifyMemoName != null) {
                    val adapter = binding.recyclerView.adapter as MemoActivity.RecyclerAdapterClass
                    if (modifiyMemoPosition != RecyclerView.NO_POSITION) {
                        memoList[modifiyMemoPosition].name = modifyMemoName
                        adapter.notifyItemChanged(modifiyMemoPosition)
                    }

                }

                if (modifyMemoDetail != null) {
                    val adapter = binding.recyclerView.adapter as MemoActivity.RecyclerAdapterClass
                    if (modifiyMemoPosition != RecyclerView.NO_POSITION) {
                        memoList[modifiyMemoPosition].detail = modifyMemoDetail
                        adapter.notifyItemChanged(modifiyMemoPosition)
                    }
                }
            }
        }

        binding.run {

            memoList.text = "${memoName.toString()} 메모 목록"
            recyclerView.run {
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(this@MemoActivity)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_memo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.memoAddButton -> {
                val addIntent = Intent(this@MemoActivity, MemoItemActivity::class.java)
                addActivityResultLauncher.launch(addIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class RecyclerAdapterClass :
        RecyclerView.Adapter<RecyclerAdapterClass.MemoViewHolderClass>() {
        inner class MemoViewHolderClass(memoItemBinding: MemoItemBinding) :
            RecyclerView.ViewHolder(memoItemBinding.root), View.OnCreateContextMenuListener,
            View.OnClickListener {
            var name: TextView = memoItemBinding.nameTextView

            init {
                itemView.setOnCreateContextMenuListener(this)
                memoItemBinding.root.setOnClickListener(this)
            }

            override fun onCreateContextMenu(
                menu: ContextMenu?,
                v: View?,
                menuInfo: ContextMenu.ContextMenuInfo?
            ) {
                menu?.setHeaderTitle(memoList[adapterPosition].name)
                menuInflater.inflate(R.menu.item_memo_menu, menu)

                menu?.findItem(R.id.deleteMemoButton)?.setOnMenuItemClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        memoList.removeAt(position)
                        notifyItemRemoved(position)
                    }
                    true
                }

                menu?.findItem(R.id.modifyMemoButton)?.setOnMenuItemClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        modifiyMemoPosition = position
                        val modifiyIntent =
                            Intent(this@MemoActivity, ModifyMemoActivity::class.java)
                        modifiyMemoActivityResultLauncher.launch(modifiyIntent)


                    }
                    true
                }

            }

            override fun onClick(v: View?) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = memoList[position]
                    val memoIntent = Intent(this@MemoActivity, DetailActivity::class.java)
                    memoIntent.putExtra("detail", clickedItem.detail)
                    this@MemoActivity.startActivity(memoIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolderClass {
            val itemBinding = MemoItemBinding.inflate(layoutInflater)
            val viewHolderClass = MemoViewHolderClass(itemBinding)
            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            itemBinding.root.layoutParams = params
            return viewHolderClass
        }

        override fun getItemCount() = memoList.size

        override fun onBindViewHolder(holder: MemoViewHolderClass, position: Int) {
            holder.name.text = "${memoList[position].name}"

        }


    }

    override fun onDestroy() {
        Log.i("종료됨", "종료됨")
        val memoName = intent.getStringExtra("name")
        Log.i("종료됨", "$memoName")
        Log.i("종료됨", "$memoList")
        myMemoList["$memoName"] = memoList
        super.onDestroy()
    }
}


