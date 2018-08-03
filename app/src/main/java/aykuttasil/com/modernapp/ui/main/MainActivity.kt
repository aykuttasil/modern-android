package aykuttasil.com.modernapp.ui.main

import android.os.Bundle
import aykuttasil.com.modernapp.R
import aykuttasil.com.modernapp.di.ViewModelFactory
import aykuttasil.com.modernapp.ui.common.BaseActivity
import javax.inject.Inject


class MainActivity : BaseActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


}
