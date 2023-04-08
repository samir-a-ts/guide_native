package com.ludev.guideproject.core.presentation.activities

import Center
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ludev.guideproject.R
import com.ludev.guideproject.features.app.presentation.activities.MainActivity
import com.ludev.guideproject.core.presentation.theme.GuideProjectTheme
import com.ludev.guideproject.core.presentation.theme.yellowColor
import com.ludev.guideproject.features.intro.presentation.activities.IntroductionActivity


@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    private val _introShownKey = "IS_INTRO_SHOWN"

    private lateinit var _preferences: SharedPreferences

    private fun  isIntroShownForTheFirstTime(): Boolean {
        return _preferences.getBoolean(_introShownKey, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _preferences = getSharedPreferences("app_cache", Context.MODE_PRIVATE)

        setContent {
            GuideProjectTheme {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    MaterialTheme.colors.primary,
                                    MaterialTheme.colors.yellowColor,
                                ),
                            ),
                        )
                        .fillMaxSize(),
                ) {
                    Center {
                        Image(
                            painter = painterResource(id = R.drawable.ic_app_logo),
                            contentDescription = "App logo",
                            modifier = Modifier.size(150.dp),
                        )
                    }
                }
            }
        }

        Handler(Looper.getMainLooper()).postDelayed(
            {

                val isShown = isIntroShownForTheFirstTime()

                val intent = Intent(
                    this,
                    if (!isShown) IntroductionActivity::class.java else MainActivity::class.java
                )

                startActivity(intent)

                finish()
            },
            3000
        )
    }
}