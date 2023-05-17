package com.brittonvehicles.evenergyinfo.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EnergyInfoSharedModel : ViewModel() {

//        field public static final int INFO_EV_BATTERY_CAPACITY = 291504390; // 0x11600106
//        field public static final int INFO_EV_CONNECTOR_TYPE = 289472775; // 0x11410107
//        field public static final int INFO_EV_PORT_LOCATION = 289407241; // 0x11400109
//        field public static final int INFO_FUEL_TYPE = 289472773; // 0x11410105
 //   INFO_MULTI_EV_PORT_LOCATIONS
    //EV_BATTERY_LEVEL
    // EV_CHARGE_PORT_OPEN
    // EV_CHARGE_PORT_CONNECTED
    // EV_BATTERY_INSTANTANEOUS_CHARGE_RATE
    // RANGE_REMAINING

    // range
    // battery remaining

    private val _batteryLevelPercentage = MutableLiveData<Float>()
    fun setBatteryLevelPercentage(value: Float) {
        _batteryLevelPercentage.value = value
    }

    val batteryLevelPercentage: MutableLiveData<Float>
        get() = _batteryLevelPercentage
}