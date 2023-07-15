package io.github.lucasoshiro.mapogps

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.ScreenManager
import androidx.car.app.Session
import androidx.lifecycle.LifecycleOwner
import com.birjuvachhani.locus.Locus

class MapoGPSAutoSession : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        val screen = MapoGPSAutoScreen(carContext, null)
        val liveData = Locus.startLocationUpdates(carContext)

        liveData.observeForever { result ->
            result.location?.let {
                val screenManager = carContext.getCarService(CarContext.SCREEN_SERVICE) as ScreenManager
                if (screenManager.stackSize > 0) {
                    screenManager.top.invalidate()

                    screenManager.push(
                        MapoGPSAutoScreen(carContext, it.latitude to it.longitude)
                    )
                }
            }
        }
        return screen
    }
}