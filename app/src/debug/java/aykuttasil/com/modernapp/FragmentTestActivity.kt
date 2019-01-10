package aykuttasil.com.modernapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import aykuttasil.com.modernapp.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector


class FragmentTestActivity : AppCompatActivity(), HasSupportFragmentInjector {
    private lateinit var injector: AndroidInjector<Fragment>

    override fun supportFragmentInjector() = injector

    fun startFragment(fragment: Fragment, injector: AndroidInjector<Fragment>) {
        this.injector = injector

        supportFragmentManager?.registerFragmentLifecycleCallbacks(
            object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                    super.onFragmentCreated(fm, f, savedInstanceState)
                    if (f is Injectable) {
                        AndroidSupportInjection.inject(f)
                    }
                }
            }, true)

        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, fragment, "TAG")
            .commit()
    }

    inline fun <reified T : Fragment> startFragment(fragment: T, crossinline injector: (T) -> Unit) {
        startFragment(
            fragment,
            AndroidInjector {
                if (it is T) {
                    println("aaa injected")
                    injector(it)
                }
            }
        )
    }
}