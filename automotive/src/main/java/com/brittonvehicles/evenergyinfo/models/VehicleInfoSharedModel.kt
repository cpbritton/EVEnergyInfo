package com.brittonvehicles.evenergyinfo.models

import android.car.VehicleUnit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brittonvehicles.evenergyinfo.formatCarUnitDistanceFromKilometers
import com.brittonvehicles.evenergyinfo.formatCarUnitDistanceFromMeters

class VehicleInfoSharedModel : ViewModel() {

//        field public static final int INFO_MAKE = 286261505; // 0x11100101
//        field public static final int INFO_MODEL = 286261506; // 0x11100102
//        field public static final int INFO_MODEL_YEAR = 289407235; // 0x11400103
//        field public static final int INFO_VIN = 286261504; // 0x11100100

    private val _vin = MutableLiveData<String>()
    private val _make = MutableLiveData<String>()
    private val _model = MutableLiveData<String>()
    private val _modelYear = MutableLiveData<Int>()
    private val _distanceDisplayUnit = MutableLiveData<Int>(VehicleUnit.MILE)
    private val _evDisplayUnit = MutableLiveData<Int>(VehicleUnit.KILOWATT_HOUR)
    private val _batteryCapacity = MutableLiveData<Float>()
    private val _connectorType = MutableLiveData<Int>()
    private val _portLocation = MutableLiveData<Int>()
    private val _chargePortOpen = MutableLiveData<Boolean>()
    private val _chargePortConnected = MutableLiveData<Boolean>()
    private val _instantaneousChargeRate = MutableLiveData<Float>()
    private val _batteryLevel = MutableLiveData<Float>()
    private val _remainingRange = MutableLiveData<Float>()
    fun setDistanceDisplayUnit(value:Int){
        _distanceDisplayUnit.value = value
    }
    val distanceDisplayUnit : MutableLiveData<Int>
        get()= _distanceDisplayUnit

    fun setEvDisplayUnit(value:Int){
        _evDisplayUnit.value = value
    }
    val evDisplayUnit:MutableLiveData<Int>
        get() = _evDisplayUnit

    fun setVin(value: String) {
        _vin.value = value
    }

    val vin: MutableLiveData<String>
        get() = _vin

    fun setMake(value: String){
        _make.value = value
    }

    val make: MutableLiveData<String>
        get() = _make

    fun setModel(value: String){
        _model.value = value
    }

    val model: MutableLiveData<String>
        get() = _model

    fun setModelYear(value: Int){
        _modelYear.value = value
    }

    val modelYear: MutableLiveData<Int>
        get() = _modelYear

    // PERF_ODOMETER
    private val _odometer = MutableLiveData<Float>()

    fun setOdometer(value: Float){
        _odometer.value = value
    }
    val odometer: MutableLiveData<Float>
        get() = _odometer
    val odometerFormatted:MutableLiveData<String>
        get() = MutableLiveData<String>(formatCarUnitDistanceFromKilometers(_odometer.value , _distanceDisplayUnit.value ))

    private val _currentGear = MutableLiveData<Int>()
    fun setCurrentGear(value: Int) {
        _currentGear.value = value
    }

    val currentGear: MutableLiveData<Int>
        get() = _currentGear



    fun setRemainingRange(value: Float){
        _remainingRange.value = value
    }
    val remainingRange:MutableLiveData<Float>
        get() = _remainingRange
    val remainingRangeFormatted:MutableLiveData<String>
        get() = MutableLiveData<String>(formatCarUnitDistanceFromMeters(_remainingRange.value,  _distanceDisplayUnit.value))
    fun setBatteryLevel(value: Float) {
        _batteryLevel.value = value
    }

    val batteryLevel: MutableLiveData<Float>
        get() = _batteryLevel

    val batterLevelPercentage: Float?
        get() = 100 * _batteryLevel.value?.div(_batteryCapacity.value!!)!!
    fun setBatterCapacity(value:Float){
        _batteryCapacity.value = value
    }

    val batteryCapacity:MutableLiveData<Float>
        get()= _batteryCapacity

}
