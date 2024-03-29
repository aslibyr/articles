package com.example.articles.custom.top_bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.articles.utils.theme.Pink40

@Composable
fun TopBarView(
    model: TopBarComponentUIModel,
    showRoundedCorner: Boolean = false,
    onBackClick: () -> Unit = {},
    onEndIconClick: () -> Unit = {}
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = if (showRoundedCorner) 12.dp else 0.dp)
            .padding(horizontal = if (showRoundedCorner) 8.dp else 0.dp)
            .clip(RoundedCornerShape(size = if (showRoundedCorner) 20.dp else 0.dp))
            .height(50.dp)

    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (model.shouldShowBackIcon) {
                Icon(
                    imageVector = Icons.Filled.NavigateBefore, "",
                    modifier = Modifier
                        .size(34.dp)
                        .padding(start = 16.dp, top = 16.dp)
                        .clickable {
                            onBackClick()
                        }
                        .height(IntrinsicSize.Min),
                    tint = Pink40

                )
            }

            Text(
                text = model.title,
                textAlign = TextAlign.Center,
                color = Pink40,
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    fontSize = 14.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            model.endIcon?.let {
                Icon(
                    imageVector = it, "",
                    modifier = Modifier
                        .size(36.dp)
                        .padding(end = 16.dp, top = 16.dp)
                        .clickable {
                            onEndIconClick()
                        }
                        .align(Alignment.TopEnd),
                    tint = Pink40
                )
            }
        }
    }
}

