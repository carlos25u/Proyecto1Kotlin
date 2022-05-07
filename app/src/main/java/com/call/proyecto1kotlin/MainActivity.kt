package com.call.proyecto1kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.call.proyecto1kotlin.databinding.ActivityMainBinding
import com.call.proyecto1kotlin.model.Persona
import com.call.proyecto1kotlin.viewmodel.PersonaViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PersonaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        binding.guardarButton.setOnClickListener{
            viewModel.guardar(Persona(
                0,
                binding.NombresEditText.text.toString(),
                binding.BalancesEditText.floatValue()
            ))
        }

        viewModel.guardado.observe(this){
            if(it){
                Snackbar.make(binding.BalancesEditText, "Guardo", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    fun TextInputEditText.floatValue() = text.toString().toFloatOrNull() ?: 0.0f
}