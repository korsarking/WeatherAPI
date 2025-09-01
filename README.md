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


<img width="1043" height="207" alt="изображение" src="https://github.com/user-attachments/assets/ee946e6b-f51a-4f6d-bd34-b207c16823a1" />
  
