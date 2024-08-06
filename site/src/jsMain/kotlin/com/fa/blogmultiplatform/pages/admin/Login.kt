package com.fa.blogmultiplatform.pages.admin

import androidx.compose.runtime.*
import com.fa.blogmultiplatform.models.ButtonTheme
import com.fa.blogmultiplatform.models.Theme
import com.fa.blogmultiplatform.util.Resources
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input

@Page
@Composable
fun LoginScreen() {
    val loginError by remember { mutableStateOf("The user doesn't exist.") }
    var signInBgColor by remember { mutableStateOf(ButtonTheme.NORMAL) }

    val inputModifier = Modifier
        .padding(all = 20.px)
        .borderRadius(4.px)
        .border(
            width = 0.px,
            style = LineStyle.None,
            color = Colors.Transparent
        )
        .fontWeight(FontWeight.Medium)
        .fontFamily(Resources.Font.ARIAL)
        .fontSize(16.px)
        .width(350.px)
        .height(54.px)

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(leftRight = 50.px, top = 80.px, bottom = 24.px)
                .backgroundColor(Theme.LIGHT_GRAY.rgb),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.margin(bottom = 50.px).width(100.px),
                src = Resources.Image.LOGO,
                description = "Logo"
            )

            Input(
                type = InputType.Text,
                attrs = inputModifier.then(Modifier.margin(bottom = 12.px)).toAttrs {
                    attr("placeholder", "Username")
                })

            Input(
                type = InputType.Password,
                attrs = inputModifier.then(Modifier.margin(bottom = 20.px)).toAttrs {
                    attr("placeholder", "Password")
                }
            )

            Button(
                attrs = Modifier.width(350.px)
                    .onMouseOver {
                        signInBgColor = ButtonTheme.HOVER
                    }
                    .onMouseOut {
                        signInBgColor = ButtonTheme.NORMAL
                    }
                    .height(54.px)
                    .fontWeight(FontWeight.Medium)
                    .fontFamily(Resources.Font.ARIAL)
                    .fontSize(16.px)
                    .border(
                        width = 0.px,
                        style = LineStyle.None,
                        color = Colors.Transparent
                    )
                    .borderRadius(4.px)
                    .backgroundColor(signInBgColor.rgb).toAttrs()
            ) {
                SpanText("Sign In", modifier = Modifier.color(Colors.White))
            }

            SpanText(text = loginError, modifier = Modifier.margin(top = 24.px).width(350.px).fontWeight(
                FontWeight.Medium).fontFamily(Resources.Font.ARIAL).fontSize(16.px).color(Colors.Red).textAlign(TextAlign.Center))
        }
    }
}