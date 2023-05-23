package com.brittonvehicles.evenergyinfo

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.brittonvehicles.evenergyinfo.databinding.FragmentChargingBinding
import com.brittonvehicles.evenergyinfo.models.VehicleInfoSharedModel
import com.ekndev.gaugelibrary.ArcGauge
import com.ekndev.gaugelibrary.Range
import com.ekndev.gaugelibrary.contract.ValueFormatter

private const val TAG = "ChargingFragment"

/**
 * Fragment to display vehicle charging information
 */
class ChargingFragment : Fragment() {

    private lateinit var chargeGauge: ArcGauge
    private lateinit var batteryVoltage: ArcGauge
    private lateinit var batteryTemp: ArcGauge

    private val vehicleInfoSharedModel by activityViewModels<VehicleInfoSharedModel>()

    private lateinit var binding:  FragmentChargingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        range.color = Color.RED
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
        binding = FragmentChargingBinding.inflate(inflater, container, false)

        chargeGauge = binding.chargeGauge
        batteryTemp = binding.batteryTemp
        batteryVoltage = binding.batteryVoltage
        configureChargeGauge()
        configureTempGauge()
        configureVoltageGauge()

        vehicleInfoSharedModel.batteryLevel.observe(viewLifecycleOwner, Observer<Float> { item ->
            chargeGauge.value = vehicleInfoSharedModel.batterLevelPercentage!!.toDouble()
        })


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}