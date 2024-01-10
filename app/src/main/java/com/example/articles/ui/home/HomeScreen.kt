package com.example.articles.ui.home

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.articles.domain.Default
import com.example.articles.domain.Empty
import com.example.articles.domain.Error
import com.example.articles.domain.Loading
import com.example.articles.domain.Success

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val articles by viewModel.articles.collectAsStateWithLifecycle()
    val context = LocalContext.current
    when (articles) {
        is Default -> {}
        is Empty -> {}
        is Error -> {}
        is Loading -> {}
        is Success -> {
            Toast.makeText(context, "Veriler başarılı", Toast.LENGTH_SHORT).show()
        }
    }

}