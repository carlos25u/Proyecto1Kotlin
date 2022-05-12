package com.call.proyecto1kotlin.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Personas")
@Parcelize
data class Persona (
    @PrimaryKey(autoGenerate = true)
    val PersonaId:Int,
    val Nombres:String,
    val Email:String,
    val Balance:Float

        ): Parcelable

