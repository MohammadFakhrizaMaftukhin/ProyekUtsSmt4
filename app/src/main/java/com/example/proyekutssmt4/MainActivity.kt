package com.example.proyekutssmt4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.proyekutssmt4.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.lifecycleOwner = this

        val email = intent.getStringExtra("email") ?: return

        viewModel.observeUser(email).observe(this) { user: User ->
            binding.user = user

            binding.btnEdit.setOnClickListener {
                val intent = Intent(this@MainActivity, UpdateProfileActivity::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
            }

            binding.btnLogout.setOnClickListener {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }
}