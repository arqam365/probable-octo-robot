package com.revzion.cognivia.core.utils

fun formatMoney(str: String): String {
    if (str.length <= 3) return str

    val lastThree = str.takeLast(3)
    val remaining = str.dropLast(3)

    val sb = StringBuilder()
    var count = 0

    for (i in remaining.length - 1 downTo 0) {
        sb.append(remaining[i])
        count++
        if (count % 2 == 0 && i != 0) {
            sb.append(',')
        }
    }

    return sb.reverse().append(',').append(lastThree).toString()
}