package com.baharudin.daiapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.daiapp.utils.Preference

class SplashAct : AppCompatActivity() {

    private lateinit var preference: Preference
    private var username_key : String =""

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        preference = Preference(this)
        getUsername()

    }

    private fun getUsername() {
        username_key = preference.getValue("username")!!
        if (username_key.isEmpty()){
            runnable
        }else{
            finishAffinity()
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }

    private val runnable = Runnable {
        finishAffinity()
        startActivity(Intent(this,LoginAct::class.java))
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,2000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}