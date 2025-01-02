package com.example.toastdialoglibrary

import android.app.Activity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class ToastDialog(private val activity: Activity) {

    private var alertDialog: AlertDialog? = null

    fun showToast(message: String, duration: Long = 2000) {
        val inflater = LayoutInflater.from(activity)
        val view = inflater.inflate(R.layout.custom_toast_dialog, null)
        val toastMessage = view.findViewById<TextView>(R.id.toastMessages)
        toastMessage.text = message

        val builder = AlertDialog.Builder(activity)
            .setView(view)
            .setCancelable(true)

        alertDialog = builder.create()
        alertDialog?.window?.setLayout(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        
        alertDialog?.show()

        // Automatically dismiss the dialog after the specified duration
        view.postDelayed({
            dismiss()
        }, duration)
    }

    private fun dismiss() {
        alertDialog?.dismiss()
    }
}
