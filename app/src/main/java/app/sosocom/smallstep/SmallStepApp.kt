package app.sosocom.smallstep

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SmallStepApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // 다크모드 임시 Off
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}