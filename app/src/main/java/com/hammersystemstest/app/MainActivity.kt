package com.hammersystemstest.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hammersystemstest.app.ui.BottomNavScreen
import com.hammersystemstest.core.ui.theme.HammersystemstestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HammersystemstestTheme {
                BottomNavScreen()
            }
        }
    }
}