package org.fa.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.VerticalDivider
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import org.fa.blogmultiplatform.components.buttonStyle
import org.fa.blogmultiplatform.components.inputStyle
import org.fa.blogmultiplatform.util.BlogColors
import org.fa.blogmultiplatform.util.Resources
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.silk.components.navigation.Link
import org.jetbrains.compose.web.dom.Footer
import org.jetbrains.compose.web.dom.Text

@Page("login")
@Composable
fun LoginPage() {
    var isFocusedSignIn by remember {
        mutableStateOf(false)
    }

    var isUserExist by remember {
        mutableStateOf(false)
    }

    val dividerStyle = Modifier.border(
        width = 0.px, style = LineStyle.None, color = BlogColors.TRANSPARENT.rgb
    )

    val inputStyle = inputStyle.toModifier().padding(leftRight = 16.px).width(350.px).height(54.px).outline(
        width = 0.px, style = LineStyle.Solid, color = BlogColors.TRANSPARENT.rgb
    ).fontFamily(Resources.Font.ARIAL).fontSize(15.px)

    val buttonStyle =
        buttonStyle.toModifier().width(350.px).height(52.px).borderRadius(4.px).fontFamily(Resources.Font.ARIAL)
            .fontSize(15.px).cursor(Cursor.Pointer)

    val textStyle = Modifier.fontFamily(Resources.Font.ARIAL).fontSize(15.px)

    Box(
        modifier = Modifier.fillMaxSize().background(BlogColors.BACKGROUND.rgb), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalDivider(modifier = dividerStyle.then(Modifier.height(80.px)))

            Image(Resources.Image.LOGO)

            VerticalDivider(modifier = dividerStyle.then(Modifier.height(50.px)))

            Input(type = InputType.Email, attrs = inputStyle.toAttrs {
                attr("placeholder", "Enter an email")
            })

            if (isUserExist) {
                SpanText(modifier = Modifier.width(350.px).padding(leftRight = 16.px, topBottom = 12.px).background(BlogColors.LT_RED.rgb).color(BlogColors.WHITE.rgb), text = "The user doesn’t exist.")
            } else {
                VerticalDivider(modifier = dividerStyle.then(Modifier.height(12.px)))
            }

            Input(type = InputType.Password, attrs = inputStyle.toAttrs {
                attr("placeholder", "Enter a password")
            })

            VerticalDivider(modifier = dividerStyle.then(Modifier.height(20.px)))

            Button(
                attrs = buttonStyle.then(Modifier.id(Resources.ID.SIGN_IN).onMouseOver {
                    isFocusedSignIn = true
                }.onMouseOut { isFocusedSignIn = false }.onClick { isUserExist = true }).toAttrs()
            ) {
                SpanText(
                    modifier = textStyle.then(Modifier.color(if (isFocusedSignIn) BlogColors.PRIMARY.rgb else BlogColors.WHITE.rgb)),
                    text = "Sign In"
                )
            }

            VerticalDivider(modifier = dividerStyle.then(Modifier.height(20.px)))

            Link(modifier = textStyle.then(Modifier.color(BlogColors.LT_BLACK.rgb)), path = "reset-my-password", text = "Reset My Password")
        }
    }
}