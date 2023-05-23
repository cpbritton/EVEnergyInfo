package com.brittonvehicles.evenergyinfo

import android.car.Car
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.brittonvehicles.evenergyinfo.databinding.ActivityMainBinding
import com.brittonvehicles.evenergyinfo.models.VehicleInfoSharedModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private const val REQUEST_CODE_ASK_PERMISSIONS = 1

class MainActivity : FragmentActivity() {

    private val tabsArray = arrayOf( "Home", "Charging","Stats", "Info")

    private lateinit var binding: ActivityMainBinding
    private lateinit var car: Car
    private lateinit var carPropertyManager: CarPropertyManager

    val vehicleInfoSharedModel by viewModels<VehicleInfoSharedModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // CRequest dangerous permissions only
        // Request dangerous permissions only
        val dangPermToRequest = checkDangerousPermissions()

        if (dangPermToRequest.isEmpty()) {
            main()
        } else {
            requestDangerousPermissions(dangPermToRequest)
            // CB:
            // onRequestPermissionsResult()
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
        carPropertyManager.registerCallback(
            carPropertyListener,
            VehiclePropertyIds.EV_BATTERY_LEVEL,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
        carPropertyManager.registerCallback(
            carPropertyListener,
            VehiclePropertyIds.INFO_EV_BATTERY_CAPACITY,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
        carPropertyManager.registerCallback(
            carPropertyListener,
            VehiclePropertyIds.DISTANCE_DISPLAY_UNITS,
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
        // DISTANCE_DISPLAY_UNITS
     //   EV_BATTERY_DISPLAY_UNITS

        try {
            //carPropertyManager.setProperty( String.javaClass , VehiclePropertyIds.INFO_MAKE, VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL , "Ginetta")
            //carPropertyManager.setProperty( Float.javaClass , VehiclePropertyIds.RANGE_REMAINING, VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL , 247)
            //carPropertyManager.setProperty( Float.javaClass , VehiclePropertyIds.PERF_ODOMETER, VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL , 100000)
            //carPropertyManager.setProperty( Float.javaClass , VehiclePropertyIds.EV_BATTERY_LEVEL, VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL , 70)
        }catch (exception : Exception){
            Log.w(TAG, exception)
        }

        // Test since we cannot set this via ADB or I dont know how
//        vehicleInfoSharedModel.setOdometer(10000F )
//        vehicleInfoSharedModel.setRemainingRange(100000F )

    }



    private var carPropertyListener = object : CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(prop: CarPropertyValue<Any>) {
            val id = prop.propertyId

            if (id == VehiclePropertyIds.INFO_MAKE ) vehicleInfoSharedModel.setMake(prop.value as String)
            if (id == VehiclePropertyIds.INFO_MODEL ) vehicleInfoSharedModel.setModel(prop.value as String)
            if (id == VehiclePropertyIds.INFO_MODEL_YEAR ) vehicleInfoSharedModel.setModelYear(prop.value as Int)
            if (id == VehiclePropertyIds.CURRENT_GEAR) vehicleInfoSharedModel.setCurrentGear(prop.value as Int)
            if (id == VehiclePropertyIds.EV_BATTERY_LEVEL)vehicleInfoSharedModel.setBatteryLevel(prop.value as Float)
            if (id == VehiclePropertyIds.INFO_EV_BATTERY_CAPACITY)vehicleInfoSharedModel.setBatterCapacity(prop.value as Float)
            if (id == VehiclePropertyIds.RANGE_REMAINING) vehicleInfoSharedModel.setRemainingRange(prop.value as Float)
            if (id == VehiclePropertyIds.PERF_ODOMETER) vehicleInfoSharedModel.setOdometer(prop.value as Float)
            if (id == VehiclePropertyIds.DISTANCE_DISPLAY_UNITS) vehicleInfoSharedModel.setDistanceDisplayUnit(prop.value as Int)
            if (id == VehiclePropertyIds.EV_BATTERY_DISPLAY_UNITS) vehicleInfoSharedModel.setEvDisplayUnit(prop.value as Int)

        }

        override fun onErrorEvent(propId: Int, zone: Int) {
        }
    }

    companion object {
        private val TAG: String? = "MainActivity"
    }

    private fun setupUX(){

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

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            //if check for "all permissions have been granted"
            if (!grantResults.contains(PackageManager.PERMISSION_DENIED))
            {
                main()
            }
        }
    }

    private fun main() {
        setupCar()
        setupUX()
        setupListeners()
        setupCarTest()
    }

    private fun setupCar() {
        car = Car.createCar(this)
        carPropertyManager = car.getCarManager(Car.PROPERTY_SERVICE) as CarPropertyManager
    }

    private fun checkDangerousPermissions(): List<String> {
        val permissions: MutableList<String> = ArrayList()
        if (checkSelfPermission(Car.PERMISSION_SPEED) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Car.PERMISSION_SPEED)
        }
        if (checkSelfPermission(Car.PERMISSION_ENERGY) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Car.PERMISSION_ENERGY)
        }
        if (checkSelfPermission(Car.PERMISSION_ENERGY_PORTS) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Car.PERMISSION_ENERGY_PORTS)
        }
        return permissions
    }

    private fun requestDangerousPermissions(permissions: List<String>) {
        requestPermissions(permissions.toTypedArray<String>(), REQUEST_CODE_ASK_PERMISSIONS)
    }


}