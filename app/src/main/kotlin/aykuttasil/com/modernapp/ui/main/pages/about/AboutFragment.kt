package aykuttasil.com.modernapp.ui.main.pages.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aykuttasil.com.modernapp.R
import aykuttasil.com.modernapp.databinding.FragmentAboutBinding
import aykuttasil.com.modernapp.di.Injectable
import aykuttasil.com.modernapp.ui.common.BaseFragment
import aykuttasil.com.modernapp.util.delegates.Inflate

class AboutFragment : BaseFragment(), Injectable {

    private val binding: FragmentAboutBinding by Inflate(R.layout.fragment_about)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }
}