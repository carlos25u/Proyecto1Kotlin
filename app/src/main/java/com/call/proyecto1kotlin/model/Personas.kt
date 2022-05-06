package com.call.proyecto1kotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Personas")
data class Personas (
    @PrimaryKey(autoGenerate = true)
    val PersonaId:Int,
    val Nombres:String,
    val Balance:Float
        )

