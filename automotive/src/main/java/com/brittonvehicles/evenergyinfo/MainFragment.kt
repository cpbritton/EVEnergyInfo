package com.brittonvehicles.evenergyinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.brittonvehicles.evenergyinfo.databinding.FragmentMainBinding
import com.brittonvehicles.evenergyinfo.databinding.GearSelectorViewBinding
import com.brittonvehicles.evenergyinfo.models.EnergyInfoSharedModel
import com.brittonvehicles.evenergyinfo.models.VehicleStatusSharedModel

class MainFragment : Fragment() {

    companion object {
        private const val TAG = "MainFragment"
    }

    private val vehicleStatusSharedModel by activityViewModels<VehicleStatusSharedModel>()
    private val energyInfoSharedModel by activityViewModels<EnergyInfoSharedModel> ()
    private lateinit var gsBinding: GearSelectorViewBinding
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        gsBinding = GearSelectorViewBinding.inflate(layoutInflater)
        binding.gearSelectorPoint.addView(gsBinding.root)

        vehicleStatusSharedModel.currentGear.observe(viewLifecycleOwner, Observer<Int> { item ->
            updateGear(item)
        })

        binding.apply {
            evInfoModel = energyInfoSharedModel
            vehicleStatusModel = vehicleStatusSharedModel
        }
        return binding.root
    }

    private fun updateGear(value:Int){
            gsBinding.textN.isEnabled = false
            gsBinding.textR.isEnabled = false
            gsBinding.textP.isEnabled = false
            gsBinding.textD.isEnabled = false

            when (value as Int ) {
                1 -> gsBinding.textN.isEnabled = true
                2 -> gsBinding.textR.isEnabled = true
                4 -> gsBinding.textP.isEnabled = true
                8 -> gsBinding.textD.isEnabled = true
                else -> {
                }
            }
    }
}