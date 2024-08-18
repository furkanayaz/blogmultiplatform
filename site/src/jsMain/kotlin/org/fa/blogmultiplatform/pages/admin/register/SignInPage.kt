package org.fa.blogmultiplatform.pages.admin.register

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
import com.varabyte.kobweb.silk.components.forms.Checkbox
import com.varabyte.kobweb.silk.components.navigation.Link
import kotlinx.browser.document
import kotlinx.coroutines.launch
import org.fa.blogmultiplatform.util.UiState
import org.jetbrains.compose.web.dom.Progress
import org.w3c.dom.HTMLInputElement

@Page("/admin/sign-up")
@Composable
fun LoginPage() {
    val pageContext = rememberPageContext()
    val scope = rememberCoroutineScope()

    val registerRepo = RegisterRepo()

    val signUpState = registerRepo.signUpFlow.collectAsState()

    var isRememberMe by remember {
        mutableStateOf(false)
    }

    var isFocusedSignIn by remember {
        mutableStateOf(false)
    }

    var isErrorExist by remember {
        mutableStateOf("")
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

            Input(type = InputType.Email, attrs = inputStyle.then(Modifier.id(Resources.ID.INPUT_EMAIL)).toAttrs {
                attr("placeholder", "Enter your email")
            })

            if (isErrorExist.isNotEmpty()) {
                SpanText(
                    modifier = Modifier.width(350.px).padding(leftRight = 16.px, topBottom = 12.px)
                        .background(BlogColors.LT_RED.rgb).color(BlogColors.WHITE.rgb), text = isErrorExist
                )
            } else {
                VerticalDivider(modifier = dividerStyle.then(Modifier.height(12.px)))
            }

            Input(type = InputType.Password, attrs = inputStyle.then(Modifier.id(Resources.ID.INPUT_PASSWORD)).toAttrs {
                attr("placeholder", "Enter your password")
            })

            VerticalDivider(modifier = dividerStyle.then(Modifier.height(12.px)))

            Checkbox(checked = isRememberMe, onCheckedChange = {
                isRememberMe = it
            }, content = {
                SpanText(modifier = textStyle, text = "Remember Me")
            })

            VerticalDivider(modifier = dividerStyle.then(Modifier.height(12.px)))

            Button(
                attrs = buttonStyle.then(Modifier.onMouseOver {
                    isFocusedSignIn = true
                }.onMouseOut { isFocusedSignIn = false }.onClick {
                    val email = document.getElementById(Resources.ID.INPUT_EMAIL) as HTMLInputElement
                    val password = document.getElementById(Resources.ID.INPUT_PASSWORD) as HTMLInputElement

                    if(email.value.isEmpty() || password.value.isEmpty()) return@onClick

                    scope.launch {
                        registerRepo.signUp(email.value, password.value)
                    }
                }).toAttrs()
            ) {
                SpanText(
                    modifier = textStyle.then(Modifier.color(if (isFocusedSignIn) BlogColors.PRIMARY.rgb else BlogColors.WHITE.rgb)),
                    text = "Sign Up"
                )
            }

            VerticalDivider(modifier = dividerStyle.then(Modifier.height(20.px)))

            Link(modifier = textStyle, path = "reset-my-password", text = "Reset My Password")

            VerticalDivider(modifier = dividerStyle.then(Modifier.height(20.px)))

            when(signUpState.value) {
                is UiState.Idle -> { /* NO-OP */ }
                is UiState.Error -> isErrorExist = (signUpState.value as UiState.Error<Boolean>).exception.message ?: "Empty error message."
                is UiState.Loading -> Progress(attrs = Modifier.width(350.px).height(16.px).color(BlogColors.PRIMARY.rgb).toAttrs())
                is UiState.Success -> pageContext.router.navigateTo("home")
            }
        }
    }
}