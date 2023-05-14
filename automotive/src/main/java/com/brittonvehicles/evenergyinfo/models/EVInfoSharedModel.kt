package com.brittonvehicles.evenergyinfo.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EVInfoSharedModel : ViewModel() {
    /**
     * Live Data Instance
     */

    //        field public static final int INFO_EV_BATTERY_CAPACITY = 291504390; // 0x11600106
//        field public static final int INFO_EV_CONNECTOR_TYPE = 289472775; // 0x11410107
//        field public static final int INFO_EV_PORT_LOCATION = 289407241; // 0x11400109
//        field public static final int INFO_FUEL_CAPACITY = 291504388; // 0x11600104
//        field public static final int INFO_FUEL_DOOR_LOCATION = 289407240; // 0x11400108
//        field public static final int INFO_FUEL_TYPE = 289472773; // 0x11410105
//        field public static final int INFO_MAKE = 286261505; // 0x11100101
//        field public static final int INFO_MODEL = 286261506; // 0x11100102
//        field public static final int INFO_MODEL_YEAR = 289407235; // 0x11400103
//        field public static final int INFO_VIN = 286261504; // 0x11100100
//        field public static final int INVALID = 0; // 0x0


    private val _currentGear = MutableLiveData<Int>()
    fun setCurrentGear(gear: Int) {
        _currentGear.value = gear
    }

    val currentGear: MutableLiveData<Int>
        get() = _currentGear
}