package com.brittonvehicles.evenergyinfo

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brittonvehicles.evenergyinfo.databinding.FragmentStatsBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private var _binding: FragmentStatsBinding? = null
private val binding get() = _binding!!
class StatsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        // inflate the layout and bind to the _binding
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lineChart.animate(usageHistory)
        binding.barChart.animate(chargeHistory)
        val chargeSum = chargeHistory.map { it.second }.sum()
        binding.totalChargeHistory.text = Html.fromHtml("$chargeSum <small>kwh</small>")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StatsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StatsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

            private val usageHistory = listOf(
                "label1" to 5f,
                "label2" to 4.5f,
                "label3" to 4.7f,
                "label4" to 3.5f,
                "label5" to 3.6f,
                "label6" to 7.5f,
                "label7" to 7.5f,
                "label8" to 10f,
                "label9" to 5f,
                "label10" to 6.5f,
                "label11" to 3f,
                "label12" to 4f,
                "label1" to 5f,
                "label2" to 4.5f,
                "label3" to 4.7f,
                "label4" to 3.5f,
                "label5" to 3.6f,
                "label6" to 7.5f,
                "label7" to 7.5f,
                "label8" to 10f,
                "label9" to 5f,
                "label10" to 6.5f,
                "label11" to 3f,
                "label12" to 4f
            )

        private val chargeHistory = listOf(
            "" to 4F,
            "" to 7F,
            "3" to 2F,
            "" to 2.3F,
            "" to 5F,
            "" to 4F,
            "" to 4F,
            "" to 7F,
            "" to 2F,
            "10" to 2.3F,
            "" to 5F,
            "" to 4F,
            "" to 4F,
            "" to 7F,
            "" to 2F,
            "" to 2.3F,
            "" to 5F,
            "" to 4F,
            "17" to 4F,
            "" to 7F,
            "" to 2F,
            "" to 2.3F,
            "" to 5F,
            "" to 4F,
            "" to 4F,
            "24" to 7F,
            "" to 2F,
            "" to 2.3F,
            "" to 5F,
            "" to 4F,
            "" to 4F,
            "" to 7F,
            "31" to 2F
        )

            private const val animationDuration = 1000L
    }
}