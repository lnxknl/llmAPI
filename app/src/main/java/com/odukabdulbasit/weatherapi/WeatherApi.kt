package com.odukabdulbasit.weatherapi
import com.google.gson.annotations.SerializedName

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

import retrofit2.http.Query

interface WeatherApi {
//    @GET("generate")
    @POST("generate")

//    @Headers("Content-Type: application/json")
    fun getWeather(@Body payload: Map<String, String>): Call<WeatherResponse>

//    fun getWeather(
//        @Query("prompt") city: String,
//        @Query("apiKey") apiKey: String
//
////    @SerializedName("prompt") city: String,
////    @SerializedName("apiKey") apiKey: String
//    ): Call<WeatherResponse>
//    @POST("generate")
//    fun generatePrompt(@Body request: PromptRequest): Call<PromptResponse>


}

