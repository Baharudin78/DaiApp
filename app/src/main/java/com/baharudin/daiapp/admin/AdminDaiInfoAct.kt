package com.baharudin.daiapp.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.daiapp.R
import com.baharudin.daiapp.databinding.ActivityAdminDaiInfoBinding
import com.baharudin.daiapp.model.Dai
import com.google.firebase.database.FirebaseDatabase

class AdminDaiInfoAct : AppCompatActivity() {

    lateinit var binding : ActivityAdminDaiInfoBinding
    lateinit var dataDai: Dai

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDaiInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Dai>("data_dai")

        dataDai = Dai()
        binding.tvNama.text = data?.nama
        binding.tvDeskripsi.text = data?.deskripsi
        binding.tvAlamat.text = data?.alamat
        binding.tvNohp.text = data?.telepon

    }

}