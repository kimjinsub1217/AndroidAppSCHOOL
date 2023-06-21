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
import com.example.androidhomework1.databinding.ActivityMainBinding
import com.example.androidhomework1.databinding.ItemInfoBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var modifiyActivityResultLauncher: ActivityResultLauncher<Intent>
    var dataList = mutableListOf<DataClass>()
    var modifiyPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val c1 = ActivityResultContracts.StartActivityForResult()

        addActivityResultLauncher = registerForActivityResult(c1) {
            if (it.resultCode == RESULT_OK) {
                val categoryName = it.data?.getStringExtra("categoryName")
                val t1 = DataClass(categoryName!!)
                Log.d("데이터들어옴", "$t1")
                dataList.add(t1)

                val adapter = binding.recyclerView.adapter as RecyclerAdapterClass
                adapter.notifyDataSetChanged()
            }
        }

        modifiyActivityResultLauncher = registerForActivityResult(c1) {
            if (it.resultCode == RESULT_OK) {
                val modifyCategoryName = it.data?.getStringExtra("modify")


                if (modifyCategoryName != null) {


                    val adapter = binding.recyclerView.adapter as RecyclerAdapterClass

                    if (modifiyPosition != RecyclerView.NO_POSITION) {

                        dataList[modifiyPosition].name = modifyCategoryName
                        adapter.notifyItemChanged(modifiyPosition)
                    }
                }
            }
        }


        binding.run {
            recyclerView.run {
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.categoryButton -> {
                val addIntent = Intent(this@MainActivity, AddCategoryActivity::class.java)
                addActivityResultLauncher.launch(addIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class RecyclerAdapterClass :
        RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>() {
        inner class ViewHolderClass(itemBinding: ItemInfoBinding) :
            RecyclerView.ViewHolder(itemBinding.root), View.OnCreateContextMenuListener, View.OnClickListener {
            var categoryName: TextView = itemBinding.categoryName

            init {
                itemView.setOnCreateContextMenuListener(this)
                itemBinding.root.setOnClickListener(this)
            }

            override fun onCreateContextMenu(
                menu: ContextMenu?,
                v: View?,
                menuInfo: ContextMenu.ContextMenuInfo?
            ) {
                menu?.setHeaderTitle(dataList[adapterPosition].name)
                menuInflater.inflate(R.menu.item_category_menu, menu)

                menu?.findItem(R.id.modifyButton)?.setOnMenuItemClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        modifiyPosition = position
                        val modifiyIntent = Intent(this@MainActivity, ModifiyActivity::class.java)
                        modifiyIntent.putExtra("name",dataList[adapterPosition].name)
                        modifiyActivityResultLauncher.launch(modifiyIntent)

                    }
                    true
                }

                menu?.findItem(R.id.DeleteButton)?.setOnMenuItemClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        dataList.removeAt(position)
                        notifyItemRemoved(position)
                    }
                    true
                }
            }

            override fun onClick(v: View?) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = dataList[position]
                    Log.d("확인", "Clicked item: ${clickedItem.name}")
                    val memoIntent = Intent(this@MainActivity, MemoActivity::class.java)
                    memoIntent.putExtra("name", clickedItem.name)
                    memoIntent.putExtra("position", position)
                    this@MainActivity.startActivity(memoIntent)

                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val itemBinding = ItemInfoBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(itemBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            itemBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount() = dataList.size

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.categoryName.text = "${dataList[position].name}"
        }
    }
}

data class DataClass(var name: String)