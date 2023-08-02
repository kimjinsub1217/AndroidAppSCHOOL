package com.example.mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.databinding.ActivityAddBinding
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.databinding.RowBinding
import com.example.mvvm.repository.Test1Repository
import com.example.mvvm.vm.TestData
import com.example.mvvm.vm.ViewModelTest2

class AddActivity : AppCompatActivity() {

    lateinit var activityAddBinding: ActivityAddBinding

    lateinit var viewModelTest2: ViewModelTest2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityAddBinding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(activityAddBinding.root)

        // ViewModel을 받아온다.
        viewModelTest2 = ViewModelProvider(MainActivity.mainActivity)[ViewModelTest2::class.java]

        activityAddBinding.run {
            buttonAdd.setOnClickListener {

                val data1 = editTextAddData1.text.toString()
                val data2 = editTextAddData2.text.toString()

                val t1 = TestData(0, data1, data2)

                // ViewModel 객체의 리스트에 담아준다.
//                viewModelTest2.dataList.value?.add(t1)
//                viewModelTest2.addItem(t1)

                // 데이터베이스에 저장한다.
                Test1Repository.addData(this@AddActivity, t1)

                finish()
            }
        }
    }
}
