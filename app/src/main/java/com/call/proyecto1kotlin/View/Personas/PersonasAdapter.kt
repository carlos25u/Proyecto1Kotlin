package com.call.proyecto1kotlin.View.Personas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.call.proyecto1kotlin.R
import com.call.proyecto1kotlin.databinding.RowPersonaBinding
import com.call.proyecto1kotlin.model.Persona

class PersonasAdapter(
    private var onItemClicked: ((persona:Persona) -> Unit)
):
    ListAdapter<Persona, PersonasAdapter.PersonaViewHolder>(PersonaDiffCallBack()){

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val binding =
            RowPersonaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonaViewHolder(binding)
    }

    inner class PersonaViewHolder(private val binding: RowPersonaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Persona) {
            binding.personaIdTextView.text = item.PersonaId.toString()
            binding.nombresTextView.text = item.Nombres
            binding.BalanceTextView.text = item.Balance.toString();

            binding.root.setOnClickListener {
                onItemClicked(item)
            }
        }
    }
}

class PersonaDiffCallBack : DiffUtil.ItemCallback<Persona>() {
    override fun areItemsTheSame(oldItem: Persona, newItem: Persona): Boolean {
        return oldItem.PersonaId == newItem.PersonaId
    }

    override fun areContentsTheSame(oldItem: Persona, newItem: Persona): Boolean {
        return oldItem == newItem
    }

}