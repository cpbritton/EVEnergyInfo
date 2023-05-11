package com.brittonvehicles.evenergyinfo

import android.car.Car
import android.car.CarInfoManager
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.brittonvehicles.evenergyinfo.databinding.GearSelectorViewBinding


class MainFragment : Fragment() {

    companion object {
        private const val TAG = "MainFragment"

    }

    private lateinit var car: Car
    private lateinit var carPropertyManager: CarPropertyManager
    private lateinit var gsBinding: GearSelectorViewBinding

    private var carPropertyListener = object : CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(value: CarPropertyValue<Any>) {
            gsBinding.textN.isEnabled = false
            gsBinding.textR.isEnabled = false
            gsBinding.textP.isEnabled = false
            gsBinding.textD.isEnabled = false

            when (value.value as Int ) {
                1 -> gsBinding.textN.isEnabled = true
                2 -> gsBinding.textR.isEnabled = true
                4 -> gsBinding.textP.isEnabled = true
                8 -> gsBinding.textD.isEnabled = true
                else -> {

                }
            }
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
            Log.w(TAG, "Received error car property event, propId=$propId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize view
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)

        // Add the GearSelectorView
        gsBinding = GearSelectorViewBinding.inflate(layoutInflater)
        val gearSelectorView = gsBinding.root
        val main = view.findViewById(R.id.gear_selector_point) as ViewGroup
        main.addView(gearSelectorView, 0)

        car = Car.createCar(this.context)
        carPropertyManager = car.getCarManager(Car.PROPERTY_SERVICE) as CarPropertyManager;

        @Suppress("DEPRECATION")
        val carInfo = car.getCarManager(Car.INFO_SERVICE) as CarInfoManager;

        // Subscribes to the gear change events.
        carPropertyManager.registerCallback(
            carPropertyListener,
            VehiclePropertyIds.CURRENT_GEAR,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
        // return view
        return view
    }
}