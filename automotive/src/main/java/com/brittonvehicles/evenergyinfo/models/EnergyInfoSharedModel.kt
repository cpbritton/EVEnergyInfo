package com.brittonvehicles.evenergyinfo.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brittonvehicles.evenergyinfo.formatCarUnitDistance
import com.brittonvehicles.evenergyinfo.getDefaultDistanceUnit

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

    private val _batteryCapacity = MutableLiveData<Float>()
    private val _connectorType = MutableLiveData<Int>()
    private val _portLocation = MutableLiveData<Int>()
    private val _chargePortOpen = MutableLiveData<Boolean>()
    private val _chargePortConnected = MutableLiveData<Boolean>()
    private val _instantaneousChargeRate = MutableLiveData<Float>()
    private val _batteryLevelPercentage = MutableLiveData<Float>()
    private val _remainingRange = MutableLiveData<Float>()

    fun setRemainingRange(value: Float){
        _remainingRange.value = value
    }
    val remainingRange:MutableLiveData<Float>
        get() = _remainingRange
    val remainingRangeFormatted:MutableLiveData<String>
        get() = MutableLiveData<String>(formatCarUnitDistance(_remainingRange.value,  getDefaultDistanceUnit()))
    fun setBatteryLevelPercentage(value: Float) {
        _batteryLevelPercentage.value = value
    }

    val batteryLevelPercentage: MutableLiveData<Float>
        get() = _batteryLevelPercentage

    fun setBatterCapacity(value:Float){
        _batteryCapacity.value = value
    }

    val batteryCapacity:MutableLiveData<Float>
        get()= _batteryCapacity


}