package com.wahidabd.compose_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import com.wahidabd.compose_sample.presentation.NavGraphs
import com.wahidabd.compose_sample.ui.theme.OneLibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneLibraryTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
