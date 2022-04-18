package io.github.lucasoshiro.mapogps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import io.github.lucasoshiro.mapogps.core.Coord
import io.github.lucasoshiro.mapogps.core.mapoFromCoord

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun updateWidgets() {
        val pageTextView: TextView = findViewById(R.id.page)
        val pageCoordinatesTextView: TextView = findViewById(R.id.pageCoordinates)

        pageTextView.text = page
        pageCoordinatesTextView.text = "$letter $number"
    }

    companion object {
        var coordinates: Coord = -23.5 to -46.6
        var mapoCoordinates = mapoFromCoord(coordinates.first, coordinates.second)
        var page = mapoCoordinates.first
        var letter = mapoCoordinates.second
        var number = mapoCoordinates.third
    }
}