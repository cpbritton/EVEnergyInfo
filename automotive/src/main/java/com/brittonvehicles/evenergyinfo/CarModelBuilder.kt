package com.brittonvehicles.evenergyinfo

import android.car.Car
import android.car.VehicleAreaType
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.content.Context
import android.util.Log
import androidx.car.app.hardware.common.CarUnit
import androidx.car.app.model.Distance
import com.brittonvehicles.evenergyinfo.models.VehicleInfoSharedModel

class CarModelBuilder {


    private val TAG: String? = "CarModelBuilder"
    private lateinit var car: Car
    private lateinit var carPropertyManager: CarPropertyManager

    fun init(context :Context) {
        car = Car.createCar(context)
        carPropertyManager = car.getCarManager(Car.PROPERTY_SERVICE) as CarPropertyManager;
        setupListeners()
    }

    private fun setupListeners(){
        carPropertyManager.registerCallback(
            carPropertyListener,
            VehiclePropertyIds.CURRENT_GEAR,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
        carPropertyManager.registerCallback(
            carPropertyListener,
            VehiclePropertyIds.INFO_MODEL,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
        carPropertyManager.registerCallback(
            carPropertyListener,
            VehiclePropertyIds.INFO_MAKE,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
        carPropertyManager.registerCallback(
            carPropertyListener,
            VehiclePropertyIds.INFO_FUEL_TYPE,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    private fun setupCarTest(){
//        "timestamp": 1526063903360950016,
//        "areaId": 0,
//        "value": "Test Car",
//        "prop":
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

        try {
            carPropertyManager.setProperty( String.javaClass , VehiclePropertyIds.INFO_MAKE, VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL , "Ginetta")
        }catch (exception : Exception){
            Log.w(TAG, "Received error setting CarProperty INFO_MAKE")
        }

        val remaining = Distance.create(200.0, Distance.UNIT_MILES)
        Log.w(TAG, CarUnit.CarSpeedUnit().toString())
    }


    private var carPropertyListener = object : CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(prop: CarPropertyValue<Any>) {
            val id = prop.propertyId
            val value : String = if (prop.value is Array<*>){
                (prop.value as Array<*>).contentToString()
            }else{
                prop.value.toString()
            }
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
        }
    }



}