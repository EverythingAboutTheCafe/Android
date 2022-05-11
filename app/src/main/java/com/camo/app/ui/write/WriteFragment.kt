package com.camo.app.ui.write

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.NumberPicker
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.camo.app.R
import com.camo.app.databinding.DialogVisitTimeBinding
import com.camo.app.databinding.FragmentWriteBinding
import com.camo.app.model.Images
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteFragment : Fragment(){


    private lateinit var binding : FragmentWriteBinding
    private lateinit var getResult: ActivityResultLauncher<Intent>
    private lateinit var writePhotoAdapter : WritePhotoAdapter
    private var uriList = arrayListOf<Images>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteBinding.inflate(inflater, container, false)
        binding.tvVisitTime.setOnClickListener {
            callNumberPickerDialog(container)
        }
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner=viewLifecycleOwner

        writePhotoAdapter = WritePhotoAdapter(uriList, requireContext(), onDeleteClick = {
            uriList.remove(it)
            writePhotoAdapter.notifyDataSetChanged()
        })

        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if(it.resultCode == RESULT_OK) {
                uriList.clear()
                if(it.data?.clipData != null) {
                    val count = it.data!!.clipData!!.itemCount
                    if (count > 5) {
                        Toast.makeText(activity, "사진은 5장까지 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        for (i in 0 until count) {
                            val imageUri = it.data!!.clipData!!.getItemAt(i).uri
                            uriList.add(Images(imageUri.toString()))
                        }
                    }
                } else {
                    it.data?.data?.let { _ ->
                        val imageUri : Uri? = it.data?.data
                        if(imageUri != null) {
                            uriList.add(Images(imageUri.toString()))
                        }
                    }
                }
                writePhotoAdapter.notifyDataSetChanged()

            }
        }

        binding.btnAddPhoto.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*")
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            getResult.launch(intent)
        }

        binding.rvWritePhoto.adapter = writePhotoAdapter
    }

    private fun callNumberPickerDialog(view: ViewGroup?) {
        val inflater : LayoutInflater = requireActivity().layoutInflater
        val dialogView : DialogVisitTimeBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_visit_time, view, false)
        val dataAMPM = arrayOf<String>("AM","PM")
        val dataWeek = arrayOf<String>("평일","주말")
        lateinit var selectAMPM:String
        lateinit var selectWeek:String


        var selectHour:Int = 0

        val npHour = dialogView.npHour.apply {
            maxValue = 12
            minValue = 1
            descendantFocusability=NumberPicker.FOCUS_BLOCK_DESCENDANTS
        }

        val npAmPm = dialogView.npAmpm.apply {
            maxValue = 1
            minValue = 0
            displayedValues = dataAMPM
            descendantFocusability=NumberPicker.FOCUS_BLOCK_DESCENDANTS
        }

        val npWeek = dialogView.npWeek.apply {
            maxValue = 1
            minValue = 0
            displayedValues = dataWeek
            descendantFocusability=NumberPicker.FOCUS_BLOCK_DESCENDANTS
        }

        val dialogBuilder : AlertDialog.Builder = AlertDialog.Builder(requireActivity()).apply {
            setView(dialogView.root)
        }

        val dialog = dialogBuilder.create()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.show()

        dialogView.btnVisitTimeCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialogView.btnVisitTimeOk.setOnClickListener {
            selectHour = npHour.value
            selectAMPM = setValueToAmPm(npAmPm.value)
            selectWeek = setValueToWeek(npWeek.value)

            binding.tvVisitTime.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.tvVisitTime.text = getString(R.string.visit_time,selectWeek, selectAMPM, selectHour)
            dialog.dismiss()
        }



    }

    private fun setValueToAmPm(value: Int): String {
        return if(value == 0) "오전"
        else "오후"
    }

    private fun setValueToWeek(value: Int): String {
        return if(value == 0) "평일"
        else "주말"
    }

    private fun setDividerColor(picker: NumberPicker, color: Int) {
        val pickerFields = NumberPicker::class.java.declaredFields
        for (pf in pickerFields) {
            if(pf.name == "mSelectionDivdier") {
                pf.isAccessible = true
                try {
                    val colorDrawable = ColorDrawable(color)
                    pf[this] = colorDrawable
                } catch (e : java.lang.IllegalArgumentException) {
                    e.printStackTrace()
                } catch (e : Resources.NotFoundException) {
                    e.printStackTrace()
                } catch (e : IllegalAccessException) {
                    e.printStackTrace()
                }
                break
            }
        }

    }

}