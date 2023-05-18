package com.brittonvehicles.evenergyinfo

import androidx.car.app.hardware.common.CarUnit
import androidx.car.app.model.*
import java.util.*
import kotlin.math.roundToInt

private const val kmPerMile = 1.609344
private const val ftPerMile = 5280
private const val ydPerMile = 1760

fun getDefaultDistanceUnit(): Int {
    return if (usesImperialUnits(Locale.getDefault())) {
        CarUnit.MILE
    } else {
        CarUnit.KILOMETER
    }
}

fun usesImperialUnits(locale: Locale): Boolean {
    return locale.country in listOf("US", "GB", "MM", "LR")
            || locale.country == "" && locale.language == "en"
}

fun getDefaultSpeedUnit(): Int {
    return when (Locale.getDefault().country) {
        "US", "GB", "MM", "LR" -> CarUnit.MILES_PER_HOUR
        else -> CarUnit.KILOMETERS_PER_HOUR
    }
}

fun formatCarUnitDistanceFromKilometers(value: Float?): String {
    if (value == null) return ""
    return formatCarUnitDistance(value *1000 , getDefaultDistanceUnit())
}
fun formatCarUnitDistance(value: Float?, unit: Int?): String {
    if (value == null) return ""
    return when (unit ?: getDefaultDistanceUnit()) {
        // distance units: base unit is meters
        CarUnit.METER -> "%.0f m".format(value)
        CarUnit.KILOMETER -> "%.1f km".format(value / 1000)
        CarUnit.MILLIMETER -> "%.0f mm".format(value * 1000) // whoever uses that...
        CarUnit.MILE -> "%.1f miles".format(value / 1000 / kmPerMile)
        else -> ""
    }
}

fun formatCarUnitSpeed(value: Float?, unit: Int?): String {
    if (value == null) return ""
    return when (unit ?: getDefaultSpeedUnit()) {
        // speed units: base unit is meters per second
        CarUnit.METERS_PER_SEC -> "%.0f m/s".format(value)
        CarUnit.KILOMETERS_PER_HOUR -> "%.0f km/h".format(value * 3.6)
        CarUnit.MILES_PER_HOUR -> "%.0f mph".format(value * 3.6 / kmPerMile)
        else -> ""
    }
}

fun roundValueToDistance(value: Double, unit: Int? = null): Distance {
    // value is in meters
    when (unit ?: getDefaultDistanceUnit()) {
        CarUnit.MILE -> {
            // imperial system
            val miles = value / 1000 / kmPerMile
            val yards = miles * ydPerMile
            val feet = miles * ftPerMile

            return when (miles) {
                in 0.0..0.1 -> if (Locale.getDefault().country == "UK") {
                    Distance.create(roundToMultipleOf(yards, 10.0), Distance.UNIT_YARDS)
                } else {
                    Distance.create(roundToMultipleOf(feet, 10.0), Distance.UNIT_FEET)
                }
                in 0.1..10.0 -> Distance.create(
                    roundToMultipleOf(miles, 0.1),
                    Distance.UNIT_MILES_P1
                )
                else -> Distance.create(roundToMultipleOf(miles, 1.0), Distance.UNIT_MILES)
            }
        }
        else -> {
            // metric system
            return when (value) {
                in 0.0..999.0 -> Distance.create(
                    roundToMultipleOf(value, 10.0),
                    Distance.UNIT_METERS
                )
                in 1000.0..10000.0 -> Distance.create(
                    roundToMultipleOf(value / 1000, 0.1),
                    Distance.UNIT_KILOMETERS_P1
                )
                else -> Distance.create(
                    roundToMultipleOf(value / 1000, 1.0),
                    Distance.UNIT_KILOMETERS
                )
            }
        }
    }
}

private fun roundToMultipleOf(num: Double, step: Double): Double {
    return (num / step).roundToInt() * step
}
