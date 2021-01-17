package com.example.trip.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.trip.R
import com.example.trip.databinding.FragmentHomeBinding
import com.example.trip.network.TourApi
import com.example.trip.repository.TourRepository
import com.example.trip.ui.base.BaseFragment
import com.example.trip.ui.home.adapter.CourseAdapter
import com.example.trip.ui.home.adapter.FestivalAdapter
import com.example.trip.ui.home.adapter.ForYouAdapter
import com.example.trip.viewmodel.HomeViewModel
import com.example.trip.viewmodel.factory.ViewModelFactory

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupViewModel()
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.courseRV.adapter = CourseAdapter()
        binding.festivalRV.adapter = FestivalAdapter()
        binding.forYouRV.adapter = ForYouAdapter()
    }

    private fun setupToolbar() {
        navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.headerLayout.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun setupViewModel() {
        val repository = TourRepository(TourApi.invoke())
        val factory = ViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        binding.viewModel = viewModel
    }

    override fun getLayoutResId() = R.layout.fragment_home
}