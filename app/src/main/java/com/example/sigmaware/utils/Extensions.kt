package com.example.sigmaware.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

fun Modifier.bottomBorder(strokeWidth: Float, color: Color) = drawBehind {
    val width = size.width
    val height = size.height - strokeWidth / 2
    drawLine(
        color = color,
        start = Offset(0f, height),
        end = Offset(width, height),
        strokeWidth = strokeWidth
    )
}