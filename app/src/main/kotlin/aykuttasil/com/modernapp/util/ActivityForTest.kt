package aykuttasil.com.modernapp.util

import android.os.Bundle
import aykuttasil.com.modernapp.R
import aykuttasil.com.modernapp.ui.common.BaseActivity

class ActivityForTest : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_test)
    }
}
