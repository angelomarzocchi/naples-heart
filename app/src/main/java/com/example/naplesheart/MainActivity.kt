package com.example.naplesheart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.naplesheart.ui.NaplesHeartApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NaplesHeartTheme {
                    val windowSize = calculateWindowSizeClass(activity = this)
                    NaplesHeartApp(
                        windowSize = windowSize.widthSizeClass
                    )
                }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NaplesHeartTheme {
        NaplesHeartApp(windowSize = WindowWidthSizeClass.Compact)
    }
}