package com.example.trip.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trip.model.tour.Content
import com.example.trip.repository.TourRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TourRepository) : ViewModel() {
    private var _courses = MutableLiveData<List<Content>>()
    private var _festivals = MutableLiveData<List<Content>>()

    val courses: LiveData<List<Content>> get() = _courses
    val festivals: LiveData<List<Content>> get() = _festivals

    init {
        getAllCourses()
        getFestivals()
    }

    private fun getAllCourses() = viewModelScope.launch {
        _courses.postValue(repository.getCourseList(pageNo = "1"))
    }

    private fun getFestivals() = viewModelScope.launch {
        _festivals.postValue(repository.getFestivalList(pageNo = "1"))
    }
}