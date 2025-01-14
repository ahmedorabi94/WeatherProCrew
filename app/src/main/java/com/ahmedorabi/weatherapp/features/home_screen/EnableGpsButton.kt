package com.ahmedorabi.weatherapp.features.home_screen


import android.app.Activity
import android.content.IntentSender
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority

@Composable
fun EnableGpsButton(
    context: Activity,
    checkLocationPermission: () -> Unit
) {
    val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        1000L // 1-second interval (can be adjusted)
    ).build()

    val locationSettingsRequest = LocationSettingsRequest.Builder()
        .addLocationRequest(locationRequest)
        .setAlwaysShow(true) // Ensures the dialog is always shown
        .build()

    // GPS Request Launcher
    val locationRequestLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        //  gpsEnabled = result.resultCode == Activity.RESULT_OK
        if (result.resultCode == Activity.RESULT_OK){
            checkLocationPermission.invoke()
        }
    }

    val client = LocationServices.getSettingsClient(context)
    val task = client.checkLocationSettings(locationSettingsRequest)

    task.addOnSuccessListener {

    }

    task.addOnFailureListener { exception ->
        if (exception is ResolvableApiException) {
            try {
                val intentSenderRequest = IntentSenderRequest.Builder(exception.resolution).build()
                locationRequestLauncher.launch(intentSenderRequest)
            } catch (sendEx: IntentSender.SendIntentException) {
                sendEx.printStackTrace()
            }
        }
    }
}
