package com.bold.bolttest.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.bold.main_weather.presentation.HomeActivity

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyAppTheme {
                SplashScreen {
                    startActivity(Intent(this@SplashActivity, HomeActivity::class.java)).apply {
                        finish()
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(onAnimationFinish: @Composable () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("weather_splash_animations.json"))

    val progress by animateLottieCompositionAsState(
        composition = composition, iterations = 1, isPlaying = true
    )

    val nextScreen = remember { mutableStateOf(false) }

    if (nextScreen.value) {
        onAnimationFinish()
    }

    if (progress == 1f) {
        nextScreen.value = true
    }
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition, progress = { progress }, modifier = Modifier.size(250.dp)
        )
    }
}