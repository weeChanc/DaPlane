package com.example.daplane.utils

import android.util.Log


typealias Interpolator = (input: Long) -> Float

// 抛物线

fun parabolaGen(
    start: Float = 0F,
    end: Float = start + 120F,
    top: Float = -240F,
    offset: Float
): Interpolator {
    val x = (end - start) / 2
    val a = top / ((x * x) - (end * x) - (start * x) + (start * end))
    return { input: Long ->
        a * (input - start) * (input - end) + offset
    }
}