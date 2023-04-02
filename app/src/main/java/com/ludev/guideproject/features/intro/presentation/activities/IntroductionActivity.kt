package com.ludev.guideproject.features.intro.presentation.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

class IntroductionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContent {

            Surface(
                color = MaterialTheme.colors.background
            ) {
                IntroductionPage()
            }
        }
    }
}

@Composable
fun IntroductionPage() {

    ConstraintLayout() {
        val (button) = createRefs()

        TextButton(
            
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 18.dp)
                absoluteRight.linkTo(parent.absoluteRight, margin = 16.dp)
            }
        ) {
            Text(text = "Пропустить")
        }
    }
}