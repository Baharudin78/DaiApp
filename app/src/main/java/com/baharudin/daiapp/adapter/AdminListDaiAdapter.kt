package com.baharudin.daiapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.daiapp.databinding.AdminItemListDaiBinding
import com.baharudin.daiapp.model.Dai

class AdminListDaiAdapter(private var list: List<Dai>, private var listener :(Dai)-> Unit) :RecyclerView.Adapter<AdminListDaiAdapter.AdminListDaiHolder>() {
    lateinit var contextAdapter : Context
    inner class AdminListDaiHolder(binding : AdminItemListDaiBinding) : RecyclerView.ViewHolder(binding.root){

        private var tvNamaDai : TextView = binding.tvNamadai
        private var tvKeahlian : TextView = binding.tvKeahlian
        private var tvAlamat : TextView = binding.tvAlamatdai

        fun bindItem(list : Dai,listener: (Dai) -> Unit){
            tvNamaDai.text = list.nama
            tvKeahlian.text = list.deskripsi
            tvAlamat.text = list.alamat

            itemView.setOnClickListener {
                listener(list)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminListDaiHolder {
        val inflated = AdminItemListDaiBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        contextAdapter = parent.context
        return AdminListDaiHolder(inflated)
    }

    override fun onBindViewHolder(holder: AdminListDaiHolder, position: Int) {
        holder.bindItem(list[position],listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}