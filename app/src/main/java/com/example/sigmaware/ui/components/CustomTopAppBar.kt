package com.example.sigmaware.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    navigationIcon: ImageVector? = null,
    navigationAction: () -> Unit = {},
    actions: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (navigationIcon != null) {
                IconButton(onClick = navigationAction) {
                    Icon(navigationIcon, contentDescription = "Back")
                }
            }
        },
        actions = { actions() },
        modifier = Modifier
    )
}