package com.udacity.asteroidradar.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.*
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.repository.AsteroidsDatabase.Companion.getInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {
    private lateinit var adapter:AsteroidsAdapter
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(requireContext()))[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel
         adapter = AsteroidsAdapter(AsteroidClick {
            findNavController().navigate(MainFragmentDirections.actionShowDetail(it))

        })
        binding.asteroidRecycler.adapter = adapter
        viewModel.AsteroidsList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }


        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_all_menu -> viewModel.defaultSelected()
            R.id.show_saved_menu -> viewModel.defaultSelected()
            R.id.show_today_menu -> viewModel.todaysAsteroidsSelected()
        }

        return true

    }

}
