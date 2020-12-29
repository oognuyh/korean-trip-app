package com.example.trip.repository

import com.example.trip.network.TourApi
import com.example.trip.repository.base.TourBaseRepository
import com.example.trip.util.CONTENT_TYPE
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TourRepository(private val api: TourApi) : TourBaseRepository() {

    companion object {
        private const val CAT1_COURSE = "C01"
    }

    suspend fun getCourseList(
        numOfRows: String = "20",
        pageNo: String,
        courseType: String,
    ) = withContext(Dispatchers.IO) {
        apiRequest {
            api.fetchContentList(numOfRows, pageNo, CONTENT_TYPE.COURSE, CAT1_COURSE, courseType)
        }.response?.body?.items?.items
    }

    suspend fun getFestivalList(
        numOfRows: String = "10",
        pageNo: String,
        arrange: String = "P",
        eventStartDate: String = getToday(),
        eventEndDate: String? = null
    ) = withContext(Dispatchers.IO) {
        apiRequest {
            api.fetchFestivalList(numOfRows, pageNo, arrange, eventStartDate, eventEndDate)
        }.response?.body?.items?.items
    }

    suspend fun getAroundContents(
        numOfRows: String = "100",
        pageNo: String,
        contentTypeId: String? = null,
        mapX: String,
        mapY: String,
        radius: String = "3000"
    ) = withContext(Dispatchers.IO) {
        apiRequest {
            api.fetchAroundContents(numOfRows, pageNo, contentTypeId, mapX, mapY, radius)
        }.response?.body?.items?.items
    }

    suspend fun getContentBasicInfo(
        contentId: String,
    ) = withContext(Dispatchers.IO) {
        api.fetchContentBasicInfo(contentId)
    }

    suspend fun getContentInfo(
        contentId: String,
        contentTypeId: String
    ) = withContext(Dispatchers.IO) {
        api.fetchContentInfo(contentId, contentTypeId)
    }

    suspend fun getContentIntro(
        contentId: String,
        contentTypeId: String
    ) = withContext(Dispatchers.IO) {
        api.fetchContentIntro(contentId, contentTypeId)
    }

    private fun getToday() = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
}