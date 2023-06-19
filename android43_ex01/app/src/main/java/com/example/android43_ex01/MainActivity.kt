package com.example.android43_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android43_ex01.databinding.ActivityMainBinding
import com.example.android43_ex01.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>
    var dataList = mutableListOf<DataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val c1 = ActivityResultContracts.StartActivityForResult()
        addActivityResultLauncher = registerForActivityResult(c1) {
            if (it.resultCode == RESULT_OK) {
                val data1 = it.data?.getStringExtra("data1")
                val data2 = it.data?.getStringExtra("data2")

                val t1 = DataClass(data1!!, data2!!)
                dataList.add(t1)

                val adapter = binding.recyclerView.adapter as RecyclerAdapterClass
                adapter.notifyDataSetChanged()
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
            R.id.mainMenuAdd -> {
                val addIntent = Intent(this@MainActivity, AddActivity::class.java)
                addActivityResultLauncher.launch(addIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    inner class RecyclerAdapterClass :
        RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>() {
        inner class ViewHolderClass(rowBinding: RowBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {
            var textViewRow1: TextView = rowBinding.textViewRow1
            var textViewRow2: TextView = rowBinding.textViewRow2
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            rowBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount() = dataList.size

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.textViewRow1.text = "data1 : ${dataList[position].data1}"
            holder.textViewRow2.text = "data2 : ${dataList[position].data2}"
        }
    }


}

data class DataClass(var data1: String, var data2: String)