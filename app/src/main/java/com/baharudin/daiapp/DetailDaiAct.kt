package com.baharudin.daiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baharudin.daiapp.databinding.ActivityDetailDaiBinding
import com.baharudin.daiapp.model.Dai

class DetailDaiAct : AppCompatActivity() {
    lateinit var binding : ActivityDetailDaiBinding
    lateinit var dataDai: Dai
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailDaiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Dai>("data_dai")

        dataDai = Dai()
        binding.tvNama.text = data?.nama
        binding.tvDeskripsi.text = data?.deskripsi
        binding.tvAlamat.text = data?.alamat
        binding.tvNohp.text = data?.telepon

    }
}