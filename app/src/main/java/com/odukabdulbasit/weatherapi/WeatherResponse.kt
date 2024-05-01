package com.odukabdulbasit.weatherapi
import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    val output: String,
    // Add other properties you need from the API response
)

data class Main(
    val temp: String,
    // Add other properties you need from the API response
)


data class PromptRequest(
    @SerializedName("prompt") val prompt: String,
    @SerializedName("apiKey") val apiKey: String
)

data class PromptResponse(
    @SerializedName("response") val response: String
)
