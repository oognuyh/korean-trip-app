package com.example.trip.utils

import com.example.trip.model.DetailInfoContent
import com.example.trip.model.DetailInfoItems
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DetailInfoDeserializer : JsonDeserializer<DetailInfoItems> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): DetailInfoItems {
        json as JsonObject

        with (json.get("item")) {
            return if (isJsonArray) { // jsonArray 형식으로 들어온 경우
                val type = object : TypeToken<List<DetailInfoContent>>() {}.type
                DetailInfoItems(Gson().fromJson<List<DetailInfoContent>>(asJsonArray, type))
            } else { // jsonObject 형식으로 들어온 경우
                val type = object : TypeToken<DetailInfoContent>() {}.type
                DetailInfoItems(Gson().fromJson<DetailInfoContent>(asJsonObject, type))
            }
        }
    }
}