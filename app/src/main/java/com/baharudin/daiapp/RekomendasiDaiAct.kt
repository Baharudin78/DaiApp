package com.baharudin.daiapp

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.baharudin.daiapp.admin.AdminDaiListAct
import com.baharudin.daiapp.databinding.ActivityRecomendasiDaiBinding
import com.baharudin.daiapp.model.Dai
import com.baharudin.daiapp.utils.Preference
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class RekomendasiDaiAct : AppCompatActivity() {

    private lateinit var binding : ActivityRecomendasiDaiBinding

    private var statusAdd = false

    private lateinit var fileUri : Uri
    private lateinit var iNama : String
    private lateinit var iAlamat : String
    private lateinit var iDeskripsi : String
    private lateinit var iTelepon : String
    lateinit var daiFoto : Dai

    private lateinit var  dataRef : DatabaseReference
    private lateinit var firebaseInstance : FirebaseDatabase
    private lateinit var preference : Preference
    private lateinit var databaseRef : DatabaseReference

    private lateinit var storageRef : StorageReference
    private lateinit var firebaseStorage : FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecomendasiDaiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = Preference(this)

        firebaseStorage = FirebaseStorage.getInstance()
        storageRef = firebaseStorage.reference
        firebaseInstance = FirebaseDatabase.getInstance()
        dataRef = FirebaseDatabase.getInstance().getReference("Rekomendasi Dai")
        databaseRef = firebaseInstance.getReference("Rekomendasi Dai")

        binding.btAddFoto.setOnClickListener {
            if (statusAdd){
                statusAdd = false

                binding.btAddFoto.setImageResource(R.drawable.ic_add_circle)
                binding.ivFoto.setImageResource(R.drawable.ic_foto)
            }else{
                ImagePicker.with(this)
                    .galleryOnly()
                    .start()
            }
        }

        binding.btRekomendasi.setOnClickListener {
            iNama = binding.etNamaDai.text.toString()
            iAlamat = binding.etAlamat.text.toString()
            iDeskripsi = binding.etDeskripsiDai.text.toString()
            iTelepon = binding.etTelpdai.text.toString()

            if (iNama == ""){
                binding.etNamaDai.error = "Isi nama dai"
                binding.etNamaDai.requestFocus()
            }else if (iAlamat == ""){
                binding.etAlamat.error = "Isi alamat dai"
                binding.etAlamat.requestFocus()
            }else if (iDeskripsi == ""){
                binding.etDeskripsiDai.error = " Isi deskripsi dai"
                binding.etDeskripsiDai.requestFocus()
            }else if (iTelepon == ""){
                binding.etTelpdai.error = "Isi telepon nya"
                binding.etTelpdai.requestFocus()
            }else{
                rekomenDai(iNama,iAlamat,iDeskripsi,iTelepon)
            }

            if (fileUri != null){
                val progressBar = ProgressDialog(this)
                progressBar.setTitle("Uploading...")
                progressBar.show()

                val ref = storageRef.child("images/" + UUID.randomUUID().toString())
                ref.putFile(fileUri)
                    .addOnSuccessListener {
                        progressBar.dismiss()
                        Toast.makeText(this, "berhasil upload foto", Toast.LENGTH_SHORT).show()

                        ref.downloadUrl.addOnSuccessListener {
                            preference.setValue("foto",it.toString())
                            saveFirebase(it.toString())
                        }
                    }
                    .addOnFailureListener{
                        progressBar.dismiss()
                        Toast.makeText(this, "Gagal upload foto", Toast.LENGTH_SHORT).show()
                    }
                    .addOnProgressListener {
                        taskSnapshot -> val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progressBar.setMessage("Upload " +  progress.toInt() + "%")
                    }
            }
        }


    }

    private fun saveFirebase(url: String) {
        databaseRef.child(iNama).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                daiFoto.foto = url

                databaseRef.child(daiFoto.nama).setValue(daiFoto)
                preference.setValue("foto","")
                preference.setValue("foto", url)

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RekomendasiDaiAct, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            fileUri = data?.data!!
            statusAdd = true

            Glide.with(this)
                .load(fileUri)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivFoto)

            Log.i("tampan","foto berhasil")
            binding.btAddFoto.setImageResource(R.drawable.ic_delete)
        }else if (requestCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Operasi digagalkan", Toast.LENGTH_SHORT).show()
        }
    }


    private fun rekomenDai(iNama: String, iAlamat: String, iDeskripsi: String, iTelepon: String) {
        val dai = Dai()
        dai.nama = iNama
        dai.alamat = iAlamat
        dai.deskripsi = iDeskripsi
        dai.telepon = iTelepon

        if (iNama != null){
            checkUser(iNama,dai)
        }

    }

    private fun checkUser(iNama: String, data: Dai) {
        databaseRef.child(iNama).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dai = snapshot.getValue(Dai::class.java)

                if (dai == null) {
                    databaseRef.child(iNama).setValue(data)
                    preference.setValue("nama", data.nama)
                    preference.setValue("alamat", data.alamat)
                    preference.setValue("deskripsi", data.deskripsi)
                    preference.setValue("telepon", data.telepon)

                    Toast.makeText(
                        this@RekomendasiDaiAct,
                        "Berhasil merekomndasikan dai",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RekomendasiDaiAct, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}