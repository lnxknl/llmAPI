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
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val apiKey = ""
    private val baseUrl = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
    .callFactory(OkHttpClient.Builder()
        .readTimeout(360, TimeUnit.SECONDS)
        .connectTimeout(360, TimeUnit.SECONDS)
        .build())
            .build()

        val weatherApi = retrofit.create(WeatherApi::class.java)
//        println(weatherApi)

        // Handle button click to fetch weather data
        val fetchButton = findViewById<Button>(R.id.fetchButton)
        val cityEditText = findViewById<EditText>(R.id.cityEditText)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        fetchButton.setOnClickListener {
            val city = cityEditText.text.toString()
            if (city.isNotEmpty()) {
                // Make the API call
val payload = mapOf(
    "apiKey" to apiKey,
    "prompt" to city
)
                val call = weatherApi.getWeather(payload)

                call.enqueue(object : Callback<WeatherResponse> {
                    override fun onResponse(
                        call: Call<WeatherResponse>,
                        response: Response<WeatherResponse>
                    ) {
                        if (response.isSuccessful) {
                            val weatherData = response.body()
//                            val weatherData =  response.body()?.string()
                            val temperatureCelsius = weatherData?.output
//                            val temperatureCelsius = weatherData?.response
//                            val temperatureCelsius =
//                                temperatureKelvin?.minus(273.15) // Convert Kelvin to Celsius
                            resultTextView.text = "echo: $temperatureCelsius"
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
