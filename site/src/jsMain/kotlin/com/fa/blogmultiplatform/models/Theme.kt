package com.fa.blogmultiplatform.models

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.rgb

enum class Theme(
    val hex: String,
    val rgb: CSSColorValue
) {
    PRIMARY(
        hex = "#00A2F",
        rgb = rgb(0, 162, 255)
    ),
    LIGHT_GRAY(
        hex = "#FAFAFA",
        rgb = rgb(250, 250, 250)
    )
}