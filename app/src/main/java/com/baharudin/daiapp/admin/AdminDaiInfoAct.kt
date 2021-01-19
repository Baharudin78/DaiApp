package com.baharudin.daiapp.admin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.daiapp.HomeActivity
import com.baharudin.daiapp.databinding.ActivityAdminDaiInfoBinding
import com.baharudin.daiapp.model.Dai
import com.google.firebase.database.*

class AdminDaiInfoAct : AppCompatActivity() {

    lateinit var binding : ActivityAdminDaiInfoBinding
    lateinit var dataDai: Dai
    lateinit var iNama: String
    lateinit var dataRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDaiInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Dai>("data_dai")
        dataRef = FirebaseDatabase.getInstance().getReference("Dai").child(data!!.nama)


        dataDai = Dai()
        iNama = binding.tvNama.text.toString()
        binding.tvNama.text = data?.nama
        binding.tvDeskripsi.text = data?.deskripsi
        binding.tvAlamat.text = data?.alamat
        binding.tvNohp.text = data?.telepon

        binding.btHapus.setOnClickListener {

            deleteData(iNama)
        }

    }
    fun deleteData(iNama : String){
        dataRef.orderByChild("nama").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    dataSnapshot.ref.removeValue()
                }
                val intent = (Intent(this@AdminDaiInfoAct,AdminDaiListAct::class.java))
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                Toast.makeText(this@AdminDaiInfoAct, "berhasil", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }



}