package com.call.proyecto1kotlin.View.Personas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.findNavController
import com.call.proyecto1kotlin.R
import com.call.proyecto1kotlin.databinding.ListaPersonasFragmentBinding
import com.call.proyecto1kotlin.model.Persona
import com.call.proyecto1kotlin.viewmodel.PersonaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListaPersonasFragment: Fragment() {
    private val viewModel: PersonaViewModel by viewModels()
    private lateinit var binding: ListaPersonasFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ListaPersonasFragmentBinding.inflate(inflater, container, false)
        val adapter = PersonasAdapter(::openPersonasFragment)
        binding.personasRecyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.personas.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { lista ->
                    adapter.submitList(lista)
                }
        }

        binding.agregarButton.setOnClickListener {
            openPersonasFragment()
        }
        return binding.root
    }

    fun openPersonasFragment(persona:Persona?=null)  {
        val action = ListaPersonasFragmentDirections.actionToPersonasFragment(persona)
        findNavController().navigate(action)
    }
}