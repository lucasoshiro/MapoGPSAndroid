package io.github.lucasoshiro.mapogps

import android.util.Log
import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator

class MapoGPSAutoService : CarAppService() {
    override fun createHostValidator(): HostValidator {
        Log.println(Log.DEBUG, "CarAppService", "CarAppService called!")
        return HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
    }

    override fun onCreateSession(): Session {
        return MapoGPSAutoSession()
    }
}