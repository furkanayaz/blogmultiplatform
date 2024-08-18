package org.fa.blogmultiplatform.components

import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.focus
import com.varabyte.kobweb.silk.style.selectors.hover
import org.fa.blogmultiplatform.util.BlogColors
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px

val inputStyle = CssStyle {
    base {
        Modifier.border(
            width = 2.px,
            style = LineStyle.Solid,
            color = BlogColors.TRANSPARENT.rgb
        ).transition(Transition.of("border", duration = 300.ms))
    }

    focus {
        Modifier.border(
            width = 2.px,
            style = LineStyle.Solid,
            color = BlogColors.PRIMARY.rgb
        )
    }
}

val buttonStyle = CssStyle {
    base {
        Modifier.background(BlogColors.PRIMARY.rgb).border(
            width = 0.px,
            style = LineStyle.None,
            color = BlogColors.TRANSPARENT.rgb
        )
    }

    hover {
        Modifier.background(BlogColors.WHITE.rgb).border(
            width = 2.px,
            style = LineStyle.Solid,
            color = BlogColors.PRIMARY.rgb
        )
    }
}