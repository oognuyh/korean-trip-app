package com.example.trip.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trip.model.AreaBasedContent
import com.example.trip.model.SearchFestivalContent
import com.example.trip.repository.TourAPIRepository
import com.example.trip.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeViewModel(private val repository: TourAPIRepository) : ViewModel() {
    private var _festivalItems = MutableLiveData<List<SearchFestivalContent>>()
    val festivalItems: LiveData<List<SearchFestivalContent>>
        get() = _festivalItems

    private var _familyCourseItems = MutableLiveData<List<AreaBasedContent>>()
    val familyCourseItems: LiveData<List<AreaBasedContent>>
        get() = _familyCourseItems

    private var _aloneCourseItems = MutableLiveData<List<AreaBasedContent>>()
    val aloneCourseItems: LiveData<List<AreaBasedContent>>
        get() = _aloneCourseItems

    private var _healingCourseItems = MutableLiveData<List<AreaBasedContent>>()
    val healingCourseItems: LiveData<List<AreaBasedContent>>
        get() = _healingCourseItems

    private var _walkCourseItems = MutableLiveData<List<AreaBasedContent>>()
    val walkCourseItems: LiveData<List<AreaBasedContent>>
        get() = _walkCourseItems

    private var _campingCourseItems = MutableLiveData<List<AreaBasedContent>>()
    val campingCourseItems: LiveData<List<AreaBasedContent>>
        get() = _campingCourseItems

    private var _tasteCourseItems = MutableLiveData<List<AreaBasedContent>>()
    val tasteCourseItems: LiveData<List<AreaBasedContent>>
        get() = _tasteCourseItems

    fun fetchFestivalItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val items = repository.getSearchFestival("10", "1", "P", getDate())
            _festivalItems.postValue(items)
        }
    }

    fun fetchCourseItems() {
        val courses = listOf(FAMILY_COURSE, ALONE_COURSE, HEALING_COURSE, WALK_COURSE, CAMPING_COURSE, TASTE_COURSE)
        var i = 0
        val courseItems = listOf(_familyCourseItems, _aloneCourseItems, _healingCourseItems, _walkCourseItems, _campingCourseItems, _tasteCourseItems)
        courseItems.forEach {
            viewModelScope.launch(Dispatchers.IO) {
                val items = repository.getAreaBasedList("20", "1", "P", "C01", courses[i++], COURSE)
                it.postValue(items)
            }
        }
    }

    private fun getDate(): String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
}