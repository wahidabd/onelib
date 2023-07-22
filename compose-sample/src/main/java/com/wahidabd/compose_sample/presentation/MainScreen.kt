package com.wahidabd.compose_sample.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.manualcomposablecalls.DestinationLambda
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.launch


/**
 * Created by Wahid on 7/4/2023.
 * Github github.com/wahidabd.
 */


@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun MainScreen(
    navigator: DestinationsNavigator
) {

    val bottomSheetState = rememberBottomSheetScaffoldState(
    )
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bottom Sheet", modifier = Modifier
                .clickable {
                    scope.launch {
                        if (bottomSheetState.bottomSheetState.isVisible) {
                            bottomSheetState.bottomSheetState.expand()
                        } else {
                            bottomSheetState.bottomSheetState.hide()
                        }
                    }
                }
        )
    }

}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(navigator = EmptyDestinationsNavigator)
}