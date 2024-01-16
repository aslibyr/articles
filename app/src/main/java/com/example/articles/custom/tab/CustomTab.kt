package com.example.articles.custom.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.articles.utils.noRippleClick
import com.example.articles.utils.theme.FontType
import com.example.articles.utils.theme.Pink40

@Composable
fun CustomTab(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier
) {

    Box(
        modifier = modifier
            .noRippleClick { onClick() }
            .size(40.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            maxLines = 1,
            fontFamily = FontType.workSansRegular,
            color = Pink40,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}