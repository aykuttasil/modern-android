package aykuttasil.com.modernapp.ui.common

import aykuttasil.com.modernapp.R
import aykuttasil.com.modernapp.ui.main.MainActivity
import javax.inject.Inject

/**
 * Created by aykutasil on 2.03.2018.
 */
class NavigationController @Inject constructor(mainActivity: MainActivity) {

    private var containerId = R.id.container
    private var fragmentManager = mainActivity.supportFragmentManager


    /*
    fun navigateToSearch() {
        val searchFragment = SearchFragment()
        fragmentManager.beginTransaction()
                .replace(containerId, searchFragment)
                .commitAllowingStateLoss()
    }

    fun navigateToRepo(owner: String, name: String) {
        val fragment = RepoFragment.create(owner, name)
        val tag = "repo/$owner/$name"
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }

    fun navigateToUser(login: String) {
        val tag = "user/$login"
        val userFragment = UserFragment.create(login)
        fragmentManager.beginTransaction()
                .replace(containerId, userFragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }
    */

}