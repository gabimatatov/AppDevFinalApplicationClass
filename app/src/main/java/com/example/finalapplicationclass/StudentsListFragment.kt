package com.example.finalapplicationclass

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapplicationclass.adapter.StudentsRecyclerAdapter
import com.example.finalapplicationclass.model.GetAllStudentsListener
import com.example.finalapplicationclass.databinding.FragmentStudentsListBinding
import com.example.finalapplicationclass.model.Model
import com.example.finalapplicationclass.model.Student

/*
* 1. Refactor to use Live Data
* 2. Handle UI Events
* 3. Refactor states to handle LOADING/LOADED
* */
class StudentsListFragment : Fragment() {

    private var adapter : StudentsRecyclerAdapter? = null
    private var binding: FragmentStudentsListBinding? = null
    private var viewModel: StudentsListViewModel? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[StudentsListViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStudentsListBinding.inflate(inflater, container, false)
        binding?.recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        binding?.recyclerView?.layoutManager = layoutManager

        adapter = StudentsRecyclerAdapter(viewModel?.students?.value)

        getAllStudents()
        viewModel?.students?.observe(viewLifecycleOwner) {
            adapter?.students = it
            adapter?.notifyDataSetChanged()
            binding?.progressBar?.visibility = View.GONE
        }


        adapter?.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("TAG", "On click Activity listener on position $position")
            }

            override fun onItemClick(student: Student?) {
                Log.d("TAG", "On student clicked name: ${student?.name}")
                student?.let {
                    val action = StudentsListFragmentDirections.actionStudentsListFragmentToBlueFragment(it.name)
                    binding?.root?.let {
                        Navigation.findNavController(it).navigate(action)
                    }
                }
            }
        }

        binding?.recyclerView?.adapter = adapter
        val action = StudentsListFragmentDirections.actionGlobalAddStudentFragment()
        binding?.addStudentButton?.setOnClickListener(Navigation.createNavigateOnClickListener(action))

        return binding?.root
    }


    override fun onResume() {
        super.onResume()
        getAllStudents()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun getAllStudents() {
//        binding?.progressBar?.visibility = View.VISIBLE

        Model.shared.refreshStudents()

//        Model.shared.getAllStudents {
//            viewModel?.set(students = it)
//            adapter?.set(it)
//            adapter?.notifyDataSetChanged()
//
//            binding?.progressBar?.visibility = View.GONE
//        }
    }
}