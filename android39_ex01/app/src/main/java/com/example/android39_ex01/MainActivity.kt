package com.example.android39_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android39_ex01.databinding.ActivityMainBinding
import com.example.android39_ex01.databinding.ItemInfoBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val studentList = mutableListOf<Student>()
    lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // 옵션 메뉴를 구성하기 위해 사용하는 메서드
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // xml 파일로 부터 메뉴를 성성한다.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.inputItem -> {
                binding.run {
                    recyclerView.visibility = View.GONE
                    inputLinearLayout.visibility = View.VISIBLE
                    korScoreEditText.setOnEditorActionListener { v, actionId, event ->
                        add()
                        clear()
                        nameEditText.requestFocus()
                        false
                    }
                }

            }

            R.id.resultItem -> {
                binding.run {
                    inputLinearLayout.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE

                    adapter = CustomAdapter()

                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    registerForContextMenu(recyclerView)
                    adapter.notifyDataSetChanged()
                }


            }
        }

        return super.onOptionsItemSelected(item)
    }


    private fun ActivityMainBinding.clear() {
        nameEditText.text.clear()
        ageEditText.text.clear()
        korScoreEditText.text.clear()
    }

    private fun ActivityMainBinding.add() {
        val name = nameEditText.text.toString()
        val age = ageEditText.text.toString().toInt()
        val kor = korScoreEditText.text.toString().toInt()

        val student = Student(name, age, kor)
        studentList.add(student)
    }

    inner class CustomAdapter : RecyclerView.Adapter<CustomAdapter.AdapterViewHolder>() {
        inner class AdapterViewHolder(binding: ItemInfoBinding) :
            RecyclerView.ViewHolder(binding.root), View.OnCreateContextMenuListener {
            val nameTextView: TextView = binding.nameTextView
            val ageTextView: TextView = binding.ageTextView
            val korTextView: TextView = binding.korScoreTextView

            init {
                itemView.setOnCreateContextMenuListener(this)
            }

            override fun onCreateContextMenu(
                menu: ContextMenu?,
                v: View?,
                menuInfo: ContextMenu.ContextMenuInfo?
            ) {
                menu?.setHeaderTitle(studentList[adapterPosition].name)
                menuInflater.inflate(R.menu.context_menu, menu)

                menu?.findItem(R.id.itemDelete)?.setOnMenuItemClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        studentList.removeAt(position)
                        notifyItemRemoved(position)
                    }
                    true
                }

            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
            val infoBinding =
                ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AdapterViewHolder(infoBinding)
        }

        override fun getItemCount() = studentList.size

        override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {

            val item = studentList[position]
            holder.nameTextView.text = "이름 : ${item.name}"
            holder.ageTextView.text = "나이 : ${item.age}"
            holder.korTextView.text = "국어점수 : ${item.korScore}"
        }
    }
}

data class Student(val name: String, val age: Int, val korScore: Int)