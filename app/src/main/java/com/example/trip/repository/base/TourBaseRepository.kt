package com.example.trip.repository.base

import com.example.trip.model.tour.common.TourVO
import com.example.trip.utils.TourApiException
import retrofit2.Response
import java.lang.Exception

abstract class TourBaseRepository {
    suspend fun <T> apiRequest(
            call: suspend () -> Response<T>
    ): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val code = response.code()
            val message = response.message()

            if (code == 500) {
                try {
                    val result = response.body() as TourVO<*>
                    val resultCode = result.response?.header?.resultCode
                    val resultMsg = result.response?.header?.resultMsg

                    throw TourApiException("$resultCode - $resultMsg")
                } catch(e: Exception) {
                    throw TourApiException("$code - $message")
                }
            } else {
                throw TourApiException("${response.code()} - ${response.message()}")
            }
        }
    }
}