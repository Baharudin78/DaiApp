package com.baharudin.daiapp.admin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.daiapp.databinding.ActivityAdminUpdateDaiBinding
import com.baharudin.daiapp.model.Dai
import com.google.firebase.database.*

class AdminUpdateDaiAct : AppCompatActivity() {
    private lateinit var iNama : String
    private lateinit var iAlamat : String
    private lateinit var iDeskripsi : String
    private lateinit var iHP : String
    lateinit var dataRef : DatabaseReference

    lateinit var binding : ActivityAdminUpdateDaiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminUpdateDaiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iNama = binding.etEditNama.text.toString()
        iAlamat = binding.etEditAlamat.text.toString()
        iDeskripsi = binding.etEditDeskripsiDai.text.toString()
        iHP = binding.etEditTelpdai.text.toString()

        dataRef = FirebaseDatabase.getInstance().getReference("Dai")


        iNama = intent.getStringExtra("namadai")!!
        iAlamat = intent.getStringExtra("alamatdai")!!
        iDeskripsi = intent.getStringExtra("deskripsidai")!!
        iHP = intent.getStringExtra("telepondai")!!

        binding.etEditNama.setText(iNama)
        binding.etEditAlamat.setText(iAlamat)
        binding.etEditDeskripsiDai.setText(iDeskripsi)
        binding.etEditTelpdai.setText(iHP)

        binding.btUpdate.setOnClickListener {
            if (iNama.isEmpty()){
                binding.etEditNama.error = "Mohon isi nama"
                binding.etEditNama.requestFocus()
                return@setOnClickListener
            }else if (iAlamat.isEmpty()){
                binding.etEditAlamat.error = " Mohon isi form ini"
                binding.etEditAlamat.requestFocus()
                return@setOnClickListener
            }
            else if (iDeskripsi.isEmpty()){
                binding.etEditDeskripsiDai.error = "Isi deskripsi dai"
                binding.etEditDeskripsiDai.requestFocus()
                return@setOnClickListener
            }else if (iHP.isEmpty()){
                binding.etEditTelpdai.error = "Isi nomer hp"
                binding.etEditTelpdai.requestFocus()
                return@setOnClickListener
            }else{

                saveData(iNama,iAlamat, iDeskripsi, iHP)
            }
        }
    }
    private fun saveData(iNama : String,iAlamat : String,iDeskripsi : String,iHP : String){
        val dai = Dai()
        dai.nama = iNama
        dai.alamat = iAlamat
        dai.deskripsi = iDeskripsi
        dai.telepon = iHP
        checkUser(iNama,dai)
    }

    private fun checkUser(iNama: String, dai: Dai) {
        dataRef.child(iNama).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataRef.child(iNama).setValue(dai)
                Toast.makeText(this@AdminUpdateDaiAct, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AdminUpdateDaiAct, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}