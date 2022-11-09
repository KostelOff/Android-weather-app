package ru.kosteloff.weatherapp.model

/*
{
   "temperature":"29 °C",
   "wind":"20 km/h",
   "description":"Partly cloudy",
   "forecast":[
      {
         "day":1,
         "temperature":"27 °C",
         "wind":"12 km/h"
      },
      {
         "day":2,
         "temperature":"22 °C",
         "wind":"8 km/h"
      }
   ]
}
 */

class WeatherModel(
    val description: String,
    val forecast: List<Forecast>,
    val temperature: String,
    val wind: String
)

data class Forecast(
    val day: Int,
    val temperature: String,
    val wind: String
)