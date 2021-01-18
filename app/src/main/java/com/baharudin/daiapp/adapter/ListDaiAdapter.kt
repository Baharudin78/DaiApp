package com.baharudin.daiapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.daiapp.databinding.ItemListDaiBinding
import com.baharudin.daiapp.model.Dai

class ListDaiAdapter (private var list: List<Dai>,private var listener :(Dai)-> Unit) : RecyclerView.Adapter<ListDaiAdapter.ListDaiHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDaiHolder {
        val inflated = ItemListDaiBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        contextAdapter = parent.context
        return ListDaiHolder(inflated)
    }

    override fun onBindViewHolder(holder: ListDaiHolder, position: Int) {
        holder.bindItem(list[position],listener)
    }

    override fun getItemCount(): Int = list.size

    inner class ListDaiHolder(binding : ItemListDaiBinding): RecyclerView.ViewHolder(binding.root){

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
}