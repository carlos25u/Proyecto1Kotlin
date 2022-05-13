package com.call.proyecto1kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.call.proyecto1kotlin.data.OcupacionDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.call.proyecto1kotlin.model.Ocupacion
import com.call.proyecto1kotlin.model.Persona
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@HiltViewModel
class OcupacionViewModel @Inject constructor(
    val ocupacionDao: OcupacionDao
): ViewModel() {
    val ocupaciones : Flow<List<Ocupacion>>
        get() =  ocupacionDao.GetLista()

    private val _guardado = MutableLiveData(false)
    val guardado: LiveData<Boolean> = _guardado

    fun guardar(ocupacion: Ocupacion){
        viewModelScope.launch {
            ocupacionDao.Insertar(ocupacion)
            _guardado.value = true
        }
    }
}