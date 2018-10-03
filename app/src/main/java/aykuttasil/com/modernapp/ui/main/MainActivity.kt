package aykuttasil.com.modernapp.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import aykuttasil.com.modernapp.R
import aykuttasil.com.modernapp.di.ViewModelFactory
import aykuttasil.com.modernapp.ui.common.BaseActivity
import aykuttasil.com.modernapp.util.logd
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logd { "onCreate" }
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }
}
