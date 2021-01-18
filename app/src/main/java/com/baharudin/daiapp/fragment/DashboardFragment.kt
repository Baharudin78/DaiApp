package com.baharudin.daiapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.baharudin.daiapp.RekomendasiDaiAct
import com.baharudin.daiapp.DaiListAct
import com.baharudin.daiapp.R
import com.baharudin.daiapp.databinding.FragmentDashboardBinding
import com.baharudin.daiapp.utils.Preference

class DashboardFragment: Fragment(R.layout.fragment_dashboard) {

    private var _binding : FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var preference: Preference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDashboardBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        preference = Preference(requireContext().applicationContext)

        binding.tvNama.text = preference.getValue("username")

        binding.ivMan.setOnClickListener {
            startActivity(Intent(requireContext(),DaiListAct::class.java))
        }
        binding.ivAdd.setOnClickListener {
            startActivity(Intent(requireContext(),RekomendasiDaiAct::class.java))
        }
    }
}