package com.example.toastdialoglibrary

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.core.os.postDelayed
import com.google.android.material.button.MaterialButton

class ToastDialog(private val context: Context) : Dialog(context) {

    init {
        setCancelable(false) // By default, dialogs are non-cancelable
    }

    /**
     * Show the dialog with a timeout of 2 seconds.
     */
    fun showWithTimeout(message: String, timeout: Long = 2000) {
        setupDialog(message)
        show()
        Handler(Looper.getMainLooper()).postDelayed(timeout) {
            dismiss()
        }
    }
    /**
     * Show the dialog until the user manually cancels it.
     */
    fun showWithButtons(
        message: String,
        onCancel: (() -> Unit)? = null,
        onAction: (() -> Unit)? = null
    ) {
        setupDialog(message)
        val cancelButton = findViewById<MaterialButton>(R.id.cancelButton)
        cancelButton?.setOnClickListener {
            onCancel?.invoke()
            dismiss() // Close the dialog on Cancel
        }

        // Set up Action button
        val actionButton = findViewById<MaterialButton>(R.id.actionButton)
        actionButton?.setOnClickListener {
            onAction?.invoke()
            dismiss() // Close the dialog after Action
        }
        show()
    }
    /**
     * Set up the dialog layout and message.
     */
    private fun setupDialog(message: String, onCancel: (() -> Unit)? = null,
                            onAction: (() -> Unit)? = null) {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_toast_dialog, null)
        val messageView =
            view.findViewById<TextView>(R.id.messageTextView) // Update with your TextView ID
        messageView?.text = message

        setContentView(view)
    }
}
