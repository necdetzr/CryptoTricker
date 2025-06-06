package com.necdetzr.loodoscrypto.utils

object FormatFunctions {
    fun formatChange(change: Double): String {
        return if (change >= 0) {
            "+%.2f%%".format(change)
        } else {
            "%.2f%%".format(change)
        }
    }


}