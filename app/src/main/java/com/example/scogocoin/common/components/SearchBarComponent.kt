package com.example.scogocoin.common.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SearchBarComponent(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String,
    onSearchClick: () -> Unit,
) {

    var isTextEmpty by remember { mutableStateOf( searchQuery.isEmpty()) }
    OutlinedTextField(
        modifier = modifier,
        value = searchQuery,
        onValueChange = {
            onSearchQueryChange(it)
            isTextEmpty = it.isEmpty()
        },
        singleLine = true,
        trailingIcon = {
            if (isTextEmpty) {
                SearchTrailingIcon(onSearchClick)
            }else{
                ClearTextTrailingIcon {
                    onSearchQueryChange("")
                    isTextEmpty = true
                }
            }
        },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClick()
            }
        ),

    )
}


@Composable
fun SearchTrailingIcon(onSearchClick: () -> Unit) {
    IconToggleButton(
        checked = false,
        onCheckedChange = { onSearchClick() }
    ) {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
    }
}


@Composable
fun ClearTextTrailingIcon(onClearClick: () -> Unit) {
    IconToggleButton(
        checked = false,
        onCheckedChange = { onClearClick() }
    ) {
        Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
    }
}