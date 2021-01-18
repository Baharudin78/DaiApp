package com.baharudin.daiapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users (
    var username : String ="",
    var email : String="",
    var password : String="",
    var nohp : String=""
        ): Parcelable