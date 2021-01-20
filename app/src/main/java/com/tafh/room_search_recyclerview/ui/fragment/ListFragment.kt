package com.tafh.room_search_recyclerview.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tafh.room_search_recyclerview.R
import com.tafh.room_search_recyclerview.adapter.PersonAdapter
import com.tafh.room_search_recyclerview.databinding.FragmentListBinding
import com.tafh.room_search_recyclerview.model.Address
import com.tafh.room_search_recyclerview.model.Person
import com.tafh.room_search_recyclerview.viewmodel.PersonViewModel

class ListFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val mAdapter: PersonAdapter by lazy { PersonAdapter() }
    private val viewModdel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        // recycler view
        binding.apply {
            with(rvPerson) {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }

        inputDataPerson()

        viewModdel.readPerson.observe(viewLifecycleOwner, Observer { personList ->
            mAdapter.setData(personList)
        })


//        binding.tvEmptyList.visibility = View.VISIBLE

        return view
    }

    private fun inputDataPerson() {

        val address = listOf(
                Address("Jl Basmol Raya", 24),
                Address("Jl Kembangan Raya", 40),
                Address("Jl Kebon Jeruk", 30),
                Address("Jl Komodo", 28),
                Address("Jl Sultan Sari", 60),
                Address("Jl Komplek DKI", 21),
                Address("Jl Linggar Jati", 12),
                Address("Jl Kebon Nanas", 18),
                Address("Jl Permata Hijau", 42),
                Address("Jl Lurus Belok", 11),

        )

        val person = listOf(
                Person("Taufik", 20, address[0]),
                Person("Mama Budi", 10, address[1]),
                Person("Bapak Budi", 12, address[2]),
                Person("Ibu Budi", 50, address[3]) ,
                Person("Adik Budi", 65, address[4]),
                Person("Kakak Budi", 72, address[5]),
                Person("Ayah Budi", 10, address[6]),
                Person("Teman Budi", 20, address[7]),
                Person("Budi Lagi", 10, address[8]),
                Person("Budi", 49, address[9])
        )


        viewModdel.insertPerson(person)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as SearchView


        searchView.apply {
            isSubmitButtonEnabled = true
            setOnQueryTextListener(this@ListFragment)
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchPerson(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchPerson(query)
        }
        return true
    }

    private fun searchPerson(query: String?) {
        val searchQuery = "%$query%"

        viewModdel.searchPerson(searchQuery).observe(viewLifecycleOwner, Observer { list ->
            list.let {
                mAdapter.setData(it)
            }

        })
    }


}