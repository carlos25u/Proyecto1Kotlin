package com.call.proyecto1kotlin.data

import androidx.room.*
import com.call.proyecto1kotlin.model.Persona
import java.util.concurrent.Flow

@Dao
interface PersonaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insertar(persona:Persona)

    @Delete
    suspend fun Eliminar(persona: Persona)

    /*@Query("SELECT * FROM Personas WHERE PersonaId = PersonaId")
    fun Buscar(personaId:Int): Flow<Persona>

    @Query("SELECT * FROM Personas ORDER BY PersonaId")
    fun GetLista(): Flow<List<Persona>>*/
}