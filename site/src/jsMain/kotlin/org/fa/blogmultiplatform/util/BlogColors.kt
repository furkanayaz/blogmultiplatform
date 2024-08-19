package org.fa.blogmultiplatform.util

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.rgb
import org.jetbrains.compose.web.css.rgba

enum class BlogColors(
    val hex: String,
    val rgb: CSSColorValue
) {
    PRIMARY(
        hex = "#FF00A2FF",
        rgb = rgb(0, 162, 255)
    ),
    BACKGROUND(
        hex = "#FFFAFAFA",
        rgb = rgb(240, 240, 240)
    ),
    LT_BLACK(
        hex = "#FF494949",
        rgb = rgb(73, 73, 73)
    ),
    DK_BLACK(
        hex = "#FF000000",
        rgb = rgb(0, 0, 0)
    ),
    WHITE(
        hex = "#FFFFFFFF",
        rgb = rgb(255, 255, 255)
    ),
    LT_RED(
        hex = "#FFFF9292",
        rgb(255, 146, 146)
    ),
    RED(
        hex = "#FFFF2F2F",
        rgb = rgb(255, 47, 47)
    ),
    TRANSPARENT(
        hex = "#00000000",
        rgb = rgba(0, 0, 0, 0)
    )
}