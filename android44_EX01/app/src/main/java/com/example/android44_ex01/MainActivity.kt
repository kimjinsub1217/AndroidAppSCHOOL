package com.example.android44_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android44_ex01.DataClass.Companion.fluitList
import com.example.android44_ex01.databinding.ActivityMainBinding
import com.example.android44_ex01.databinding.RowBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            recyclerView.run {
                adapter = RecyclerViewAdapter()
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
            R.id.main_menu_add -> {
                val addInput = Intent(this@MainActivity, InputActivity::class.java)
                startActivity(addInput)
            }
        }

        return super.onOptionsItemSelected(item)
    }


    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {
        inner class ViewHolderClass(rowBinding: RowBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {
            var textViewRow: TextView = rowBinding.textViewRow

            init {
                rowBinding.root.setOnClickListener {
                    val showIntent = Intent(this@MainActivity, ShowActivity::class.java)

                    // 사용자가 터치한 항목의 순서값을 담아준다.
                    showIntent.putExtra("position", adapterPosition)
                    startActivity(showIntent)
                }
            }

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

        override fun getItemCount() = fluitList.size

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.textViewRow.text = fluitList[position].type
        }
    }

    override fun onResume() {
        super.onResume()

        val adapter = binding.recyclerView.adapter as RecyclerViewAdapter
        adapter.notifyDataSetChanged()
    }

}