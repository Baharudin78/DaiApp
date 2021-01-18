package com.baharudin.daiapp.fragment.tab

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.daiapp.R
import com.baharudin.daiapp.adapter.ListDaiAdapter
import com.baharudin.daiapp.databinding.FragmentSearchBinding
import com.baharudin.daiapp.model.Dai
import com.baharudin.daiapp.utils.Preference
import com.google.firebase.database.*

class SearchFragment : Fragment(R.layout.fragment_search){

    private var _binding : FragmentSearchBinding ?= null
    private val binding get() = _binding!!

    private lateinit var dataRef : DatabaseReference
    private lateinit var preference: Preference
    private var dataList = ArrayList<Dai>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSearchBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        preference = Preference(requireActivity().applicationContext)
        dataRef = FirebaseDatabase.getInstance().getReference("Dai")


        binding.rvSearchdai.layoutManager = LinearLayoutManager(requireContext())
        getData()
    }
    private fun getData(){
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (getDataAll in snapshot.children){
                    val dai = getDataAll.getValue(Dai::class.java)
                    dataList.add(dai!!)
                }
                binding.rvSearchdai.adapter = ListDaiAdapter(dataList){

                }
                binding.tvJumlahdai.setText("${dataList.size}")
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}