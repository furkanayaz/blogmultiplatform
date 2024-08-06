package com.fa.blogmultiplatform.models

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.rgb

enum class Theme(
    val hex: String,
    val rgb: CSSColorValue
) {
    PRIMARY(
        hex = "#00A2FF",
        rgb = rgb(0, 162, 255)
    ),
    LIGHT_GRAY(
        hex = "#FAFAFA",
        rgb = rgb(250, 250, 250)
    )
}

enum class ButtonTheme(
    val hex: String,
    val rgb: CSSColorValue
) {
    NORMAL(
        hex = "#FF00A2FF",
        rgb = rgb(0, 162, 255)
    ),
    HOVER(
        hex = "#FF009AF1",
        rgb = rgb(0, 154, 241)
    )
}