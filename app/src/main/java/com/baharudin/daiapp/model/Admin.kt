package com.baharudin.daiapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Admin (
    var username : String ="",
    var password : String =""
        ) : Parcelable