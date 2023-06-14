package com.test.android33_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.test.android33_ex01.databinding.ActivityMainBinding
import com.test.android33_ex01.databinding.EditTextListBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var dataList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            listView.run {
                adapter = CustomAdapter()
            }

            editText.run {
                setOnEditorActionListener { v, actionId, event ->
                    // 사용자가 입력한 문자열을 리스트에 담는다.
                    dataList.add(text.toString())
                    setText("")
                    // 리스트뷰 갱신
                    val adapter = listView.adapter as CustomAdapter
                    adapter.notifyDataSetChanged()

                    false
                }
            }

        }
    }

    inner class CustomAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return dataList.size
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var editTextList: EditTextListBinding
            var mainView = convertView

            if (convertView == null) {
                editTextList = EditTextListBinding.inflate(layoutInflater)
                mainView = editTextList.root
                mainView.tag = editTextList
            } else {
                editTextList = mainView!!.tag as EditTextListBinding
            }

            editTextList.editTextView.text = dataList[position]
            editTextList.run {

                editTextView.run {
                    editTextList.editTextView.text = dataList[position]
                }

                buttonRow.run {
                    setOnClickListener {
                        // position 번째 문자열을 삭제한다.
                        dataList.removeAt(position)
                        // 리스트뷰 갱신
                        val adapter = binding.listView.adapter as CustomAdapter
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            return mainView
        }
    }
}