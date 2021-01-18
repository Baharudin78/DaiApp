package com.baharudin.daiapp.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.daiapp.databinding.ActivityLoginAdminBinding
import com.baharudin.daiapp.model.Admin
import com.baharudin.daiapp.utils.Preference
import com.google.firebase.database.*

class LoginAdminAct : AppCompatActivity() {

    private lateinit var iUsername : String
    private lateinit var iPassword : String
    private lateinit var dataRef : DatabaseReference
    private lateinit var preference: Preference
    lateinit var binding : ActivityLoginAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = Preference(this)
        dataRef = FirebaseDatabase.getInstance().getReference("Admin")

        binding.btMasuk.setOnClickListener {
            iUsername = binding.etUsername.text.toString()
            iPassword = binding.etPassword.text.toString()

            if (iUsername == ""){
                binding.etUsername.error = "Isi Username anda"
                binding.etUsername.requestFocus()
            }else if (iPassword == ""){
                binding.etPassword.error ="Isi password anda"
                binding.etPassword.requestFocus()
            }else{
                getAdmin(iUsername,iPassword)
            }
        }
    }

    private fun getAdmin(iUsername: String, iPassword: String) {
            dataRef.child(iUsername).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val admin = snapshot.getValue(Admin::class.java)
                    if (admin == null) {
                        Toast.makeText(
                            this@LoginAdminAct,
                            "Admin tidak ditemukan",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        if (admin.password.equals(iPassword)) {
                            preference.setValue("adminnama", admin.username)
                            preference.setValue("sandiadmin", admin.password)
                            finishAffinity()
                            startActivity(Intent(this@LoginAdminAct, AdminAct::class.java))
                        } else {
                            Toast.makeText(this@LoginAdminAct, "password salah", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@LoginAdminAct, error.message, Toast.LENGTH_SHORT).show()
                }

            })
    }
}