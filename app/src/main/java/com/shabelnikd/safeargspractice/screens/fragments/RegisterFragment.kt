package com.shabelnikd.safeargspractice.screens.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shabelnikd.safeargspractice.R
import com.shabelnikd.safeargspractice.databinding.FragmentRegisterBinding
import com.shabelnikd.safeargspractice.screens.Dependencies
import com.shabelnikd.safeargspractice.screens.database.entities.UserTuple
import com.shabelnikd.safeargspractice.screens.fragments.RegisterFragmentDirections
import com.shabelnikd.safeargspractice.screens.models.User
import kotlinx.coroutines.launch
import kotlin.toString

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private var etNameFilled = false
    private var etEmailFilled = false
    private var etPasswordFilled = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSend.isEnabled = false
        setTextChangedListeners()
        setClickListeners()

    }

    private fun setTextChangedListeners() {
        val etNameStateChange = fun(state: Boolean) { etNameFilled = state }
        val etEmailStateChange = fun(state: Boolean) { etEmailFilled = state }
        val etPasswordStateChange = fun(state: Boolean) { etPasswordFilled = state }

        binding.etName.addTextChangedListener(returnTextWatcher(etNameStateChange))
        binding.etEmail.addTextChangedListener(returnTextWatcher(etEmailStateChange))
        binding.etPassword.addTextChangedListener(returnTextWatcher(etPasswordStateChange))

    }


    fun returnTextWatcher(changeState: (state: Boolean) -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                changeState(!s.isNullOrEmpty())
                binding.btnSend.isEnabled = etNameFilled && etEmailFilled && etPasswordFilled
            }
        }
    }

    private fun setClickListeners() {
        binding.btnSend.setOnClickListener {
            val newUser = User(
                name = binding.etName.text.toString(),
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString().toInt()
            )

            lifecycleScope.launch {
                Dependencies.usersRepository.insertNewUser(newUser.toUsersDbEntity())
            }

            findNavController().navigate(
                RegisterFragmentDirections.actionRegisterFragmentToDetailFragment(newUser)
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}