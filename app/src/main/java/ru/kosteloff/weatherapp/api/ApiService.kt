package ru.kosteloff.weatherapp.api

import retrofit2.Response
import retrofit2.http.GET
import ru.kosteloff.weatherapp.model.WeatherModel

interface ApiService {
    @GET("weather/Kazan")
    suspend fun getWeather(): Response<WeatherModel>
}