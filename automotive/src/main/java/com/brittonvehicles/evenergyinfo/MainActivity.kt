package com.brittonvehicles.evenergyinfo

import android.car.Car
import android.car.VehicleAreaType
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.graphics.Color

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.car.app.hardware.common.CarUnit
import androidx.car.app.model.Distance.*
import com.brittonvehicles.evenergyinfo.databinding.ActivityMainBinding
import com.brittonvehicles.evenergyinfo.models.EnergyInfoSharedModel
import com.brittonvehicles.evenergyinfo.models.VehicleInfoSharedModel
import com.brittonvehicles.evenergyinfo.models.VehicleStatusSharedModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private val tabsArray = arrayOf( "Home", "Charging","Stats", "Info")

    private lateinit var binding: ActivityMainBinding
    private lateinit var carModelBuilder :CarModelBuilder
    private lateinit var car: Car
    private lateinit var carPropertyManager: CarPropertyManager

    val vehicleInfoSharedModel by viewModels<VehicleInfoSharedModel>()
    val energyInfoSharedModel by viewModels<EnergyInfoSharedModel>()
    val vehicleStatusSharedModel by viewModels<VehicleStatusSharedModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

       TabLayoutMediator(tabLayout, viewPager) { tab, position ->
           when (position) {
               0 -> {
                   tab.text = tabsArray[position]
                   tab.setIcon( R.drawable.ic_tab0)
                   tab.icon?.setTint(Color.WHITE)
               }
               1 -> {
                   tab.text = tabsArray[position]
                   tab.setIcon( R.drawable.ic_tab1)
                   tab.icon?.setTint(Color.GRAY)
               }
               2 -> {
                   tab.text = tabsArray[position]
                   tab.setIcon( R.drawable.ic_tab2)
                   tab.icon?.setTint(Color.GRAY)
               }
               3 -> {
                   tab.text = tabsArray[position]
                   tab.setIcon( R.drawable.ic_tab2)
                   tab.icon?.setTint(Color.GRAY)
               }
           }
       }.attach()

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tabLayout.getTabAt(0)?.icon?.setTint(Color.GRAY)
                tabLayout.getTabAt(1)?.icon?.setTint(Color.GRAY)
                tabLayout.getTabAt(2)?.icon?.setTint(Color.GRAY)
                tabLayout.getTabAt(3)?.icon?.setTint(Color.GRAY)
                when (tab.position) {
                    0 -> {
                        tab.icon?.setTint(Color.WHITE)
                    }
                    1 -> {
                        tab.icon?.setTint(Color.WHITE)
                    }
                    2 -> {
                        tab.icon?.setTint(Color.WHITE)
                    }
                    3 -> {
                        tab.icon?.setTint(Color.WHITE)
                   }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        car = Car.createCar(this)
        carPropertyManager = car.getCarManager(Car.PROPERTY_SERVICE) as CarPropertyManager;
        setupListeners()
        setupCarTest()
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
        carPropertyManager.registerCallback(
            carPropertyListener,
            VehiclePropertyIds.EV_BATTERY_LEVEL,
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
            //carPropertyManager.setProperty( String.javaClass , VehiclePropertyIds.INFO_MAKE, VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL , "Ginetta")
            carPropertyManager.setProperty( Float.javaClass , VehiclePropertyIds.RANGE_REMAINING, VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL , 247)
            carPropertyManager.setProperty( Float.javaClass , VehiclePropertyIds.PERF_ODOMETER, VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL , 99999.99)
        }catch (exception : Exception){
            Log.w(Companion.TAG, exception)
        }
        vehicleStatusSharedModel.setOdometer(12323.23F as Float)
        energyInfoSharedModel.setRemainingRange(397508F as Float)
//        val remaining = Distance.create(200.0, Distance.UNIT_KILOMETERS)
//        Log.w(Companion.TAG,remaining.toString())

//        val measureS : LocaleData.MeasurementSystem  = LocaleData.getMeasurementSystem(ULocale.getDefault())
//        Log.w(TAG, measureS.toString())

    }

//    fun getDistanceInDeviceUnit(context: Context, distance: Double): String {
//        val resources = context.resources
//        val unitAbbreviation = resources.configuration.units
//        val unitMultiplier = when (unitAbbreviation) {
//            "metric" -> 1.0
//            "imperial" -> 0.621371
//            else -> throw IllegalArgumentException("Unsupported unit abbreviation: $unitAbbreviation")
//        }
//        return String.format("%.2f %s", distance * unitMultiplier, unitAbbreviation)
//    }



    private var carPropertyListener = object : CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(prop: CarPropertyValue<Any>) {
            val id = prop.propertyId

            if (id == VehiclePropertyIds.INFO_MAKE ) vehicleInfoSharedModel.setMake(prop.value as String)
            if (id == VehiclePropertyIds.INFO_MODEL ) vehicleInfoSharedModel.setModel(prop.value as String)
            if (id == VehiclePropertyIds.CURRENT_GEAR) vehicleStatusSharedModel.setCurrentGear(prop.value as Int)
            if (id == VehiclePropertyIds.EV_BATTERY_LEVEL)energyInfoSharedModel.setBatteryLevelPercentage(prop.value as Float)
            if (id == VehiclePropertyIds.RANGE_REMAINING) energyInfoSharedModel.setRemainingRange(prop.value as Float)
            if (id == VehiclePropertyIds.PERF_ODOMETER) vehicleStatusSharedModel.setOdometer(prop.value as Float)
           // if (id == VehiclePropertyIds.INFO_FUEL_TYPE && prop.value is Array<*>)


        }

        override fun onErrorEvent(propId: Int, zone: Int) {
        }
    }

    companion object {
        private val TAG: String? = "MainActivity"
    }


}