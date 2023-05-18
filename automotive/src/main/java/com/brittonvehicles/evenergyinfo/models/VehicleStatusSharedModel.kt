package com.brittonvehicles.evenergyinfo.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brittonvehicles.evenergyinfo.formatCarUnitDistance
import com.brittonvehicles.evenergyinfo.formatCarUnitDistanceFromKilometers
import com.brittonvehicles.evenergyinfo.getDefaultDistanceUnit

class VehicleStatusSharedModel : ViewModel() {

// PERF_ODOMETER
    //PERF_VEHICLE_SPEED
  //  PERF_VEHICLE_SPEED_DISPLAY


    // PERF_ODOMETER
    private val _odometer = MutableLiveData<Float>()

    fun setOdometer(value: Float){
        _odometer.value = value
    }
    val odometer: MutableLiveData<Float>
        get() = _odometer
    val odometerFormatted:MutableLiveData<String>
        get() = MutableLiveData<String>(formatCarUnitDistanceFromKilometers(_odometer.value , getDefaultDistanceUnit()))

    private val _currentGear = MutableLiveData<Int>()
    fun setCurrentGear(value: Int) {
        _currentGear.value = value
    }

    val currentGear: MutableLiveData<Int>
        get() = _currentGear
}