package com.test.android35_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.android35_ex01.databinding.ActivityMainBinding
import com.test.android35_ex01.databinding.ItemInfoBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var dataList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {


            korEditText.run {
                setOnEditorActionListener { v, actionId, event ->
                    // 사용자가 입력한 문자열을 리스트에 담는다.
                    dataList.add(nameEditText.text.toString())
                    dataList.add(ageEditText.text.toString())
                    dataList.add(korEditText.text.toString())
                    setText("")

                    // 리스트뷰 갱신
                    val adapter = recyclerView.adapter as CustomAdapter
                    adapter.notifyDataSetChanged()

                    false
                }
            }

        }

    }

    inner class CustomAdapter : RecyclerView.Adapter<CustomAdapter.AdapterViewHolder>() {
        inner class AdapterViewHolder(itemInfoBinding: ItemInfoBinding) : RecyclerView.ViewHolder(binding.root) {
            var nameTextView: TextView = binding.nameEditText
            var ageTextView: TextView=binding.ageEditText
            var korTextView:TextView =binding.korEditText


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
            // ViewBinding
            val rowBinding = ItemInfoBinding.inflate(layoutInflater)

            //ViewHolder
            val viewHolderClass = AdapterViewHolder(rowBinding)


            return viewHolderClass
        }

        override fun getItemCount() = dataList.size

        override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
            holder.ageTextView
            holder.imageViewRow.setImageResource(imgRes[position])
        }
    }

}