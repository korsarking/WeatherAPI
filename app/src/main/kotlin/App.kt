import helpers.RetrofitClient
import helpers.WeatherResponse
import kotlinx.coroutines.*
import kotlinx.datetime.*

class App {
    fun run() = runBlocking {
        val now = Clock.System.now()
        val currentDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
        val nextDay = currentDateTime.date.plus(DatePeriod(days = 1))

        val apiKey = "a56b88d8dccf4bccaad125110253108"
        val cities = arrayOf("Chisinau", "Madrid", "Kyiv", "Amsterdam")
        val days = 1
        val aqi = "no"
        val alerts = "no"

        val results = cities.map { city ->
            async {
                try {
                    city to RetrofitClient.api.getForecast(
                        apiKey = apiKey,
                        city = city,
                        days = days,
                        date = nextDay.toString(),
                        aqi = aqi,
                        alerts = alerts
                    )
                } catch (e: Exception) {
                    println("Error fetching forecast for $city: ${e.message}")
                    null
                }
            }
        }.awaitAll()
            .filterNotNull()
            .toMap()

        println(
            String.format(
                "%-12s | %-12s | %-8s | %-8s | %-10s | %-9s | %-6s | %-12s",
                "City", "Date", "Min (C)", "Max (C)", "Humidity (%)", "Wind (kph)", "Wind Dir", "Condition"
            )
        )
        println("-".repeat(105))

        results.forEach { (city, res) ->
            val forecastDay = res.forecast.forecastday[0]
            val day = forecastDay.day
            val current = res.current

            println(
                String.format(
                    "%-12s | %-12s | %-8.1f | %-8.1f | %-12d | %-10.1f | %-8s | %-12s",
                    city, forecastDay.date, day.mintemp_c, day.maxtemp_c, day.avghumidity, day.maxwind_kph, current.wind_dir, day.condition.text
                )
            )
        }
    }
}

fun main() {
    App().run()
}
