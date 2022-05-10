package com.call.proyecto1kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.call.proyecto1kotlin.data.PersonaDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.call.proyecto1kotlin.model.Persona
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@HiltViewModel
class PersonaViewModel @Inject constructor(
    val personaDao: PersonaDao
) : ViewModel(){

    val personas : Flow<List<Persona>>
        get() =  personaDao.GetLista()

    private val _guardado = MutableLiveData(false)
    val guardado: LiveData<Boolean> = _guardado

    fun guardar(persona: Persona){
        viewModelScope.launch {
            personaDao.Insertar(persona)
            _guardado.value = true
        }
    }
}