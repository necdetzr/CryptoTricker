package com.example.ui.util

import java.util.Locale

object FormatFunctions {

    fun formatChange(change: Double): String {
        return if (change >= 0) {
            "+%.2f%%".format(change)
        } else {
            "%.2f%%".format(change)
        }
    }

    fun formatAnyNumber(value: Any?): String {
        return when (value) {
            is Double -> String.format(Locale.US, "%,.2f", value)
            is Float -> String.format(Locale.US, "%,.2f", value)
            is Int -> String.format(Locale.US, "%,d", value)
            is Long -> String.format(Locale.US, "%,d", value)
            null -> "N/A"
            else -> value.toString()
        }
    }
}