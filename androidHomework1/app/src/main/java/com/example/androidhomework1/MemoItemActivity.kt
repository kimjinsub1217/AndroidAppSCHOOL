package com.example.androidhomework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidhomework1.databinding.ActivityMemoItemBinding
import com.example.androidhomework1.databinding.MemoItemBinding

class MemoItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityMemoItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            addButton.setOnClickListener {
                var memoName = memoNameEditText.text.toString()
                var memoDetail = memoDetailEditText.text.toString()

                val resultIntent = Intent()
                resultIntent.putExtra("memoName", memoName)
                resultIntent.putExtra("memoDetail", memoDetail)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
            cancelButton.setOnClickListener {
                finish()
            }
        }
    }
}