package com.call.proyecto1kotlin.data

import androidx.room.*
import com.call.proyecto1kotlin.model.Ocupacion
import com.call.proyecto1kotlin.model.Persona
import kotlinx.coroutines.flow.Flow

@Dao
interface OcupacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insertar(ocupacion: Ocupacion)

    @Query("""
        SELECT *FROM Ocupaciones WHERE OcupacionId=:ocupacionId        
          """)

    fun Buscar(ocupacionId: Int): Flow<Ocupacion>
    @Delete
    suspend fun Eliminar(ocupacion: Ocupacion)

    @Query("""SELECT * FROM Ocupaciones ORDER BY OcupacionId """)
    fun GetLista(): Flow<List<Ocupacion>>
}