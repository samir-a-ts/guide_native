package com.ludev.guideproject.features.intro.data

import android.content.SharedPreferences
import com.ludev.guideproject.features.intro.domain.ILaunchRepository

class LaunchRepository(private val preferences: SharedPreferences) : ILaunchRepository {
    private val _launchKey = "LAUNCH"

    override fun isFirstLaunch(): Boolean {
        val result = preferences.getBoolean(_launchKey, false)

        if (!result) {
            with(preferences.edit()) {
                putBoolean(_launchKey, true)
                apply()
            }
        }

        return result
    }
}