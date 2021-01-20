package com.baharudin.daiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.baharudin.daiapp.databinding.ActivityDaiInfoBinding
import com.baharudin.daiapp.model.Dai
import com.bumptech.glide.Glide

class DaiInfoAct : AppCompatActivity() {
    lateinit var binding : ActivityDaiInfoBinding
    lateinit var dataDai: Dai
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaiInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Dai>("data_dai")

        dataDai = Dai()
        binding.tvNama.text = data?.nama
        binding.tvDeskripsi.text = data?.deskripsi
        binding.tvAlamat.text = data?.alamat
        binding.tvNohp.text = data?.telepon

        Glide.with(this)
                .load(binding.ivLogo)
                .into(binding.ivLogo)

        binding.btChat.setOnClickListener {
            Toast.makeText(this, "FItur ini belum ditanamkan", Toast.LENGTH_SHORT).show()
        }
        binding.btUndang.setOnClickListener {
            Toast.makeText(this, "Fitur ini belum tersedia", Toast.LENGTH_SHORT).show()
        }

        binding.ivBack.setOnClickListener {
            val intent = Intent(this,DaiListAct::class.java)
            startActivity(intent)
            finish()
        }
    }
}