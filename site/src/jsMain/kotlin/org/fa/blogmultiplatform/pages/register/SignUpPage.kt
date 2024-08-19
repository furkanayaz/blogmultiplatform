package org.fa.blogmultiplatform.pages.register

import androidx.compose.runtime.*
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
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.navigation.Link
import kotlinx.browser.document
import kotlinx.coroutines.launch
import org.fa.blogmultiplatform.pages.register.vm.RegisterVM
import org.fa.blogmultiplatform.util.State
import org.jetbrains.compose.web.dom.Progress
import org.w3c.dom.HTMLInputElement

@Page("/sign-up")
@Composable
fun SignUpPage() {
    val pageContext = rememberPageContext()
    val scope = rememberCoroutineScope()

    val register = RegisterVM()

    val signUpState = register.signUpFlow.collectAsState()

    val dividerStyle = Modifier.border(
        width = 0.px, style = LineStyle.None, color = BlogColors.TRANSPARENT.rgb
    )

    val inputStyle = inputStyle.toModifier().padding(leftRight = 16.px).width(350.px).height(54.px).outline(
        width = 0.px, style = LineStyle.Solid, color = BlogColors.TRANSPARENT.rgb
    ).fontFamily(Resources.Font.ARIAL).fontSize(15.px).background(BlogColors.WHITE.rgb)

    val buttonStyle =
        buttonStyle.toModifier().width(350.px).height(52.px).borderRadius(4.px).fontFamily(Resources.Font.ARIAL)
            .fontSize(15.px).cursor(Cursor.Pointer)

    val textStyle = Modifier.fontFamily(Resources.Font.ARIAL).fontSize(15.px).color(BlogColors.LT_BLACK.rgb)

    Box(
        modifier = Modifier.fillMaxSize().background(BlogColors.BACKGROUND.rgb), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(bottom = 150.px),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalDivider(modifier = dividerStyle.then(Modifier.height(80.px)))

            Image(Resources.Image.LOGO)

            VerticalDivider(modifier = dividerStyle.then(Modifier.height(50.px)))

            Input(type = InputType.Email, attrs = inputStyle.then(Modifier.id(Resources.ID.INPUT_FULL_NAME)).toAttrs {
                attr("placeholder", "Enter your full-name")
            })

            VerticalDivider(modifier = dividerStyle.then(Modifier.height(12.px)))

            Input(type = InputType.Email, attrs = inputStyle.then(Modifier.id(Resources.ID.INPUT_EMAIL)).toAttrs {
                attr("placeholder", "Enter your email")
            })

            if (register.isErrorExist.isNotEmpty()) {
                SpanText(
                    modifier = Modifier.width(350.px).padding(leftRight = 16.px, topBottom = 12.px)
                        .background(BlogColors.LT_RED.rgb).color(BlogColors.WHITE.rgb), text = register.isErrorExist.take(100)
                )
            } else {
                VerticalDivider(modifier = dividerStyle.then(Modifier.height(12.px)))
            }

            Input(type = InputType.Password, attrs = inputStyle.then(Modifier.id(Resources.ID.INPUT_PASSWORD)).toAttrs {
                attr("placeholder", "Enter your password")
            })

            VerticalDivider(modifier = dividerStyle.then(Modifier.height(12.px)))

            Button(
                attrs = buttonStyle.then(Modifier.onMouseOver {
                    register.isFocusedSignIn = true
                }.onMouseOut { register.isFocusedSignIn = false }.onClick {
                    val fullName = document.getElementById(Resources.ID.INPUT_FULL_NAME) as HTMLInputElement
                    val email = document.getElementById(Resources.ID.INPUT_EMAIL) as HTMLInputElement
                    val password = document.getElementById(Resources.ID.INPUT_PASSWORD) as HTMLInputElement

                    if(fullName.value.isEmpty() || email.value.isEmpty() || password.value.isEmpty()) return@onClick

                    scope.launch {
                        register.signUp(fullName.value, email.value, password.value)
                    }
                }).toAttrs()
            ) {
                SpanText(
                    modifier = textStyle.then(Modifier.color(if (register.isFocusedSignIn) BlogColors.PRIMARY.rgb else BlogColors.WHITE.rgb)),
                    text = "Sign Up"
                )
            }

            VerticalDivider(modifier = dividerStyle.then(Modifier.height(20.px)))

            Link(path = "sign-in") {
                SpanText(modifier = textStyle, text = "I have a already account.")
            }

            VerticalDivider(modifier = dividerStyle.then(Modifier.height(20.px)))

            when(signUpState.value) {
                is State.Error -> register.isErrorExist = (signUpState.value as State.Error<Boolean>).exception.message ?: "Empty error message."
                is State.Loading -> Progress(attrs = Modifier.width(350.px).height(16.px).color(BlogColors.PRIMARY.rgb).toAttrs())
                is State.Success -> pageContext.router.navigateTo("home")
                else -> { /* NO-OP */ }
            }
        }
    }
}