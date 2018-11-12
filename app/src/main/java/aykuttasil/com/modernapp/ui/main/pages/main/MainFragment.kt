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
import aykuttasil.com.modernapp.util.LogUtils
import aykuttasil.com.modernapp.util.delegates.Inflate
import aykuttasil.com.modernapp.util.load
import aykuttasil.com.modernapp.util.then
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.delay

class MainFragment : BaseFragment(), Injectable {

    private val binding: FragmentMainBinding by Inflate(R.layout.fragment_main)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGoUserActivity.setOnClickListener {
            Navigation.findNavController(binding.btnGoUserActivity).navigate(R.id.action_mainFragment_to_aboutFragment)
        }

        load {
            LogUtils.info("Thread Name load 1:" + Thread.currentThread().name)
            delay(10000)
            LogUtils.info("Thread Name load 2:" + Thread.currentThread().name)

            val ad = "aykut"
            ad
        } then {
            LogUtils.info("Thread Name then:" + Thread.currentThread().name)
            LogUtils.info("Result: $it")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.info("MainFragment > onDestroy")
    }
}