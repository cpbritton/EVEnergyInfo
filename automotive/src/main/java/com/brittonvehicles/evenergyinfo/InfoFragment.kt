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

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var binding: FragmentInfoBinding

/**
 * A simple [Fragment] subclass.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    val vehicleInfoSharedModel by activityViewModels<VehicleInfoSharedModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)

        vehicleInfoSharedModel.model.observe(viewLifecycleOwner, Observer<String> { item ->
            binding.carModelTextView.text = item
        })

        vehicleInfoSharedModel.make.observe(viewLifecycleOwner, Observer<String> { item ->
            binding.carMakeTextView.text = item
        })

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

}