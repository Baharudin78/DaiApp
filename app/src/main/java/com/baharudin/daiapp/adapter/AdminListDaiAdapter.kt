package com.baharudin.daiapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.baharudin.daiapp.R
import com.baharudin.daiapp.model.Dai

class AdminListDaiAdapter(val mCx : Context,val layoutResId : Int,val daiList : List<Dai>) : ArrayAdapter<Dai>(mCx,layoutResId,daiList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getView(position, convertView, parent)

        val layoutInflater  = LayoutInflater.from(mCx)
        val view = layoutInflater.inflate(layoutResId,null)

        val tvNama : TextView = view.findViewById(R.id.tv_namadai)
        val tvAlamat : TextView = view.findViewById(R.id.tv_alamatdai)
        val tvDeskripsi : TextView = view.findViewById(R.id.tv_keahlian)
        val tvHP : TextView = view.findViewById(R.id.tv_nohp)

        val dai = daiList[position]

        tvNama.text = dai.nama
        tvAlamat.text = dai.alamat
        tvDeskripsi.text = dai.deskripsi
        tvHP.text = dai.telepon

        return view


    }
}