package com.test.android33_customadapter_row

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.test.android33_customadapter_row.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val data1 = arrayOf("데이터1","데이터2","데이터3","데이터4","데이터5", )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }

    // AdapterClass
    // BaseAdapter를 상속받고 메서드를 구현해준다.

    inner class CustomAdapter : BaseAdapter() {
        // 리스트뷰의 로우의 개수를 결정하는 메서드
        // 이 메서드가 반환하는 정수 만큼 로우를 생성한다.
        override fun getCount(): Int {
            return data1.size
        }
        // 현재 번째의 로우 View를 반환하도록 만들어준다.
        override fun getItem(position: Int): Any? {
            return null
        }
        // 현재 번째의 로우 View의 ID를 반환하도록 만들어준다.
        override fun getItemId(position: Int): Long {
            return 0
        }
        // 로우로 사용할 View를 생성하여 반환하는 메서드
        // 여기서 반환하는 View를 현재 번째의 Row로 사용한다.
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            return 0
        }
    }
}