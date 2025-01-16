package com.example.finalapplicationclass

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.navigation.Navigation
import com.example.finalapplicationclass.databinding.FragmentAddStudentBinding
import com.example.finalapplicationclass.model.Model
import com.example.finalapplicationclass.model.Student

class AddStudentFragment : Fragment() {

    private var binding: FragmentAddStudentBinding? = null

    private var cameraLauncher: ActivityResultLauncher<Void?>? = null

    // On Create View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddStudentBinding.inflate(inflater, container, false)

        binding?.cancelButton?.setOnClickListener(::onCancelClick)
        binding?.saveButton?.setOnClickListener(::onSaveClick)

        cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            binding?.imageView?.setImageBitmap(bitmap)
        }

        binding?.addPhotoButton?.setOnClickListener {
            cameraLauncher?.launch(null)
        }

        return binding?.root
    }

    // On Destroy
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    // On Create
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    // On Create Options Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    // On Cancel Click
    private fun onCancelClick(view: View){
        Navigation.findNavController(view).popBackStack()
    }

    // On Save Click
    private fun onSaveClick(view: View){
        binding?.saveMessageTextView?.text = "${binding?.nameEditText?.text} ${binding?.idEditText?.text} is saved"
        val student = Student(
            name = binding?.nameEditText?.text?.toString() ?: "",
            id = binding?.idEditText?.text?.toString() ?: "",
            avatarUrl = "",
            isChecked = false
        )

        binding?.progressBar?.visibility = View.VISIBLE

        binding?.imageView?.isDrawingCacheEnabled = true
        binding?.imageView?.buildDrawingCache()
        val bitmap = (binding?.imageView?.drawable as BitmapDrawable).bitmap

        Model.shared.add(student, bitmap) {
            binding?.progressBar?.visibility = View.GONE
            Navigation.findNavController(view).popBackStack()
        }
    }
}