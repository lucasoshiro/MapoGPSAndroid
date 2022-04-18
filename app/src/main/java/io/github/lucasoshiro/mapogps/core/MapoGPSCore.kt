package io.github.lucasoshiro.mapogps.core

import org.ejml.simple.SimpleMatrix
import kotlin.math.PI
import kotlin.math.roundToInt
import kotlin.math.sin

typealias Coord = Pair<Double, Double>
typealias MeterPair = Pair<Double, Double>

private const val R = 6371000
private const val LAT_LEN = 10001965.729

private val W = SimpleMatrix(
    arrayOf(
        doubleArrayOf(1.30648032e+01, 1.59610092e+01),
        doubleArrayOf(4.44570272e-06, -3.92767624e-10),
        doubleArrayOf(3.15864474e-06, -3.47287197e-02)
    )
)

private val ORIGIN = -23.55039 to -46.63395

private val VCOORD_PAGE = PAGES
    .map { row ->
        row.map { page ->
            if (page != "   ")
                page.trim().uppercase()
            else
                null
        }
    }

private const val ALPHABET = "ABCDEFHJLMNOPRSTUVXZ"
private const val N_ALPHABET = ALPHABET.length

private fun degreeToRadians(deg: Double) =  deg/180 * PI

private fun coordToMeters(origin: Coord, destination: Coord): MeterPair {
    val meanDegreeLength = (PI * R / 180) *
            (sin(degreeToRadians(destination.first)) -
                    sin(degreeToRadians(origin.first))) /
            (destination.first - origin.first)
    val delta = (origin.first - destination.first) to (origin.second - destination.second)

    return (delta.first * LAT_LEN) to (delta.second * meanDegreeLength)
}

private fun mapoFromVCoord(y: Double, x: Double): Triple<String?, Char, Int> {
    val i = y.toInt()
    val j = x.toInt()

    val restI = y - i
    val restJ = x - j

    val number = (restI * 30.0 + 0.5).roundToInt()
    val letter = ALPHABET[(restJ * N_ALPHABET - 0.5).roundToInt()]
    return Triple(VCOORD_PAGE[i][j], letter, number)
}


fun mapoFromCoord(y: Double, x: Double): Triple<String?, Char, Int> {
    val realDelta = coordToMeters(ORIGIN, y to x)
    val r = SimpleMatrix(
        arrayOf(
            doubleArrayOf(1.0, realDelta.first, realDelta.second)
        )
    )
    val v = r.mult(W).let { it.get(0, 0) to it.get(0, 1) }
    return mapoFromVCoord(v.first, v.second)
}