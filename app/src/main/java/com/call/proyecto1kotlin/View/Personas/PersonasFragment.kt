package com.call.proyecto1kotlin.View.Personas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.call.proyecto1kotlin.databinding.PersonasFragmentBinding
import com.call.proyecto1kotlin.databinding.ListaPersonasFragmentBinding
import com.call.proyecto1kotlin.model.Persona
import com.call.proyecto1kotlin.viewmodel.PersonaViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonasFragment : Fragment() {
    private val viewModel: PersonaViewModel by viewModels()
    private lateinit var binding: PersonasFragmentBinding

    private val args : PersonasFragmentArgs by navArgs()

    private var personaId: Int =0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PersonasFragmentBinding.inflate(inflater, container, false)

        LlenarCampos()

        binding.guardarButton.setOnClickListener {
            viewModel.guardar(
                Persona(
                    personaId,
                    binding.NombresEditText.text.toString(),
                    binding.BalancesEditText.floatValue()
                )
            )
        }

        viewModel.guardado.observe(viewLifecycleOwner){
            if (it) {
                Snackbar.make(binding.BalancesEditText, "Guardo", Snackbar.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
        }

        return binding.root
    }

    fun LlenarCampos(){
       val persona: Persona? = args.personas

        persona?.let {
            personaId = it.PersonaId
            binding.NombresEditText.setText(it.Nombres)
            binding.BalancesEditText.setText(it.Balance.toString())
        }
    }

    fun TextInputEditText.floatValue() = text.toString().toFloatOrNull() ?: 0.0f
}