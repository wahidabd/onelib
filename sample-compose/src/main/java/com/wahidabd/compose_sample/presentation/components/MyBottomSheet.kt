package com.wahidabd.compose_sample.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Scanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wahidabd.compose_sample.ui.theme.Purple80


/**
 * Created by Wahid on 7/4/2023.
 * Github github.com/wahidabd.
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet(
    bottomSheetState: BottomSheetScaffoldState
) {
    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetShape = RoundedCornerShape(4.dp),
        sheetContent = {},
    ) {

    }
}

@Composable
private fun BottomSheet() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = "Bottom Sheet",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth()
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3)
        ){
            items(bottomSheetItems.size){
                BottomSheetItem(data = bottomSheetItems[it])
            }
        }
    }
}

@Composable
fun BottomSheetItem(
    data: BottomSheetItem
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .clickable { }
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        Icon(
            imageVector = data.icon,
            contentDescription = data.title,
            tint = Purple80
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = data.title, style = MaterialTheme.typography.bodyMedium, color = Purple80)
    }
}

private val bottomSheetItems = listOf(
    BottomSheetItem("Notification", Icons.Default.Notifications),
    BottomSheetItem("Email", Icons.Default.Email),
    BottomSheetItem("Account", Icons.Default.AccountBalance),
    BottomSheetItem("Search", Icons.Default.Search),
    BottomSheetItem("Scan", Icons.Default.Scanner),
    BottomSheetItem("Edit", Icons.Default.Edit),
    BottomSheetItem("Settings", Icons.Default.Settings),
)

data class BottomSheetItem(
    val title: String,
    val icon: ImageVector
)