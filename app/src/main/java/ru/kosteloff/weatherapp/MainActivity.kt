package ru.kosteloff.weatherapp

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.kosteloff.weatherapp.databinding.ActivityMainBinding
import ru.kosteloff.weatherapp.viewmodel.WeatherViewModel
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherResp()
        binding.tvDayOfWeek.text = dayOfWeek()
        binding.date.text = date()
        otherDates()
    }

    private fun weatherResp() {
        viewModel.weatherResp.observe(this) { weather ->
            binding.apply {
                tvMainTemp.text = weather.temperature
                tvDescription.text = weather.description
                tvWind.text = "Wind speed: ${weather.wind}"

                val desc = weather.description
                if (desc.contains("rain") ||
                    desc.contains("Rain")
                ) {
                    binding.imageView.setImageResource(R.drawable.rain)
                } else if (desc.contains("Cloudy") ||
                    desc.contains("cloudy")
                ) {
                    binding.imageView.setImageResource(R.drawable.cloudy)
                } else if (
                    desc.contains("sunny") ||
                    desc.contains("Sunny")
                ) {
                    binding.imageView.setImageResource(R.drawable.sunny)
                } else {
                    binding.imageView.setImageResource(R.drawable.gradient)
                }

                val forecast1 = weather.forecast[0]
                val forecast2 = weather.forecast[1]
                val forecast3 = weather.forecast[2]

                tvTemperatureDay1.text = forecast1.temperature
                tvTemperatureDay2.text = forecast2.temperature
                tvTemperatureDay3.text = forecast3.temperature

                tvWindDay1.text = forecast1.wind
                tvWindDay2.text = forecast2.wind
                tvWindDay3.text = forecast3.wind
            }
        }
    }

    private fun date(): String {
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val calendar = Calendar.getInstance()
        val getTime = calendar.time
        val formatter = simpleDateFormat.format(getTime)
        return formatter
    }

    private fun dayOfWeek(): String {
        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfWeek = sdf.format(d)
        dayOfWeekOtherWeeks(dayOfWeek)
        return dayOfWeek
    }

    private fun dayOfWeekOtherWeeks(dayOfWeek: String) {
        when (dayOfWeek) {
            "Monday" -> {
                binding.apply {
                    tvDay1Name.text = "Tuesday"
                    tvDay2Name.text = "Wednesday"
                    tvDay3Name.text = "Thursday"
                }
            }
            "Tuesday" -> {
                binding.apply {
                    tvDay1Name.text = "Wednesday"
                    tvDay2Name.text = "Thursday"
                    tvDay3Name.text = "Friday"
                }
            }
            "Wednesday" -> {
                binding.apply {
                    tvDay1Name.text = "Thursday"
                    tvDay2Name.text = "Friday"
                    tvDay3Name.text = "Saturday"
                }
            }
            "Thursday" -> {
                binding.apply {
                    tvDay1Name.text = "Friday"
                    tvDay2Name.text = "Saturday"
                    tvDay3Name.text = "Sunday"
                }
            }
            "Friday" -> {
                binding.apply {
                    tvDay1Name.text = "Saturday"
                    tvDay2Name.text = "Sunday"
                    tvDay3Name.text = "Monday"
                }
            }
            "Saturday" -> {
                binding.apply {
                    tvDay1Name.text = "Sunday"
                    tvDay2Name.text = "Monday"
                    tvDay3Name.text = "Tuesday"
                }
            }
            "Sunday" -> {
                binding.apply {
                    tvDay1Name.text = "Monday"
                    tvDay2Name.text = "Tuesday"
                    tvDay3Name.text = "Wednesday"
                }
            }
        }
    }

    private fun otherDates() {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)

        if ((day + 1).toString().length == 2) {
            binding.dat1.text = "${day + 1}.$month.$year"
        } else {
            binding.dat1.text = "0${day + 1}.$month.$year"
        }


        if ((day + 2).toString().length == 2) {
            binding.dat2.text = "${day + 2}.$month.$year"
        } else {
            binding.dat2.text = "0${day + 2}.$month.$year"
        }

        if ((day + 3).toString().length == 2) {
            binding.dat3.text = "${day + 3}.$month.$year"
        } else {
            binding.dat3.text = "0${day + 3}.$month.$year"
        }
    }
}