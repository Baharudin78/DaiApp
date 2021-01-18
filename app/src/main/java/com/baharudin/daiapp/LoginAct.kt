package com.baharudin.daiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.baharudin.daiapp.admin.LoginAdminAct
import com.baharudin.daiapp.databinding.ActivityLoginBinding
import com.baharudin.daiapp.model.Users
import com.baharudin.daiapp.utils.Preference
import com.google.firebase.database.*

class LoginAct : AppCompatActivity() {
    private lateinit var iUsername : String
    private lateinit var iPassword : String
    private lateinit var dataRef : DatabaseReference
    lateinit var preference : Preference
    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = Preference(this)
        dataRef = FirebaseDatabase.getInstance().getReference("Users")

        binding.btMasuk.setOnClickListener {
            iUsername = binding.etUsername.text.toString()
            iPassword = binding.etPassword.text.toString()

            if (iUsername == ""){
                binding.etUsername.error = "Isi Username anda"
                binding.etUsername.requestFocus()
            }else if (iPassword == ""){
                binding.etPassword.error = "Isi Password anda"
            }else{
                getUser(iUsername,iPassword)
            }
        }
        binding.btDaftar.setOnClickListener {
            startActivity(Intent(this,RegisterAct::class.java))
        }
        binding.tvAdmin.setOnClickListener {
            startActivity(Intent(this,LoginAdminAct::class.java))
        }
    }

    private fun getUser(iUsername: String, iPassword: String) {
        dataRef.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(Users::class.java)
                if (user == null) {
                    Toast.makeText(this@LoginAct, "username tidak ditemukan", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (user.password.equals(iPassword)) {
                        preference.setValue("username", user.username)
                        preference.setValue("email", user.username)
                        preference.setValue("password", user.password)
                        preference.setValue("nohp", user.nohp.toString())
                        finishAffinity()
                        startActivity(Intent(this@LoginAct, HomeActivity::class.java))
                    } else {
                        Toast.makeText(this@LoginAct, "password salah", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginAct, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}