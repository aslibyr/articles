package com.example.articles.custom.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.articles.utils.theme.Pink40


@Composable
fun BoxScope.ListResetButton(
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(
            20.dp
        ),
        modifier = Modifier
            .width(60.dp)
            .height(60.dp)
            .padding(end = 16.dp, bottom = 16.dp)
            .align(Alignment.BottomEnd)
            .fillMaxSize()
            .clickable {
                // Kullanıcı düğmeye bastığında listenin en üstüne kaydırma işlemi
                onClick.invoke()
            },
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(Pink40)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.Default.ArrowUpward,
                contentDescription = "Scroll to Top",
                modifier = Modifier
                    .size(32.dp),
                tint = Color.White
            )
        }
    }
}