package com.testing.weatherapp.util

object InterpretationValues {

    fun windDirection(value: String): String = when(value) {
            "nw" -> "Северо-западное"
            "n" -> "Северное"
            "ne" -> "Северо-восточное"
            "e" -> "Восточное"
            "se" -> "Юго-восточное"
            "s" -> "Южное"
            "sw" -> "Юго-западное"
            "w" -> "Западное"
            "c" -> "Штиль"
        else -> "Северо-западное"
    }

    fun weatherCondition(value: String): String = when(value) {
        "clear" -> "Ясно"
        "partly-cloudy" -> "Малооблачно"
        "cloudy" -> "Облачно с прояснениями"
        "overcast" -> "Пасмурно"
        "drizzle" -> "Морось"
        "light-rain" -> "Небольшой дождь"
        "rain" -> "Дождь"
        "moderate-rain" -> "Умеренно сильный дождь"
        "heavy-rain" -> "Сильный дождь"
        "continuous-heavy-rain" -> "Длительный сильный дождь"
        "showers" -> "Ливень"
        "wet-snow" -> "Дождь со снегом"
        "light-snow" -> "Небольшой снег"
        "snow" -> "Снег"
        "snow-showers" -> "Снегопад"
        "hail" -> "Град"
        "thunderstorm" -> "Гроза"
        "thunderstorm-with-rain" -> "Дождь с грозой"
        "thunderstorm-with-hail" -> "Гроза с градом"
        else -> "Ясно"
    }
}