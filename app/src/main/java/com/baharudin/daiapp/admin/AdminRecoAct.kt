package com.baharudin.daiapp.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.daiapp.adapter.ListDaiAdapter
import com.baharudin.daiapp.databinding.ActivityAdminRecoBinding
import com.baharudin.daiapp.model.Dai
import com.google.firebase.database.*

class AdminRecoAct : AppCompatActivity() {

    private lateinit var dataRef : DatabaseReference
    private lateinit var binding : ActivityAdminRecoBinding

    private var dataList = ArrayList<Dai>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminRecoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataRef = FirebaseDatabase.getInstance().getReference("Rekomendasi Dai")

        binding.rvRekoList.layoutManager = LinearLayoutManager(this)


        getRecomend()
    }
    private fun getRecomend(){
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (getReco in snapshot.children) {
                    val reco = getReco.getValue(Dai::class.java)
                    dataList.add(reco!!)
                }
                binding.rvRekoList.adapter = ListDaiAdapter(dataList){
                    startActivity(Intent(this@AdminRecoAct,AdminRecoInfoAct::class.java).putExtra("data_dai",it))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AdminRecoAct, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,AdminAct::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}