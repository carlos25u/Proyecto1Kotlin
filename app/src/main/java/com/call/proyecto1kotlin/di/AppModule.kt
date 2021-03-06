package com.call.proyecto1kotlin.di

import android.content.Context
import androidx.room.Room
import com.call.proyecto1kotlin.data.OcupacionDao
import com.call.proyecto1kotlin.data.PersonaDao
import com.call.proyecto1kotlin.data.PersonasDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun ProvideTicketDb(@ApplicationContext context: Context): PersonasDb{
        val DATABASE_NAME = "PersonasDb"
        return Room.databaseBuilder(
            context,
            PersonasDb::class.java,
            DATABASE_NAME       )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun ProvidePersonaDao(personasDb: PersonasDb):PersonaDao{
        return personasDb.personaDao
    }

    @Provides
    fun ProvideOcupacionDao(personasDb: PersonasDb):OcupacionDao{
        return personasDb.ocupacionDao
    }
}