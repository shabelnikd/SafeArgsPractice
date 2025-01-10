package com.shabelnikd.safeargspractice.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.shabelnikd.safeargspractice.R
import com.shabelnikd.safeargspractice.databinding.FragmentAllUsersBinding
import com.shabelnikd.safeargspractice.screens.Dependencies
import com.shabelnikd.safeargspractice.screens.adapters.UserAdapter
import com.shabelnikd.safeargspractice.screens.database.entities.UserTuple
import com.shabelnikd.safeargspractice.screens.models.User
import kotlinx.coroutines.launch
import kotlin.getValue


class AllUsersFragment : Fragment() {

    private var _binding: FragmentAllUsersBinding? = null
    private val binding get() = _binding!!

    //    private val args: AllUsersFragmentArgs by navArgs()
    private val usersList = arrayListOf<UserTuple>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        initialization()


    }

    private fun loadData() {
        lifecycleScope.launch {
            val users = Dependencies.usersRepository.getAllUsers()
            usersList.addAll(users)
        }


    }

    private fun initialization() {
        val userAdapter = UserAdapter(usersList, onDeleteClick = { position, id ->
            usersList.removeAt(position)
            lifecycleScope.launch {
                Dependencies.usersRepository.deleteUserById(id)
            }
        })

        binding.rvUsers.adapter = userAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        usersList.clear()
    }
}