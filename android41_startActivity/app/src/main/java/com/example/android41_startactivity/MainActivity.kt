package com.example.android41_startactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android41_startactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            buttonMain.run {
                setOnClickListener {
                    val secondIntent = Intent(this@MainActivity, SecondActivity::class.java)
                    startActivity(secondIntent)
                }
            }
        }
    }
}