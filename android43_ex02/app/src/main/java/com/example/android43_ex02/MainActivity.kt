package com.example.android43_ex02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android43_ex02.databinding.ActivityMainBinding
import com.example.android43_ex02.databinding.ItemInfoBinding

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
                val fruitData = it.data?.getStringExtra("fruit")
                val quantityData = it.data?.getIntExtra("quantity", -1)
                val originData = it.data?.getStringExtra("origin")
                val t1 = DataClass(fruitData!!, quantityData!!, originData!!)
                Log.d("데이터들어옴","$t1")
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
            R.id.addButton -> {
                val addIntent = Intent(this@MainActivity, ItemActivity::class.java)
                addActivityResultLauncher.launch(addIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class RecyclerAdapterClass :
        RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>() {
        inner class ViewHolderClass(itemBinding: ItemInfoBinding) :
            RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {
            var textViewRow1: TextView = itemBinding.name

            init {
                itemBinding.root.setOnClickListener(this)
            }
            override fun onClick(v: View?) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = dataList[position]
                    // 로그에 데이터 출력
                    Log.d("로그출력", "Clicked item: ${clickedItem.fruit}")


                    val infoIntent = Intent(this@MainActivity, FruitInfoActivity::class.java)
                    infoIntent.putExtra("fruit", clickedItem.fruit)
                    infoIntent.putExtra("quantity", clickedItem.quantity)
                    infoIntent.putExtra("origin", clickedItem.origin)
                    this@MainActivity.startActivity(infoIntent)
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
            holder.textViewRow1.text = "${position+1}번 ${dataList[position].fruit}"
        }
    }

}


data class DataClass(var fruit: String, var quantity: Int, var origin: String)