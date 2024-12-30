package com.example.finalapplicationclass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class AddStudentFragment : Fragment() {

    private var saveButton: Button? = null
    private var cancelButton: Button? = null
    private var nameEditText: EditText? = null
    private var idEditText: EditText? = null
    private var saveMessageTextView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_student, container, false)

        val saveButton: Button? = view?.findViewById(R.id.add_student_activity_save_button)
        val cancelButton: Button? = view?.findViewById(R.id.add_student_activity_cancel_button)
        val nameEditText: EditText? = view?.findViewById(R.id.add_student_activity_name_edit_text)
        val idEditText: EditText? = view?.findViewById(R.id.add_student_activity_id_edit_text)
        val saveMessageTextView: TextView? = view?.findViewById(R.id.add_student_activity_save_message_text_view)

        setupView(view)
        cancelButton?.setOnClickListener(::onCancelClick)
        saveButton?.setOnClickListener(::onSaveClick)

        return view
    }

    private fun setupView(view: View) {
        saveButton = view.findViewById(R.id.add_student_activity_save_button)
        cancelButton = view.findViewById(R.id.add_student_activity_cancel_button)
        nameEditText = view.findViewById(R.id.add_student_activity_name_edit_text)
        idEditText = view.findViewById(R.id.add_student_activity_id_edit_text)
        saveMessageTextView = view.findViewById(R.id.add_student_activity_save_message_text_view)
    }

    private fun onCancelClick(view: View){
        TODO()
    }

    private fun onSaveClick(view: View){
        saveMessageTextView?.text = "${nameEditText?.text} ${idEditText?.text} is saved"
    }

}