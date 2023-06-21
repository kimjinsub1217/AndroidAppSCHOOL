package com.example.androidhomework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidhomework1.databinding.ActivityModifyMemoBinding

class ModifyMemoActivity : AppCompatActivity() {
    lateinit var binding: ActivityModifyMemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {

            addButton.setOnClickListener {

                val modifyDetailText = modifyDetailEditText.text.toString()
                val intent = Intent()

                intent.putExtra("modifyDetail", modifyDetailText)
                setResult(RESULT_OK, intent)
                finish()
            }

            cancelButton.setOnClickListener {
                finish()
            }
        }
    }
}