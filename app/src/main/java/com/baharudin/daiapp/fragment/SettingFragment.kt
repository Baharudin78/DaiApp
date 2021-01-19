package com.baharudin.daiapp.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.baharudin.daiapp.LoginAct
import com.baharudin.daiapp.R
import com.baharudin.daiapp.databinding.FragmentSettingBinding
import com.baharudin.daiapp.utils.Preference

class SettingFragment : Fragment(R.layout.fragment_setting)  {

    private lateinit var preference: Preference
    private var _binding : FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingBinding.bind(view)

        preference = Preference(requireActivity().applicationContext)

        binding.tvNamauser.setText(preference.getValue("username"))
        binding.tvEmailuser.setText(preference.getValue("email"))
        binding.tvNouser.setText(preference.getValue("nohp"))

        binding.btKeluar.setOnClickListener {
           preference.clear()
            Toast.makeText(requireContext(), "keluar", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireActivity(),LoginAct::class.java))
            activity?.finish()
        }
    }

}