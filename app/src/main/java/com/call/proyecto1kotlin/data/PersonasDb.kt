package com.call.proyecto1kotlin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.call.proyecto1kotlin.model.Ocupacion
import com.call.proyecto1kotlin.model.Persona

@Database(
    entities = [Persona::class, Ocupacion::class],
    version = 3
)
abstract class PersonasDb : RoomDatabase() {
    abstract val personaDao: PersonaDao
    abstract val ocupacionDao: OcupacionDao
}