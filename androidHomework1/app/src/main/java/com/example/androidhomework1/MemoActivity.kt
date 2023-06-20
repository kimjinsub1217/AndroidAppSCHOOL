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
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework1.databinding.ActivityMemoBinding
import com.example.androidhomework1.databinding.ItemInfoBinding
import com.example.androidhomework1.databinding.MemoItemBinding

class MemoActivity : AppCompatActivity() {
    lateinit var binding: ActivityMemoBinding
    lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>
    var memoList = mutableListOf<memoClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val c1 = ActivityResultContracts.StartActivityForResult()
        addActivityResultLauncher = registerForActivityResult(c1) {
            if (it.resultCode == RESULT_OK) {
                val memoData = it.data?.getStringExtra("memo")
                val t1 = memoClass(memoData!!)
                memoList.add(t1)

                val adapter = binding.recyclerView.adapter as MainActivity.RecyclerAdapterClass
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.memoAddButton -> {
                val memoAddIntent = Intent(this@MemoActivity, MemoItemActivity::class.java)
                addActivityResultLauncher.launch(memoAddIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class RecyclerAdapterClass :
        RecyclerView.Adapter<RecyclerAdapterClass.MemoViewHolderClass>() {
        inner class MemoViewHolderClass(memoItemBinding: MemoItemBinding) :
            RecyclerView.ViewHolder(memoItemBinding.root), View.OnCreateContextMenuListener,
            View.OnClickListener {
            var memoTextView: TextView = memoItemBinding.memoTextView

            init {
                itemView.setOnCreateContextMenuListener(this)
                memoItemBinding.root.setOnClickListener(this)
            }

            override fun onCreateContextMenu(
                menu: ContextMenu?,
                v: View?,
                menuInfo: ContextMenu.ContextMenuInfo?
            ) {
                menu?.setHeaderTitle(memoList[adapterPosition].memo)
                menuInflater.inflate(R.menu.item_memo_menu, menu)

                menu?.findItem(R.id.DeleteButton)?.setOnMenuItemClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        memoList.removeAt(position)
                        notifyItemRemoved(position)
                    }
                    true
                }

//                menu?.findItem(R.id.DeleteButton)?.setOnMenuItemClickListener {
//                    val position = adapterPosition
//                    if (position != RecyclerView.NO_POSITION) {
//                        modifiyPosition = position
//                        val modifiyIntent = Intent(this@MemoActivity, ModifiyActivity::class.java)
//                        modifiyActivityResultLauncher.launch(modifiyIntent)
//
//
//                    }
//                    true
//                }

            }

            override fun onClick(v: View?) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
//                    val clickedItem = dataList[position]
//                    Log.d("확인", "Clicked item: ${clickedItem.name}")
//                    val memoIntent = Intent(this@MainActivity, MemoActivity::class.java)
//                    memoIntent.putExtra("name", clickedItem.name)
//                    this@MainActivity.startActivity(memoIntent)
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
            holder.memoTextView.text = "메모$ {position+1} :${memoList[position].memo}"
        }


    }

}

data class memoClass(var memo: String)
