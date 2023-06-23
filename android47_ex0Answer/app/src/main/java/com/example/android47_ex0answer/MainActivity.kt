package com.example.android47_ex0answer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android47_ex0answer.databinding.ActivityMainBinding
import com.example.android47_ex0answer.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val tempData = arrayOf("고기 1", "고기 2", "고기 3", "고기 4", "고기 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            recyclerViewInfo.run {
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            buttonAdd.setOnClickListener {
                val inputIntent = Intent(this@MainActivity, InputActivity::class.java)
                startActivity(inputIntent)
            }
        }
    }

    inner class RecyclerAdapterClass : RecyclerView.Adapter<RecyclerAdapterClass.ViewHolder>() {
        inner class ViewHolder(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root) {
            var textViewRow: TextView

            init {
                textViewRow = rowBinding.textViewRow
                rowBinding.root.setOnClickListener {
                    val resultIntent = Intent(this@MainActivity, ResultActivity::class.java)
                    resultIntent.putExtra("position",adapterPosition)
                    startActivity(resultIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolder(rowBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            rowBinding.root.layoutParams = params
            return viewHolderClass
        }


        override fun getItemCount(): Int {
            return DataClass.humanList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textViewRow.text = DataClass.humanList[position].name
        }
    }

    override fun onResume() {
        super.onResume()
        binding.recyclerViewInfo.adapter?.notifyDataSetChanged()
    }
}