package com.ludev.guideproject.features.intro.presentation.activities

import com.ludev.guideproject.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ludev.guideproject.core.presentation.theme.GuideProjectTheme

class IntroductionActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GuideProjectTheme {
                Surface(
                    color =  MaterialTheme.colors.background,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val constraintSet = ConstraintSet {
                        val button = createRefFor("button")

                        val tabBarIndicator = createRefFor("tab_bar_indicator")

                        val tabBarView = createRefFor("tab_bar_view")

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
                    }

                    ConstraintLayout (
                        constraintSet = constraintSet,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        TextButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.layoutId("button")
                        ) {
                            Text(
                                text = stringResource(
                                    id = R.string.intro_skip_button_text
                                ),
                                style = MaterialTheme.typography.h4,
                            )
                        }

                        var page = rememberPagerState()

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

                            }
                        }

                        ConstraintLayout (
                            modifier = Modifier
                                .size(56.dp, 8.dp)
                                .layoutId("tab_bar_indicator")
                        ) {}
                    }
                }
            }
        }
    }
}
