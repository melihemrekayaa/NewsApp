package com.example.newsapp.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    readOnly: Boolean,
    onClick:() -> Unit,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    
}