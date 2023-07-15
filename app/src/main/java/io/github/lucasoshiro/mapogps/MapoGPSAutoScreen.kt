package io.github.lucasoshiro.mapogps

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.*
import io.github.lucasoshiro.mapogps.core.Coord
import io.github.lucasoshiro.mapogps.core.mapoFromCoord

class MapoGPSAutoScreen(
    carContext: CarContext,
    private val coord: Coord?
) : Screen(carContext) {
    override fun onGetTemplate(): Template {
        val displayStr = coord?.let {
            val (page, letter, number) = mapoFromCoord(it.first, it.second)
            return@let "$page\n$letter $number"
        } ?: ""

        val row = Row.Builder().setTitle("  ").addText(displayStr).build()
        val pane = Pane.Builder().addRow(row).build()

        return PaneTemplate.Builder(pane)
            .setHeaderAction(Action.APP_ICON)
            .build()
    }
}