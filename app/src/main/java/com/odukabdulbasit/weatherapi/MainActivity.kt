package com.odukabdulbasit.weatherapi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val apiKey = "YOUR_API_KEY_HERE"
    private val baseUrl = "https://api.openweathermap.org/data/2.5/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherApi = retrofit.create(WeatherApi::class.java)

        // Handle button click to fetch weather data
        val fetchButton = findViewById<Button>(R.id.fetchButton)
        val cityEditText = findViewById<EditText>(R.id.cityEditText)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        fetchButton.setOnClickListener {
            val city = cityEditText.text.toString()
            if (city.isNotEmpty()) {
                // Make the API call
                val call = weatherApi.getWeather(city, apiKey)

                call.enqueue(object : Callback<WeatherResponse> {
                    override fun onResponse(
                        call: Call<WeatherResponse>,
                        response: Response<WeatherResponse>
                    ) {
                        if (response.isSuccessful) {
                            val weatherData = response.body()
                            val temperatureKelvin = weatherData?.main?.temp
                            val temperatureCelsius =
                                temperatureKelvin?.minus(273.15) // Convert Kelvin to Celsius
                            resultTextView.text = "Temperature: $temperatureCelsius°C"
                        } else {
                            resultTextView.text = "Error: ${response.code()}"
                        }
                    }

                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                        resultTextView.text = "Failed to fetch data: ${t.message}"
                    }
                })
            } else {
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}