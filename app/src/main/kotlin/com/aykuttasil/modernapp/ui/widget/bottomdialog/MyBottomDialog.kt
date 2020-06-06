package com.aykuttasil.modernapp.ui.widget.bottomdialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.aykuttasil.modernapp.R
import com.aykuttasil.modernapp.common.util.extension.gone
import com.aykuttasil.modernapp.common.util.extension.visible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.internal.NavigationMenuView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.dialog_mybottom.*

class MyBottomDialog : BottomSheetDialogFragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.dialog_mybottom, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    navigation_view.setNavigationItemSelectedListener { menuItem ->
      // Bottom Navigation Drawer menu item clicks
      when (menuItem.itemId) {
        // R.id.nav1 -> context!!.toast(getString(R.string.nav1_clicked))
      }
      // Add code here to update the UI based on the item selected
      // For example, swap UI fragments here
      true
    }

    close_imageview.setOnClickListener {
      this.dismiss()
    }

    disableNavigationViewScrollbars(navigation_view)
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
    dialog.setOnShowListener { dialogInterface ->
      val d = dialogInterface as BottomSheetDialog

      val bottomSheet = d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
      val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
      bottomSheetBehavior.addBottomSheetCallback(object :
        BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
          if (slideOffset > 0.5) {
            close_imageview.visible()
          } else {
            close_imageview.gone()
          }
        }

        @SuppressLint("SwitchIntDef")
        override fun onStateChanged(bottomSheet: View, newState: Int) {
          when (newState) {
            BottomSheetBehavior.STATE_HIDDEN -> dismiss()
            // else -> close_imageview.visibility = View.GONE
          }
        }
      })
    }

    return dialog
  }

  private fun disableNavigationViewScrollbars(navigationView: NavigationView?) {
    val navigationMenuView = navigationView?.getChildAt(0) as NavigationMenuView
    navigationMenuView.isVerticalScrollBarEnabled = false
  }
}
