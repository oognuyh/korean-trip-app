package com.example.trip.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trip.repository.TourRepository
import com.example.trip.viewmodel.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: Any) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as TourRepository) as T

            else -> throw IllegalArgumentException("There is no $modelClass")
        }
    }
}