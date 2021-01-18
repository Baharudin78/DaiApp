package com.baharudin.daiapp.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.daiapp.databinding.ActivityAdminBinding

class AdminAct : AppCompatActivity() {
    lateinit var binding : ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivMan.setOnClickListener {
            startActivity(Intent(this,AdminDaiListAct::class.java))
        }

        binding.ivList.setOnClickListener {
            startActivity(Intent(this,AdminRecoAct::class.java))
        }
        binding.ivTambah.setOnClickListener {
            startActivity(Intent(this,AdminAddNewAct::class.java))
        }
    }
}