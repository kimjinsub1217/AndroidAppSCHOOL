package com.example.android35_ex03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android35_ex03.databinding.ActivityMainBinding
import com.example.android35_ex03.databinding.ItemInfoBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val playerInfoList = mutableListOf<FootballPlayer>()
    lateinit var adapter: CustomAdapter
    val dataList = arrayOf(
        "토고", "프랑스", "스위스", "스폐인", "일본", "독일", "브라질", "대한민국"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CustomAdapter()

        binding.run {
            addButton.setOnClickListener {
                spinner.run {
                    recyclerView.visibility = View.GONE
                    inputLinearLayout.visibility = View.VISIBLE

                    val a1 = ArrayAdapter<String>(
                        this@MainActivity,
                        android.R.layout.simple_spinner_item,
                        dataList
                    )

                    // Spinner가 펼쳐져 있을 때의 항목 모양
                    a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    adapter = a1

                    // spinner의 항목을 코드로 선택한다.
                    // 0부터 시작하는 순서값을 넣어준다.
                    setSelection(0)

                    // 항목을 선택하면 동작하는 리스너
                    // 3번째 : 선택한 항목의 순서값 (0부터)
                    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {

                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }

                    }
                }
            }

            viewButton.setOnClickListener {

                inputLinearLayout.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                var position = spinner.selectedItemPosition
                var nationality = dataList[position] // 국적
                var name = nameEditText.text.toString()// 이름

                val selectedId = radioGroup.checkedRadioButtonId
                val selectedRadioButton = binding.root.findViewById<RadioButton>(selectedId)
                val selectedText = selectedRadioButton.text.toString()//포지션


                var playerInfo = FootballPlayer(nationality, name, selectedText)
                playerInfoList.add(playerInfo)

                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                adapter.notifyDataSetChanged()

                nameEditText.text.clear()
                strikerButton.isChecked = true

            }


        }


    }

    inner class CustomAdapter : RecyclerView.Adapter<CustomAdapter.AdapterViewHolder>() {
        inner class AdapterViewHolder(binding: ItemInfoBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val nationImage: ImageView = binding.imageView // 국가
            val nameTextView: TextView = binding.nameTextView // 이름
            val positionTextView: TextView = binding.positionTextView // 포지션

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
            val infoBinding =
                ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AdapterViewHolder(infoBinding)
        }

        override fun getItemCount() = playerInfoList.size

        override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
            val item = playerInfoList[position]
            when (item.nation) {
                "토고" -> {
                    holder.nationImage.setImageResource(R.drawable.imgflag1)
                }

                "프랑스" -> {
                    holder.nationImage.setImageResource(R.drawable.imgflag2)
                }

                "스위스" -> {
                    holder.nationImage.setImageResource(R.drawable.imgflag3)
                }

                "스폐인" -> {
                    holder.nationImage.setImageResource(R.drawable.imgflag4)
                }

                "일본" -> {
                    holder.nationImage.setImageResource(R.drawable.imgflag5)
                }

                "독일" -> {
                    holder.nationImage.setImageResource(R.drawable.imgflag6)
                }

                "브라질" -> {
                    holder.nationImage.setImageResource(R.drawable.imgflag7)
                }

                "대한민국" -> {
                    holder.nationImage.setImageResource(R.drawable.imgflag8)
                }
            }
            holder.nameTextView.text = "${item.name} "
            holder.positionTextView.text = "${item.soccerPosition} "


        }

    }
}


data class FootballPlayer(val nation: String, val name: String, val soccerPosition: String)