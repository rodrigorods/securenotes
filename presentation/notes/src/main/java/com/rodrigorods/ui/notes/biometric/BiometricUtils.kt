package com.rodrigorods.ui.notes.biometric

import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
import android.os.Build
import android.os.CancellationSignal
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.rodrigorods.ui.notes.R

@RequiresApi(Build.VERSION_CODES.P)
fun showBiometricPrompt(
    activity: AppCompatActivity,
    onAuthenticationListener: (Boolean) -> Unit
) {
    val executor = ContextCompat.getMainExecutor(activity)

    val biometricPrompt = BiometricPrompt
        .Builder(activity)
        .setTitle(activity.resources.getString(R.string.biometric_dialog_title))
        .setSubtitle(activity.resources.getString(R.string.biometric_dialog_subtitle))
        .setDescription(activity.resources.getString(R.string.biometric_dialog_description))
        .setNegativeButton(
            activity.resources.getString(R.string.biometric_dialog_negative_button),
            executor,
            { dialogInterface, i -> onAuthenticationListener(false) }
        )
        .build()

    val callback = getAuthenticationCallback(onAuthenticationListener)
    val cancellationSignal = CancellationSignal().apply {
        this.setOnCancelListener { onAuthenticationListener(false) }
    }

    biometricPrompt.authenticate(cancellationSignal, executor, callback)
}

@RequiresApi(Build.VERSION_CODES.P)
private fun getAuthenticationCallback(
    onAuthenticationListener: (Boolean) -> Unit
) = object : AuthenticationCallback() {
    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        super.onAuthenticationError(errorCode, errString)
        onAuthenticationListener(false)
    }

    override fun onAuthenticationFailed() {
        super.onAuthenticationFailed()
        onAuthenticationListener(false)
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        super.onAuthenticationSucceeded(result)
        onAuthenticationListener(true)
    }
}