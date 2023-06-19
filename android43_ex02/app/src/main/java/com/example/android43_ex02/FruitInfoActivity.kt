package com.example.android43_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android43_ex02.databinding.ActivityFruitInfoBinding

class FruitInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityFruitInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFruitInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fruit = intent.getStringExtra("fruit")
        val quantity = intent.getIntExtra("quantity", -1)
        val origin = intent.getStringExtra("origin")

        binding.run {

            fruitNameTextView.text = fruit.toString()
            quantityTextView.text = quantity.toString()
            originTextView.text = origin.toString()


            button.setOnClickListener {
                finish()
            }
        }
    }
}