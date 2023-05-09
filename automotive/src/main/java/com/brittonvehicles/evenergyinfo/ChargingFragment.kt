package com.brittonvehicles.evenergyinfo

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekndev.gaugelibrary.ArcGauge
import com.ekndev.gaugelibrary.Range
import com.ekndev.gaugelibrary.contract.ValueFormatter
import android.car.Car
import android.car.CarInfoManager
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.content.ComponentName
import android.os.IBinder
import com.brittonvehicles.evenergyinfo.databinding.ActivityMainBinding
import com.brittonvehicles.evenergyinfo.databinding.FragmentChargingBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "ChargingFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [ChargingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChargingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var chargeGauge: ArcGauge
    private lateinit var batteryVoltage: ArcGauge
    private lateinit var batteryTemp: ArcGauge

    /** Car API. */
    private lateinit var car: Car

    /**
     * An API to read VHAL (vehicle hardware access layer) properties. List of vehicle properties
     * can be found in {@link VehiclePropertyIds}.
     *
     * <p>https://developer.android.com/reference/android/car/hardware/property/CarPropertyManager
     */
    private lateinit var carPropertyManager: CarPropertyManager

    private var _binding: FragmentChargingBinding? = null
    private val binding get() = _binding!!

    private var carPropertyListener = object : CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(value: CarPropertyValue<Any>) {
            Log.d(TAG, "Received on changed car property event")
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
            Log.w(TAG, "Received error car property event, propId=$propId")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

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
    }

    private fun configureChargeGauge(){
        val range = Range()
        range.color = Color.RED
        range.from = 0.0
        range.to = 20.0

        val range2 = Range()
        range2.color = Color.GREEN
        range2.from = 20.0
        range2.to = 80.0

        val range3 = Range()
        range3.color = Color.BLUE
        range3.from = 80.0
        range3.to = 100.0

        //add color ranges to gauge
        chargeGauge.addRange(range)
        chargeGauge.addRange(range2)
        chargeGauge.addRange(range3)

        //set min max and current value
        chargeGauge.minValue = 0.0
        chargeGauge.maxValue = 100.0
        chargeGauge.value = 70.0
        chargeGauge.label = "%"

        chargeGauge.setIcon(
            resources.getDrawable(R.drawable.baseline_battery_charging_full_24, null)
        )

        chargeGauge.icon.setTint(Color.WHITE)

        chargeGauge.valueColor = Color.WHITE
        chargeGauge.labelColor = Color.WHITE
        chargeGauge.setFormatter(ValueFormatter {
            it.toInt().toString()
        })
    }

    private fun configureTempGauge(){
        val range = Range()
        range.color = Color.BLUE
        range.from = 0.0
        range.to = 40.0

        val range2 = Range()
        range2.color = Color.GREEN
        range2.from = 40.0
        range2.to = 90.0

        val range3 = Range()
        range3.color = Color.RED
        range3.from = 90.0
        range3.to = 120.0

        //add color ranges to gauge
        batteryTemp.addRange(range)
        batteryTemp.addRange(range2)
        batteryTemp.addRange(range3)

        //set min max and current value
        batteryTemp.minValue = 0.0
        batteryTemp.maxValue = 120.0
        batteryTemp.value = 72.0
        //There is a Unicode symbol for Celsius degrees that you can use in Java: \u2103. For Fahrenheit you can use \u2109.
        batteryTemp.label = "\u2109"

        batteryTemp.setIcon(
            resources.getDrawable(R.drawable.outline_device_thermostat_24, null)
        )

        batteryTemp.icon.setTint(Color.WHITE)

        batteryTemp.valueColor = Color.WHITE
        batteryTemp.labelColor = Color.WHITE
//        batteryTemp.setFormatter(ValueFormatter {
//            it.toInt().toString()
//        })
    }

    private fun configureVoltageGauge(){

        val range = Range()
        range.color = Color.BLUE
        range.from = 0.0
        range.to = 400.0

        //add color ranges to gauge
        batteryVoltage.addRange(range)

        //set min max and current value
        batteryVoltage.minValue = 0.0
        batteryVoltage.maxValue = 400.0
        batteryVoltage.value = 367.0
        batteryVoltage.label = "Volts"

        batteryVoltage.setIcon(
            resources.getDrawable(R.drawable.outline_electric_bolt_24, null)
        )

        batteryVoltage.icon.setTint(Color.WHITE)

        batteryVoltage.valueColor = Color.WHITE
        batteryVoltage.labelColor = Color.WHITE
        batteryVoltage.setFormatter(ValueFormatter {
            it.toInt().toString()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // inflate the layout and bind to the _binding
        _binding = FragmentChargingBinding.inflate(inflater, container, false)

        chargeGauge = binding.chargeGauge
        batteryTemp = binding.batteryTemp
        batteryVoltage = binding.batteryVoltage
        configureChargeGauge()
        configureTempGauge()
        configureVoltageGauge()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        car.disconnect()
    }
}