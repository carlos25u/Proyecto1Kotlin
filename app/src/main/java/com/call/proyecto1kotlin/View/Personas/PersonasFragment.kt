package com.call.proyecto1kotlin.View.Personas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.call.proyecto1kotlin.databinding.PersonasFragmentBinding
import com.call.proyecto1kotlin.databinding.ListaPersonasFragmentBinding
import com.call.proyecto1kotlin.model.Ocupacion
import com.call.proyecto1kotlin.model.Persona
import com.call.proyecto1kotlin.viewmodel.PersonaViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

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
                    binding.EmailEditText.text.toString(),
                    binding.BalancesEditText.floatValue(),
                )
            )
        }

        binding.eliminarButton.setOnClickListener{
            viewModel.eliminar(
                Persona(
                    personaId,
                    binding.NombresEditText.text.toString(),
                    binding.EmailEditText.text.toString(),
                    binding.BalancesEditText.floatValue(),
                )
            )
        }

        val addOcupaciones = ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_dropdown_item)

        addOcupaciones.addAll(Arrays.asList("Doctor", "Ingeniero"))
        binding.ocupacionSpinner.adapter = addOcupaciones


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
            binding.EmailEditText.setText(it.Email)
            binding.BalancesEditText.setText(it.Balance.toString())
        }
    }

    fun TextInputEditText.floatValue() = text.toString().toFloatOrNull() ?: 0.0f
}