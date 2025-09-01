import okhttp3.OkHttpClient
import okhttp3.Request
import com.google.gson.JsonParser
import kotlinx.datetime.*

data class Forecast(
    val date: String,
    val maxTemp: Float,
    val minTemp: Float,
    val humidity: Int,
    val windKph: Float,
    val windDir: String,
    val condition: String
)

class App {
    fun run() {
        val client = OkHttpClient()

        val now = Clock.System.now()
        val currentDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
        val nextDay = currentDateTime.date.plus(DatePeriod(days = 1))

        val apiKey = "a56b88d8dccf4bccaad125110253108"
        val cities = arrayOf("Chisinau", "Madrid", "Kyiv", "Amsterdam")
        val days = 1
        val aqi = "no"
        val alerts = "no"

        val placeHolder = mutableMapOf<String, Forecast>()

        cities.forEach { city ->

            val url =
                "https://api.weatherapi.com/v1/forecast.json?key=$apiKey&q=$city&days=$days&dt=$nextDay&aqi=$aqi&alerts=$alerts"

            val request = Request.Builder()
                .url(url)
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    println("Error for $city: ${response.code}")
                } else {
                    val body = response.body?.string()
                    if (body != null) {
                        val json = JsonParser.parseString(body).asJsonObject

                        val forecastDay = json["forecast"]
                            .asJsonObject["forecastday"]
                            .asJsonArray[0]
                            .asJsonObject

                        val date = forecastDay["date"].asString
                        val day = forecastDay["day"].asJsonObject

                        val forecast = Forecast(
                            date = date,
                            maxTemp = day["maxtemp_c"].asFloat,
                            minTemp = day["mintemp_c"].asFloat,
                            humidity = day["avghumidity"].asInt,
                            windKph = day["maxwind_kph"].asFloat,
                            windDir = json["current"].asJsonObject["wind_dir"].asString,
                            condition = day["condition"].asJsonObject["text"].asString
                        )

                        placeHolder[city] = forecast
                    }
                }
            }
        }

        println(
            String.format(
                "%-12s | %-12s | %-8s | %-8s | %-10s | %-9s | %-6s | %-12s",
                "City", "Date", "Min (C)", "Max (C)", "Humidity (%)", "Wind (kph)", "Wind Dir", "Condition"
            )
        )
        println("-".repeat(105))

        placeHolder.forEach { (city, f) ->
            println(
                String.format(
                    "%-12s | %-12s | %-8.1f | %-8.1f | %-12d | %-10.1f | %-8s | %-12s",
                    city, f.date, f.minTemp, f.maxTemp, f.humidity, f.windKph, f.windDir, f.condition
                )
            )
        }
    }
}

fun main() {
    val app = App()
    app.run()
}
