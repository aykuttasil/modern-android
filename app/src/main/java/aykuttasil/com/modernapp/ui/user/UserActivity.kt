package aykuttasil.com.modernapp.ui.user

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import aykuttasil.com.modernapp.R
import aykuttasil.com.modernapp.databinding.ActivityUserBinding
import aykuttasil.com.modernapp.di.ViewModelFactory
import aykuttasil.com.modernapp.ui.common.BaseActivity
import aykuttasil.com.modernapp.util.delegates.contentView
import aykuttasil.com.modernapp.util.logd
import javax.inject.Inject


class UserActivity : BaseActivity() {

    private val binding: ActivityUserBinding by contentView(R.layout.activity_user)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logd { "oncreate" }
        viewModel = ViewModelProviders.of(this@UserActivity, viewModelFactory).get(UserViewModel::class.java)
    }
}
