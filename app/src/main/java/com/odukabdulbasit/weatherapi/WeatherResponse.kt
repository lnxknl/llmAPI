package com.odukabdulbasit.weatherapi


data class WeatherResponse(
    val main: Main,
    // Add other properties you need from the API response
)

data class Main(
    val temp: Double
    // Add other properties you need from the API response
)
