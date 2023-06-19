package com.example.androidhomework1

import android.R.attr.button
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomework1.databinding.ActivityModifiyBinding


class ModifiyActivity : AppCompatActivity() {
    lateinit var binding: ActivityModifiyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifiyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.run {

            addButton.setOnClickListener {
                val modifyText = modifyEditText.text.toString()
                Log.i("ㅋㅋㅋ",modifyText)
                val intent = Intent()
                intent.putExtra("modify", modifyText)
                setResult(RESULT_OK, intent)
                finish()
            }


            cancelButton.setOnClickListener {
                finish()
            }
        }
    }
}