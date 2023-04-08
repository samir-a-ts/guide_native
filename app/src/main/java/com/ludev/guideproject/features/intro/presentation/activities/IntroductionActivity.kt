package com.ludev.guideproject.features.intro.presentation.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.ludev.guideproject.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ludev.guideproject.core.presentation.components.Gap
import com.ludev.guideproject.core.presentation.components.PrimaryButton
import com.ludev.guideproject.core.presentation.theme.GuideProjectTheme
import com.ludev.guideproject.core.presentation.theme.thirdColor
import com.ludev.guideproject.features.app.presentation.activities.MainActivity
import com.ludev.guideproject.features.intro.presentation.components.TabBarIndicator


class IntroductionActivity : ComponentActivity() {

    data class  IntroPageData(val titleStringId: Int, val descriptionStringId: Int, val imageId: Int)

    private val _data = listOf(
        IntroPageData(
            R.string.intro_welcome_to_guide_1,
            R.string.intro_welcome_to_guide_desc_1,
            R.drawable.intro_welcome_to_guide_icon_1,
        ),
        IntroPageData(
            R.string.intro_welcome_to_guide_2,
            R.string.intro_welcome_to_guide_desc_2,
            R.drawable.intro_welcome_to_guide_icon_2,
        ),
        IntroPageData(
            R.string.intro_welcome_to_guide_3,
            R.string.intro_welcome_to_guide_desc_3,
            R.drawable.intro_welcome_to_guide_icon_3,
        ),
    )

    private val _introShownKey = "IS_INTRO_SHOWN"

    private lateinit var _preferences: SharedPreferences

    private fun proceed() {
         with(_preferences.edit()) {
             putBoolean(_introShownKey, true)

             commit()
         }

        val intent = Intent(
            this,
            MainActivity::class.java
        )

        startActivity(intent)

        finish()
    }

    @Composable
    fun IntroPageDataView(
        data: IntroPageData
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.size(104.dp),
                painter = painterResource(id = data.imageId),
                contentDescription = "Intro page icon",
            )
            Gap(40.dp)
            Text(
                modifier = Modifier.width(244.dp),
                text = stringResource(id = data.titleStringId),
                style = MaterialTheme.typography.h2.copy(
                    color = MaterialTheme.colors.onSurface,
                ),
                textAlign = TextAlign.Center,
            )
            Gap(8.dp)
            Text(
                modifier = Modifier.width(244.dp),
                text = stringResource(id = data.descriptionStringId),
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.thirdColor,
                ),
                textAlign = TextAlign.Center,
            )
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _preferences = getSharedPreferences("app_cache", Context.MODE_PRIVATE)

        setContent {
            GuideProjectTheme {
                Surface(
                    color =  MaterialTheme.colors.background,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val constraintSet = ConstraintSet {
                        val button = createRefFor("skip_button")

                        val tabBarIndicator = createRefFor("tab_bar_indicator")

                        val tabBarView = createRefFor("tab_bar_view")

                        val proceedButton = createRefFor("proceed_button")

                        constrain(button) {
                            top.linkTo(parent.top, margin = 18.dp)
                            end.linkTo(parent.end, margin = 16.dp)
                        }

                        constrain(tabBarView) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }

                        constrain(tabBarIndicator) {
                            top.linkTo(tabBarView.bottom)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }

                        constrain(proceedButton) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    }

                    val page = rememberPagerState()

                    ConstraintLayout (
                        constraintSet = constraintSet,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        AnimatedVisibility(
                            visible = page.currentPage != page.pageCount - 1,
                            enter = fadeIn(),
                            exit = fadeOut(),
                            modifier =  Modifier
                                .layoutId("skip_button"),
                        ) {
                            TextButton(
                                onClick = {
                                    proceed()
                              },
                            ) {
                                Text(
                                    text = stringResource(
                                        id = R.string.intro_skip_button_text
                                    ),
                                    style = MaterialTheme.typography.h4,
                                )
                            }
                        }

                        ConstraintLayout (
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(246.dp)
                                .layoutId("tab_bar_view")
                        ) {
                            HorizontalPager(
                                count = 3,
                                state = page,
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                IntroPageDataView(_data[page.currentPage])
                            }
                        }

                        ConstraintLayout (
                            modifier = Modifier
                                .height(8.dp)
                                .layoutId("tab_bar_indicator")
                        ) {
                            TabBarIndicator(
                                current = page.currentPage,
                                length = page.pageCount,
                            )
                        }

                        AnimatedVisibility(
                            visible = page.currentPage == page.pageCount - 1,
                            enter = fadeIn(),
                            exit = fadeOut(),
                            modifier =  Modifier
                                .layoutId("proceed_button"),
                            ) {
                            PrimaryButton(
                                onClick = {
                                    proceed()
                                },
                            ) {
                                Text(
                                    text = stringResource(id = R.string.proceed_button_text),
                                    style = MaterialTheme.typography.button.copy(
                                        color = MaterialTheme.colors.onPrimary,
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

