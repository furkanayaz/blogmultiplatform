package org.fa.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.dom.Text

@Page("reset-my-password")
@Composable
fun ResetMyPassword() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("An email has sent your address.")
    }
}