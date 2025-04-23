package com.example.proyekutssmt4

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.proyekutssmt4.databinding.ActivityUpdateProfileBinding

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateProfileBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_profile)
        binding.lifecycleOwner = this
        binding.userViewModel = viewModel

        val email = intent.getStringExtra("email") ?: return

        viewModel.observeUser(email).observe(this) { user ->
            binding.edtName.setText(user.name)
            binding.edtPhone.setText(user.phone)
            binding.edtAddress.setText(user.address)

            binding.btnUpdate.setOnClickListener {
                val updatedUser = user.copy(
                    name = binding.edtName.text.toString(),
                    phone = binding.edtPhone.text.toString(),
                    address = binding.edtAddress.text.toString()
                )
                viewModel.update(updatedUser)
                Toast.makeText(this@UpdateProfileActivity, "Profil diperbarui", Toast.LENGTH_SHORT).show()
                finish()
            }

            binding.btnKembali.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
                finish()
            }
        }
    }
}