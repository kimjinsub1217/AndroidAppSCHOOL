package com.example.android35_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android35_ex02.databinding.ActivityMainBinding
import com.example.android35_ex02.databinding.ItemInfoBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val studentList = mutableListOf<Student>()
    lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CustomAdapter()

        binding.run {

            addButton.setOnClickListener {
                inputLinearLayout.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                korScoreEditText.setOnEditorActionListener { v, actionId, event ->

                    addData()
                    clearEditTexts()
                    nameEditText.requestFocus()
                    false
                }
            }

            viewButton.setOnClickListener {
                inputLinearLayout.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                adapter.notifyDataSetChanged()
            }
        }
    }


    private fun ActivityMainBinding.addData() {
        val name = nameEditText.text.toString()
        val age = ageEditText.text.toString().toInt()
        val kor = korScoreEditText.text.toString().toInt()

        val student = Student(name, age, kor)
        studentList.add(student)
    }

    private fun ActivityMainBinding.clearEditTexts() {
        nameEditText.text.clear()
        ageEditText.text.clear()
        korScoreEditText.text.clear()
    }

    inner class CustomAdapter : RecyclerView.Adapter<CustomAdapter.AdapterViewHolder>() {
        inner class AdapterViewHolder(binding: ItemInfoBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val nameTextView: TextView = binding.nameTextView
            val ageTextView: TextView = binding.ageTextView
            val korTextView: TextView = binding.korScoreTextView
            val deleteButton: Button =binding.Button

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
            val infoBinding =
                ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AdapterViewHolder(infoBinding)
        }

        override fun getItemCount() = studentList.size

        override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
            val item = studentList[position]
            holder.nameTextView.text = "이름 : ${item.name} "  // 이름은 첫 번째 요소
            holder.ageTextView.text = "나이 : ${item.age} "  // 나이는 두 번째 요소
            holder.korTextView.text = "국어 점수 : ${item.korScore} "  // 국어 점수는 세 번째 요소

            holder.deleteButton.setOnClickListener {
                studentList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, studentList.size)
            }
        }
    }
}

data class Student(var name: String, var age: Int, var korScore: Int)