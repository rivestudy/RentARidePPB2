package com.dkp.rentarideppb2.data.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo(name = "namamobil") var namamobil: String?,
    @ColumnInfo(name = "harga sewa") var hargamobil: String?,
    @ColumnInfo(name = "merk") var merkmobil: String?

    )


