package com.fa.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import com.fa.blogmultiplatform.models.Theme
import com.fa.blogmultiplatform.util.Resources
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import org.jetbrains.compose.web.css.px

@Page
@Composable
fun LoginScreen() {
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
        }
    }
}