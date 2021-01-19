package com.baharudin.daiapp.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.baharudin.daiapp.HomeActivity
import com.baharudin.daiapp.R
import com.baharudin.daiapp.databinding.ActivityAdminRecoInfoBinding
import com.baharudin.daiapp.model.Dai
import com.google.firebase.database.*

class AdminRecoInfoAct : AppCompatActivity() {
    lateinit var binding : ActivityAdminRecoInfoBinding
    lateinit var dataDai: Dai
    lateinit var iNama: String
    lateinit var dataRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminRecoInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Dai>("data_dai")
        dataRef = FirebaseDatabase.getInstance().getReference("Rekomendasi Dai").child(data!!.nama)


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
                val intent = (Intent(this@AdminRecoInfoAct,AdminRecoAct::class.java))
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                Toast.makeText(this@AdminRecoInfoAct, "berhasil", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }



}