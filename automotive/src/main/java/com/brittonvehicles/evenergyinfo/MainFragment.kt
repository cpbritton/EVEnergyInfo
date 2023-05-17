package com.brittonvehicles.evenergyinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.brittonvehicles.evenergyinfo.databinding.GearSelectorViewBinding
import com.brittonvehicles.evenergyinfo.models.VehicleStatusSharedModel


class MainFragment : Fragment() {

    companion object {
        private const val TAG = "MainFragment"

    }

    private val vehicleStatusSharedModel by activityViewModels<VehicleStatusSharedModel>()
    private lateinit var gsBinding: GearSelectorViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize view
        // TODO: change to a binding
        // = FragmentInfoBinding.inflate(inflater, container, false)

        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        // Add the GearSelectorView
        gsBinding = GearSelectorViewBinding.inflate(layoutInflater)
        val gearSelectorView = gsBinding.root
        val main = view.findViewById(R.id.gear_selector_point) as ViewGroup
        main.addView(gearSelectorView, 0)

        vehicleStatusSharedModel.currentGear.observe(viewLifecycleOwner, Observer<Int> { item ->
            updateGear(item)
        })

        return view
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