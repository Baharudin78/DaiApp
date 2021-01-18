package com.baharudin.daiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.baharudin.daiapp.databinding.ActivityRegisterBinding
import com.baharudin.daiapp.model.Users
import com.baharudin.daiapp.utils.Preference
import com.google.firebase.database.*

class RegisterAct : AppCompatActivity() {

    private lateinit var iUsername : String
    private lateinit var iPassword : String
    private lateinit var iEmail : String
    private lateinit var iHp : String

    private lateinit var dataRef : DatabaseReference
    private lateinit var firebaseInstance : FirebaseDatabase
    private lateinit var preference: Preference
    private lateinit var databaseInst : DatabaseReference
    lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = Preference(this)
        firebaseInstance = FirebaseDatabase.getInstance()
        dataRef = FirebaseDatabase.getInstance().getReference("Users")
        databaseInst = firebaseInstance.getReference("Users")

        binding.btDaftar.setOnClickListener {
            iUsername = binding.etUsername.text.toString()
            iPassword = binding.etPassword.text.toString()
            iEmail = binding.etEmail.text.toString()
            iHp = binding.etNohp.text.toString()

            if (iUsername == ""){
                binding.etUsername.error = "Isi username anda"
                binding.etUsername.requestFocus()
            }else if (iPassword == ""){
                binding.etPassword.error = "Isi password anda"
                binding.etPassword.requestFocus()
            }else if (iEmail == ""){
                binding.etEmail.error = "Isi email anda"
                binding.etEmail.requestFocus()
            }else if (iHp == ""){
                binding.etNohp.error = "Isi nomer HP anda"
                binding.etNohp.requestFocus()
            }else{
                saveUser(iUsername,iPassword,iEmail,iHp)
            }
        }
    }

    private fun saveUser(iUsername: String, iPassword: String, iEmail: String, iHp: String) {
        val user = Users()
        user.username = iUsername
        user.password = iPassword
        user.email = iEmail
        user.nohp = iHp

        if (iUsername != null){
            checkUser(iUsername,user)
        }
    }

    private fun checkUser(iUsername: String, user: Users) {
        databaseInst.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(Users::class.java)
                if (data == null) {
                    databaseInst.child(iUsername).setValue(user)
                    preference.setValue("username", user.username)
                    preference.setValue("password", user.password)
                    preference.setValue("email", user.email)
                    preference.setValue("nohp", user.nohp.toString())

                    Toast.makeText(this@RegisterAct, "berhasil mendaftar", Toast.LENGTH_SHORT)
                        .show()

                    val register = Intent(this@RegisterAct, HomeActivity::class.java)
                    register.putExtra("data", user)
                    startActivity(register)
                } else {
                    Toast.makeText(
                        this@RegisterAct,
                        "Username sudah digunakkan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RegisterAct, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}