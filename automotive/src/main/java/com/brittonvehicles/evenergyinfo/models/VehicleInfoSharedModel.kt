package com.brittonvehicles.evenergyinfo.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VehicleInfoSharedModel : ViewModel() {

//        field public static final int INFO_MAKE = 286261505; // 0x11100101
//        field public static final int INFO_MODEL = 286261506; // 0x11100102
//        field public static final int INFO_MODEL_YEAR = 289407235; // 0x11400103
//        field public static final int INFO_VIN = 286261504; // 0x11100100

    private val _vin = MutableLiveData<String>()
    private val _make = MutableLiveData<String>()
    private val _model = MutableLiveData<String>()
    private val _modelYear = MutableLiveData<Int>()

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

}
