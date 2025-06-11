package com.sanaa.myweather.presentation.composable

import android.graphics.BlurMaskFilter
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.dropShadow(
    shape: Shape = RectangleShape,
    color: Color = Color.Black.copy(0.25f),
    blur: Dp = 4.dp,
    offsetY: Dp = 4.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp,
    scaleX: Float = 1f,
    scaleY: Float = 1f,
) = this.drawBehind {
    val scaleXHat = (size.width + spread.toPx())*(scaleX - 1)
    val scaleYHat = (size.height + spread.toPx())*(scaleY - 1)

    val shadowSize = Size(size.width + spread.toPx() + scaleXHat, size.height + spread.toPx() + scaleYHat)
    val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)
    val paint = Paint()
    paint.color = color

    if (blur.toPx() > 0) {
        paint.asFrameworkPaint().apply {
            maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
        }
    }
    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(offsetX.toPx()-scaleXHat/2, offsetY.toPx()-scaleYHat/2)
        canvas.drawOutline(shadowOutline, paint)
        canvas.restore()
    }
}