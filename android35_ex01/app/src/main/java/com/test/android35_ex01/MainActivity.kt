package com.test.android35_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android35_ex01.databinding.ActivityMainBinding
import com.test.android35_ex01.databinding.ItemInfoBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val dataList = mutableListOf<List<String>>()
    lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CustomAdapter()

        binding.run {

            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            korEditText.setOnEditorActionListener { v, actionId, event ->

                addDataToList()
                clearEditTexts()

                adapter.notifyDataSetChanged()
                nameEditText.requestFocus()
                false
            }
        }
    }

    fun addDataToList() {
        binding.run {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString()
            val kor = korEditText.text.toString()

            val item = listOf(name, age, kor)
            dataList.add(item)
        }

    }

    fun clearEditTexts() {
        binding.run {
            nameEditText.text.clear()
            ageEditText.text.clear()
            korEditText.text.clear()
        }
    }

    inner class CustomAdapter : RecyclerView.Adapter<CustomAdapter.AdapterViewHolder>() {
        inner class AdapterViewHolder(binding: ItemInfoBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val nameTextView: TextView = binding.nameTextView
            val ageTextView: TextView = binding.ageTextView
            val korTextView: TextView = binding.korTextView
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
            val rowBinding =
                ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AdapterViewHolder(rowBinding)
        }

        override fun getItemCount() = dataList.size

        override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
            val item = dataList[position]  // 데이터 항목 가져오기
            holder.nameTextView.text = "이름 : ${item[0]}"  // 이름은 첫 번째 요소
            holder.ageTextView.text = "나이 : ${item[1]}"  // 나이는 두 번째 요소
            holder.korTextView.text = "국어 점수 : ${item[2]}\n"  // 국어 점수는 세 번째 요소
        }
    }
}