package com.example.articles.custom.bottom_sheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.articles.R
import com.example.articles.utils.TabItemModel
import com.example.articles.utils.theme.FontType
import com.example.articles.utils.theme.Pink40
import com.example.articles.utils.theme.Pink80

@ExperimentalMaterial3Api
@Composable
fun BottomModalSheet(
    onDismissRequest: () -> Unit,
    items: List<TabItemModel>,
    itemClicked: (String) -> Unit = {},
    selectedCountry: String?
) {
    val context = LocalContext.current
    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        modifier = Modifier
    ) {

        LazyColumn {
            items(items) { countryItem ->
                val selected = countryItem.id == selectedCountry
                val icon = when (countryItem.id) {
                    "tr" -> {
                        painterResource(id = R.drawable.turkiye)
                    }

                    "de" -> {
                        painterResource(id = R.drawable.germany)
                    }

                    "us" -> {
                        painterResource(id = R.drawable.united_states)
                    }

                    else -> {
                        painterResource(id = R.drawable.united_kingdom)
                    }

                }
                ModalBottomSheetItem(onItemClicked = {
                    itemClicked(countryItem.id)
                    onDismissRequest()
                }, title = countryItem.title, icon = icon, selected)
            }
        }
    }
}

@Composable
fun ModalBottomSheetItem(
    onItemClicked: () -> Unit,
    title: String,
    icon: Painter,
    selected: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp)
            .clickable {
                onItemClicked.invoke()
            },
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(Pink80)

    ) {
        Row(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
            ) {

                Image(
                    painter = icon,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 11.sp
                    ),
                    fontFamily = FontType.workSansRegular
                )
            }
            AnimatedVisibility(visible = selected) {
                Icon(
                    Icons.Outlined.Check,
                    contentDescription = "",
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 16.dp),
                    tint = Pink40

                )
            }

        }
    }
}