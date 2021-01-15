package com.example.trip.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trip.repository.TourRepository
import com.example.trip.viewmodel.AroundViewModel

@Suppress("UNCHECKED_CAST")
class AndroidViewModelFactory(
    private val repository: Any, private val application: Application
) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AroundViewModel::class.java) -> AroundViewModel(repository as TourRepository, application) as T

            else -> throw IllegalArgumentException("There is no $modelClass")
        }
    }
}