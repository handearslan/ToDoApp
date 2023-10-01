package com.handearslan.todoapphomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.handearslan.todoapphomework.common.viewBinding
import com.handearslan.todoapphomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}

