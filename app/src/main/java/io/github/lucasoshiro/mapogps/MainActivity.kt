package io.github.lucasoshiro.mapogps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.birjuvachhani.locus.Locus
import io.github.lucasoshiro.mapogps.core.Coord
import io.github.lucasoshiro.mapogps.core.mapoFromCoord

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Locus.startLocationUpdates(this) { result ->
            result.location?.let {
                this.updateWidgets(it.latitude to it.longitude)
            }
        }
    }

    private fun updateWidgets(coord: Coord) {
        val pageTextView: TextView = findViewById(R.id.page)
        val pageCoordinatesTextView: TextView = findViewById(R.id.pageCoordinates)

        val (page, letter, number) = mapoFromCoord(coord.first, coord.second)

        Log.println(Log.INFO, "mapoGPS", "$coord")

        pageTextView.text = page
        pageCoordinatesTextView.text = "$letter $number"
    }
}