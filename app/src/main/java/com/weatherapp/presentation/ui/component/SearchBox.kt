package com.weatherapp.presentation.ui.component
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.weatherapp.R
import com.weatherapp.presentation.ui.theme.LightText
import com.weatherapp.presentation.ui.theme.SearchShape
import com.weatherapp.presentation.ui.theme.White

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBox(
    onSearchChange: ((String) -> Unit)? = null,
    actionSearch: (() -> Unit)? = null,
    keyboardController: SoftwareKeyboardController? = null,

) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = {
            text = it
            onSearchChange?.invoke(it)
        },
        label = null,
        placeholder = { Text( stringResource(id = R.string.search_by)) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(54.dp)
            .clip(SearchShape.medium),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "",
                tint = LightText,
                modifier = Modifier.size(20.dp)
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                actionSearch?.invoke()
                keyboardController?.hide()
            }
        )
    )
}