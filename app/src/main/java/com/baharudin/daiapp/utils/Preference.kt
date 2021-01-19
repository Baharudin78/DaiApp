package com.baharudin.daiapp.utils

import android.content.Context
import android.content.SharedPreferences

class Preference(context: Context) {
    companion object {
        const val USER_PREF = "USER_PREF"
    }
    var sharedPreferences = context.getSharedPreferences(USER_PREF,0)

    fun setValue(key: String, value : String){
        val editor :SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }
    fun getValue(key: String) : String?{
        return sharedPreferences.getString(key,"")
    }
    fun clear(){
        val editor :SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
            .apply()
    }
}