package helpers

data class WeatherResponse(
    val forecast: ForecastWrapper,
    val current: Current
)

data class ForecastWrapper(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day
)

data class Day(
    val maxtemp_c: Float,
    val mintemp_c: Float,
    val avghumidity: Int,
    val maxwind_kph: Float,
    val condition: Condition
)

data class Condition(
    val text: String
)

data class Current(
    val wind_dir: String
)