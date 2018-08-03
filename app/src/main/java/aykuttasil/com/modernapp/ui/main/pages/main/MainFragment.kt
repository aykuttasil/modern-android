package aykuttasil.com.modernapp.ui.main.pages.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import aykuttasil.com.modernapp.R
import aykuttasil.com.modernapp.databinding.FragmentMainBinding
import aykuttasil.com.modernapp.di.Injectable
import aykuttasil.com.modernapp.ui.common.BaseFragment
import aykuttasil.com.modernapp.util.delegates.Inflate
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainFragment : BaseFragment(), Injectable {

    private val binding: FragmentMainBinding by Inflate(R.layout.fragment_main)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnGoUserActivity.onClick {
            Navigation.findNavController(binding.btnGoUserActivity).navigate(R.id.action_mainFragment_to_aboutFragment)
        }
    }
}