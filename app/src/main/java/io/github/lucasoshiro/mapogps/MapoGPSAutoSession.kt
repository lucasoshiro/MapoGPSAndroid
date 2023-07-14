package io.github.lucasoshiro.mapogps

import android.content.Intent
import androidx.car.app.Screen
import androidx.car.app.Session

class MapoGPSAutoSession : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        return MapoGPSAutoScreen(carContext)
    }

}