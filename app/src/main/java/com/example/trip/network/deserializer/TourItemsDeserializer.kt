package com.example.trip.network.deserializer

import android.util.Log
import com.example.trip.model.tour.common.TourItems
import com.example.trip.utils.genericType
import com.example.trip.model.tour.Content
import com.example.trip.model.tour.ContentInfo
import com.example.trip.model.tour.ContentIntro
import com.google.gson.*
import java.lang.Exception
import java.lang.reflect.Type

class TourItemsDeserializer<T> : JsonDeserializer<TourItems<T>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): TourItems<T> {
        try {
            json as JsonObject
        } catch (e: Exception) {
            Log.d(javaClass.simpleName, "Maybe the number of items is zero.")
            return TourItems(null, null)
        }

        if (!json.has("item")) return TourItems(null, null)
        with (json.get("item")) {
            return when (typeOfT) {
                genericType<TourItems<Content>>() -> if (isJsonArray) {
                    TourItems(item = null, items = Gson().fromJson(asJsonArray, genericType<List<Content>>()))
                } else {
                    TourItems(item = Gson().fromJson(asJsonObject,  genericType<Content>()), items = null)
                }

                genericType<TourItems<ContentInfo>>() ->  if (isJsonArray) {
                    TourItems(item = null, items = Gson().fromJson(asJsonArray,  genericType<List<ContentInfo>>()))
                } else {
                    TourItems(item = Gson().fromJson(asJsonObject,  genericType<ContentInfo>()), items = null)
                }

                genericType<TourItems<ContentIntro>>() -> if (isJsonArray) {
                    TourItems(item = null, items = Gson().fromJson(asJsonArray,  genericType<List<ContentIntro>>()))
                } else {
                    TourItems(item = Gson().fromJson(asJsonObject,  genericType<ContentIntro>()), items = null)
                }

                else -> TourItems(null, null)
            }
        }
    }
}

