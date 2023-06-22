package com.example.android47_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android47_ex01.MyDataList.dataList
import com.example.android47_ex01.databinding.ActivityMainBinding
import com.example.android47_ex01.databinding.ItemInfoBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val c1 = ActivityResultContracts.StartActivityForResult()
        addActivityResultLauncher = registerForActivityResult(c1) {
            if (it.resultCode == RESULT_OK) {

                val name = it.data?.getStringExtra("name")
                val date = it.data?.getStringExtra("date")
                val gender = it.data?.getStringExtra("gender")
                val hobby = it.data?.getStringExtra("hobby")


                val t1 = DataClass(name!!, date!!, gender!!, hobby!!)
                Log.d("데이터들어옴", "$t1")
                dataList.add(t1)

                val adapter = binding.recyclerView.adapter as RecyclerAdapterClass
                adapter.notifyDataSetChanged()
            }
        }

        binding.run {
            addButton.setOnClickListener {
                val intent = Intent(this@MainActivity, InfoActivity::class.java)
                addActivityResultLauncher.launch(intent)
            }

            recyclerView.run {
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }

    }

    inner class RecyclerAdapterClass :
        RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>() {
        inner class ViewHolderClass(itemBinding: ItemInfoBinding) :
            RecyclerView.ViewHolder(itemBinding.root) {
            var name: TextView = itemBinding.name

            init {
                itemBinding.root.setOnClickListener {
                    val showIntent = Intent(this@MainActivity, PersonInfoActivity::class.java)

                    showIntent.putExtra("position", adapterPosition)
                    startActivity(showIntent)
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
            holder.name.text = "${dataList[position].name}"
            Log.d("날짜", "${dataList[position].date}")
            Log.d("성별", "${dataList[position].gender}")
            Log.d("취미", "${dataList[position].hobby}")
        }
    }

}



