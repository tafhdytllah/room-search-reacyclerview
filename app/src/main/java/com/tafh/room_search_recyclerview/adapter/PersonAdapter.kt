package com.tafh.room_search_recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tafh.room_search_recyclerview.databinding.ItemPersonBinding
import com.tafh.room_search_recyclerview.model.Person

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonViewModel>() {

    private var list = listOf<Person>()

    inner class PersonViewModel(private val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(personList: Person) {
            binding.apply {
                tvAge.text = personList.age.toString()
                tvName.text = personList.name
                tvStreetName.text = personList.address.streetName
                tvStreetNumber.text = personList.address.streetNumber.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewModel {
        val view = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewModel(view)
    }

    override fun onBindViewHolder(holder: PersonViewModel, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setData(dataPerson: List<Person>) {
        this.list = dataPerson
        notifyDataSetChanged()
    }
}