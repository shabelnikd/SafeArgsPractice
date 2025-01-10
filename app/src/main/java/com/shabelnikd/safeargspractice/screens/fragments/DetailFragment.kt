package com.shabelnikd.safeargspractice.screens.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.shabelnikd.safeargspractice.R
import com.shabelnikd.safeargspractice.databinding.FragmentDetailBinding
import com.shabelnikd.safeargspractice.screens.models.User

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user: User? = args.user

        user?.let { user ->
            binding.tvName.text = user.name
            binding.tvEmail.text = user.email
            binding.tvPassword.text = user.password.toString()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}