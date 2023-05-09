package com.brittonvehicles.evenergyinfo

import android.car.Car
import android.car.CarInfoManager
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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

    private var carPropertyListener = object : CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(value: CarPropertyValue<Any>) {

        }

        override fun onErrorEvent(propId: Int, zone: Int) {
           // Log.w(TAG, "Received error car property event, propId=$propId")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        car = Car.createCar(this.context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view :View = inflater.inflate(R.layout.fragment_info, container, false)

        @Suppress("DEPRECATION")
        val carInfo = car.getCarManager(Car.INFO_SERVICE) as CarInfoManager;

        var makeTV =  view?.findViewById(R.id.carMakeTextView) as TextView
        @Suppress("DEPRECATION")
        makeTV.text = carInfo.manufacturer
        var modelTV =  view?.findViewById(R.id.carModelTextView) as TextView
        @Suppress("DEPRECATION")
        modelTV.text = carInfo.model

        return view
    }

    companion object {
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
}