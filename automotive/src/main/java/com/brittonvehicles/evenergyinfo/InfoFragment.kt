package com.brittonvehicles.evenergyinfo

import android.car.Car
import android.car.CarInfoManager
import android.car.VehicleAreaType
import android.car.VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.brittonvehicles.evenergyinfo.databinding.FragmentInfoBinding
import com.brittonvehicles.evenergyinfo.databinding.FragmentStatsBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private var _binding: FragmentInfoBinding? = null
private val binding get() = _binding!!

/**
 * A simple [Fragment] subclass.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var car: Car
    private lateinit var carPropertyManager: CarPropertyManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        car = Car.createCar(this.context)
        carPropertyManager = car.getCarManager(Car.PROPERTY_SERVICE) as CarPropertyManager;
        setupListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)

        setupCarTest()
        @Suppress("DEPRECATION")
        val carInfo = car.getCarManager(Car.INFO_SERVICE) as CarInfoManager;

        @Suppress("DEPRECATION")
        binding.carMakeTextView.text = carInfo.manufacturer
        @Suppress("DEPRECATION")
        binding.carModelTextView.text = carInfo.model

        return binding.root
    }

    companion object {

        private const val TAG = "InfoFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
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

    }


    private var carPropertyListener = object : CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(prop: CarPropertyValue<Any>) {
            val id = prop.propertyId
            val value : String = if (prop.value is Array<*>){
                (prop.value as Array<*>).contentToString()
            }else{
                prop.value.toString()
            }
            binding.carLog.append(" $id : $value  \n")
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
        }
    }
}