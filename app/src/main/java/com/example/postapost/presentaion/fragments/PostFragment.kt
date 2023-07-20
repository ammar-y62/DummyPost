package com.example.postapost.presentaion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.postapost.databinding.FragmentPostBinding
import com.example.postapost.presentaion.MainActivity
import com.example.postapost.presentaion.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : DialogFragment() {

    private lateinit var binding: FragmentPostBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostBinding.inflate(layoutInflater)
        viewModel = (activity as MainActivity).viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDimensions()
        setOnClicks()
    }

    private fun setDimensions() {
        val metrics = resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels
        this.dialog!!.window!!.setLayout(((10 * width) / 10), (9 * height) / 10)
        this.dialog!!.setCancelable(true)
    }

    private fun setOnClicks() {

        binding.btnShare.setOnClickListener {
            val title = if (binding.editTextTitle.text.toString().isNotEmpty()) {
                binding.editTextTitle.text.toString()
            } else {
                "Empty title"
            }
            val body = if (binding.editTextBody.text.toString().isNotEmpty()) {
                binding.editTextBody.text.toString()
            } else {
                "empty body"
            }
            val tagsList = if (binding.editTextTitle.text.toString().isNotEmpty()) {
                binding.editTextTitle.text.toString().split(" ")
            } else {
                emptyList()
            }

            viewModel.sharePost(title, body, tagsList)
            findNavController().navigateUp()

        }

    }

}