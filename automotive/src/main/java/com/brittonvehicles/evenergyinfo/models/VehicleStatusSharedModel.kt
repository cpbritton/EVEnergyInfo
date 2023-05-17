package com.brittonvehicles.evenergyinfo.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VehicleStatusSharedModel : ViewModel() {

// PERF_ODOMETER
    //PERF_VEHICLE_SPEED
  //  PERF_VEHICLE_SPEED_DISPLAY



    private val _currentGear = MutableLiveData<Int>()
    fun setCurrentGear(gear: Int) {
        _currentGear.value = gear
    }

    val currentGear: MutableLiveData<Int>
        get() = _currentGear
}