package org.fa.blogmultiplatform.pages.admin.home

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.dom.Text

@Page("home")
@Composable
fun HomePage() {
    Box {
        Text("You are in home page")
    }
}