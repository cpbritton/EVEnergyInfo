package com.brittonvehicles.evenergyinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.brittonvehicles.evenergyinfo.databinding.FragmentInfoBinding
import com.brittonvehicles.evenergyinfo.models.VehicleInfoSharedModel

/**
 * Fragment for data display and debug informational items.
 */
class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private val vehicleInfoSharedModel by activityViewModels<VehicleInfoSharedModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)

        vehicleInfoSharedModel.make.observe(viewLifecycleOwner, Observer<String> { item ->
            binding.infoMake.text = item
        })

        vehicleInfoSharedModel.model.observe(viewLifecycleOwner, Observer<String> { item ->
            binding.infoModel.text = item
        })

        vehicleInfoSharedModel.modelYear.observe(viewLifecycleOwner, Observer<Int> { item ->
            binding.infoModelYear.text = item.toString()
        })

        return binding.root
    }

    companion object {
        private const val TAG = "InfoFragment"
    }

}