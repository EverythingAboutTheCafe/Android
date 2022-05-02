package com.camo.app.ui.write

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
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
}