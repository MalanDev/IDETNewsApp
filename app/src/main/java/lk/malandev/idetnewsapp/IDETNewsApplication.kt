package lk.malandev.idetnewsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IDETNewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}