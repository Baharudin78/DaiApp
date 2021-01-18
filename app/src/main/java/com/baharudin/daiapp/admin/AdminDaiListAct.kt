package com.baharudin.daiapp.admin

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.daiapp.adapter.AdminListDaiAdapter
import com.baharudin.daiapp.databinding.ActivityAdminDaiListBinding
import com.baharudin.daiapp.databinding.ItemAdminListDaiBinding
import com.baharudin.daiapp.databinding.ItemListDaiBinding
import com.baharudin.daiapp.model.Dai
import com.baharudin.daiapp.utils.Preference
import com.google.firebase.database.*

class AdminDaiListAct : AppCompatActivity() {

    private lateinit var dataRef : DatabaseReference
    private lateinit var preference: Preference
    private lateinit var dataList : MutableList<Dai>
    private lateinit var listDai : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.)

        preference = Preference(this)
        dataList = mutableListOf()

        listDai = binding.listView
        dataRef = FirebaseDatabase.getInstance().getReference("Dai")



        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (getAll in snapshot.children) {
                    val dai = getAll.getValue(Dai::class.java)
                    dataList.add(dai!!)
                }

                val adapterList = AdminListDaiAdapter(applicationContext,,dataList)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AdminDaiListAct, error.message, Toast.LENGTH_SHORT).show()
            }

        })



    }
}