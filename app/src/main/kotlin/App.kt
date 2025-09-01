import okhttp3.OkHttpClient
import okhttp3.Request
import com.google.gson.JsonParser
import kotlinx.datetime.*

data class Forecast(
    val date: String,
    val maxTemp: Float,
    val minTemp: Float,
    val avgTemp: Float,
    val humidity: Int,
    val windKph: Float,
    val precipMm: Float,
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
                            avgTemp = day["avgtemp_c"].asFloat,
                            humidity = day["avghumidity"].asInt,
                            windKph = day["maxwind_kph"].asFloat,
                            precipMm = day["totalprecip_mm"].asFloat,
                            condition = day["condition"].asJsonObject["text"].asString
                        )

                        placeHolder[city] = forecast
                    }
                }
            }
        }

        placeHolder.forEach { (city, forecast) ->
            println("==== $city ====")
            println("Date: ${forecast.date}")
            println("Condition: ${forecast.condition}")
            println("Temp: ${forecast.minTemp}\u00B0C .. ${forecast.maxTemp}\u00B0C (avg ${forecast.avgTemp}\u00B0C)")
            println("Humidity: ${forecast.humidity}%")
            println("Wind: ${forecast.windKph} kph")
            println("Precipitation: ${forecast.precipMm} mm")
            println()
        }
    }
}

fun main() {
    val app = App()
    app.run()
}
