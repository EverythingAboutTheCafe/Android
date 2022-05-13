package com.camo.app.ui.write

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.camo.app.R
import com.camo.app.databinding.DialogVisitTimeBinding

class VisitTimeDialogFragment : DialogFragment() {


    private val viewModel : WriteViewModel by activityViewModels()
    private lateinit var binding:DialogVisitTimeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable=false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = DialogVisitTimeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        val dataAMPM = arrayOf<String>("AM","PM")
        val dataWeek = arrayOf<String>("평일","주말")
        lateinit var resultData:String
        lateinit var selectAMPM:String
        lateinit var selectWeek:String


        var selectHour:Int = 0

        val npHour = binding.npHour.apply {
            maxValue = 12
            minValue = 1
            descendantFocusability= NumberPicker.FOCUS_BLOCK_DESCENDANTS
        }

        val npAmPm = binding.npAmpm.apply {
            maxValue = 1
            minValue = 0
            displayedValues = dataAMPM
            descendantFocusability= NumberPicker.FOCUS_BLOCK_DESCENDANTS
        }

        val npWeek = binding.npWeek.apply {
            maxValue = 1
            minValue = 0
            displayedValues = dataWeek
            descendantFocusability= NumberPicker.FOCUS_BLOCK_DESCENDANTS
        }

        binding.btnVisitTimeCancel.setOnClickListener {
            dialog?.dismiss()
        }

        binding.btnVisitTimeOk.setOnClickListener {
            selectHour = npHour.value
            selectAMPM = setValueToAmPm(npAmPm.value)
            selectWeek = setValueToWeek(npWeek.value)
            resultData = getString(R.string.visit_time,selectWeek, selectAMPM, selectHour)
            viewModel.setVisitTime(resultData)
            dialog?.dismiss()
        }

        return binding.root
    }

    private fun setValueToAmPm(value: Int): String {
        return if(value == 0) "오전"
        else "오후"
    }

    private fun setValueToWeek(value: Int): String {
        return if(value == 0) "평일"
        else "주말"
    }

}