package com.baharudin.daiapp.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.daiapp.adapter.AdminListDaiAdapter
import com.baharudin.daiapp.databinding.ActivityAdminDaiListBinding
import com.baharudin.daiapp.model.Dai
import com.baharudin.daiapp.utils.Preference
import com.google.firebase.database.*

class AdminDaiListAct : AppCompatActivity() {

    lateinit var binding : ActivityAdminDaiListBinding

    private lateinit var dataRef : DatabaseReference
    private lateinit var preference: Preference
    private  var dataList =ArrayList<Dai>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDaiListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = Preference(this)
        dataRef = FirebaseDatabase.getInstance().getReference("Dai")

        binding.rvListdai.layoutManager = LinearLayoutManager(this)


        dataRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (getAll in snapshot.children) {
                    val dai = getAll.getValue(Dai::class.java)
                    dataList.add(dai!!)
                }
                binding.rvListdai.adapter = AdminListDaiAdapter(dataList){
                    startActivity(Intent(this@AdminDaiListAct,AdminDaiInfoAct::class.java).putExtra("data_dai",it))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AdminDaiListAct, error.message, Toast.LENGTH_SHORT).show()
            }

        })



    }

}