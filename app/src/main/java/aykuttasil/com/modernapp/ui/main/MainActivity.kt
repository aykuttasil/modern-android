package aykuttasil.com.modernapp.ui.main

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import com.google.android.material.bottomnavigation.BottomNavigationView
import aykuttasil.com.modernapp.R
import aykuttasil.com.modernapp.databinding.ActivityMainBinding
import aykuttasil.com.modernapp.di.ViewModelFactory
import aykuttasil.com.modernapp.ui.common.BaseActivity
import aykuttasil.com.modernapp.ui.common.NavigationController
import aykuttasil.com.modernapp.ui.common.RetryCallback
import aykuttasil.com.modernapp.util.NewMessageNotification
import aykuttasil.com.modernapp.util.delegates.contentView
import aykuttasil.com.modernapp.util.load
import aykuttasil.com.modernapp.util.logd
import aykuttasil.com.modernapp.util.then
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.delay
import javax.inject.Inject


class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by contentView(R.layout.activity_main)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var navigationController: NavigationController

    private lateinit var mainViewModel: MainViewModel

    companion object {
        private const val KEY_IMAGE_URI = "imageUri"
        private const val IMAGE_PICK_REQUEST = 101
    }

    private var imageUri: Uri? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        mainViewModel.getUser("aykuttasillll").observe(this, Observer {
            binding.user = it?.data
            binding.resource = it
            binding.callback = object : RetryCallback {
                override fun retry() {
                    mainViewModel.retryGetUser("aykuttasil")
                }

            }
            binding.executePendingBindings()
        })


        btnImagePicker.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).also { it.type = "image/*" }
            startActivityForResult(intent, IMAGE_PICK_REQUEST)
        }

        btnShowNotif.setOnClickListener {
            NewMessageNotification.notify(this, "Test", 1)
        }

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_IMAGE_URI)) {
            logd { "Saved state contains an image Uri. Let's assign it to imageUri." }
            imageUri = savedInstanceState.getParcelable(KEY_IMAGE_URI) as Uri
            imageUri?.let { loadAndShowPhoto(it) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_PICK_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            logd { "User picked photo with uri ${data.data}, let's load it" }
            imageUri = data.data // So we can store it in onSaveInstanceState
            loadAndShowPhoto(data.data)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (outState != null && imageUri != null) {
            logd { "Let's save the imageUri to our saved state so we can load it when we come back!" }
            outState.putParcelable(KEY_IMAGE_URI, imageUri)
        }
    }


    private fun loadAndShowPhoto(uri: Uri) {
        load {
            // This run on a background thread
            logd { "Start loading image on thread ${Thread.currentThread().name}" }
            delay(2000L) // Fake a long loading so we can test what happens in onStop()
            MediaStore.Images.Media.getBitmap(contentResolver, uri)
        } then {
            // This runs on the main thread
            logd { "Image with size ${it.width} x ${it.height} loaded. Display on ImageView running on thread ${Thread.currentThread().name}" }
            imageView.setImageBitmap(it)
        }
    }

    // override fun onSupportNavigateUp() = Navigation.findNavController(R.id.nav_host_fragment).navigateUp()

}
