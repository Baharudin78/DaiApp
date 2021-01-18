package com.baharudin.daiapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Dai (
    var nama : String = "",
    var deskripsi : String ="" ,
    var alamat : String ="",
    var telepon : String ="",
    var foto : String =""
        ):Parcelable {

        constructor():this("","","",""){

                }
}