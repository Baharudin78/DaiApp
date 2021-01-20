package com.baharudin.daiapp.fragment.tab

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.daiapp.DaiInfoAct
import com.baharudin.daiapp.R
import com.baharudin.daiapp.adapter.ListDaiAdapter
import com.baharudin.daiapp.databinding.FragmentListBinding
import com.baharudin.daiapp.model.Dai
import com.baharudin.daiapp.utils.Preference
import com.google.firebase.database.*

class ListFragment : Fragment(R.layout.fragment_list) {
    private var _binding : FragmentListBinding?= null
    private val binding get() = _binding!!

    private lateinit var dataRef : DatabaseReference
    private lateinit var preference: Preference
    private var dataList = ArrayList<Dai>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentListBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        preference = Preference(requireActivity().applicationContext)
        dataRef = FirebaseDatabase.getInstance().getReference("Dai")

        binding.rvListDai.layoutManager = LinearLayoutManager(requireContext())
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
                binding.rvListDai.adapter = ListDaiAdapter(dataList){
                    startActivity(Intent(requireContext(), DaiInfoAct::class.java).putExtra("data_dai",it))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }
}