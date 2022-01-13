package com.example.a6_6.blankfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.a6_6.R
import com.example.a6_6.databinding.FragmentLogin1Binding
import com.example.a6_6.viewmodel.AppbarViewModel

/**
 * This fragment is added to main graph via [MainFragment]'s  [NavHostFragment]
 */


class LoginFragment1 : BaseDataBindingFragment<FragmentLogin1Binding>() {

    private val appbarViewModel by activityViewModels<AppbarViewModel>()

    override fun getLayoutRes(): Int = R.layout.fragment_login1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding!!.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_main_dest_to_loginFragment2)
        }

    }

    override fun onResume() {
        super.onResume()
        // Set this navController as ViewModel's navController
        appbarViewModel.currentNavController.value = findNavController()
    }
}