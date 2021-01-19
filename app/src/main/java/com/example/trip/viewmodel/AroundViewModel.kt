package com.example.trip.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.trip.model.tour.Content
import com.example.trip.repository.TourRepository
import com.example.trip.util.LocationLiveData
import kotlinx.coroutines.launch

class AroundViewModel(private val repository: TourRepository, application: Application) : AndroidViewModel(application) {
    private val location = LocationLiveData(application)
    var contents = MutableLiveData<List<Content>>()

    fun getNearbyContents(mapX: String, mapY: String) = viewModelScope.launch {
        contents.postValue(repository.getAroundContents(pageNo = "1", mapX = mapX, mapY = mapY))
    }

    fun getLocation() = location
}