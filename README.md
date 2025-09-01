# WeatherAPI.com Code Challenge

## Challenge Description
Using the [WeatherAPI.com](https://www.weatherapi.com/) API, this app retrieves a **forecast for the next day** for the following cities:
- Chisinau
- Madrid
- Kyiv
- Amsterdam

The data is then printed to **STDOUT** in a nicely formatted table.

---

## Requirements (from challenge)
- The app should be written in **Java** (bonus: implemented in **Kotlin**).
- The code should be tracked with **git** and pushed to GitHub or Bitbucket.
- The data for the next day's forecast should be outputted to **STDOUT**.
- The output should be formatted as a **table with the cities as rows**.
- Each row shows:
  - Minimum Temperature (°C)
  - Maximum Temperature (°C)
  - Humidity (%)
  - Wind Speed (kph)
  - Wind Direction
  - Condition description (extra field included)

---

## Bonus Points Implemented
Written in **Kotlin**  
Built with **Gradle**  
Uses **Retrofit** for API interaction  
Uses **Kotlinx DateTime** for working with dates  
Uses **Kotlinx Coroutines** to support asynchronous API calls  

---

## How to Use WeatherAPI.com API
1. **Create an Account**: Sign up at [WeatherAPI.com](https://www.weatherapi.com/).  
2. **Obtain an API Key**: Copy your API key from your dashboard.  
3. **Update the Key in Code**: Replace the placeholder `apiKey` in `App.kt` with your own.  

---

## Project Structure
src/
└── main/
└── kotlin/
├── App.kt # Application entry point
└── helpers/
├── DTOs.kt # Data models for API responses
└── API.kt # Retrofit API + client setup



---------------------------------------



## Build & Run
Make sure you have **Java 21+** and **Gradle** installed.

```bash
# Clone the repo
git clone https://github.com/<your-username>/WeatherAPI-Challenge.git
cd WeatherAPI-Challenge

# Build
gradle build

# Run
gradle run
```


---------------------------------------


OUTPUT EXAMPLE:

City         | Date         | Min (C)   | Max (C)   | Humidity (%) | Wind (kph) | Wind Dir | Condition   
---------------------------------------------------------------------------------------------------------
Chisinau     | 2025-09-02   | 17.3      | 29.4      | 45           | 23.1       | NW       | Sunny       
Madrid       | 2025-09-02   | 19.2      | 31.5      | 30           | 18.6       | E        | Clear       
Kyiv         | 2025-09-02   | 14.8      | 25.2      | 55           | 15.7       | W        | Partly cloudy
Amsterdam    | 2025-09-02   | 13.0      | 22.8      | 65           | 20.4       | SW       | Cloudy      
