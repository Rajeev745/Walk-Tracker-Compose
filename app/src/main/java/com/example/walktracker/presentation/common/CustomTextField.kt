package com.example.walktracker.presentation.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.walktracker.R
import com.example.walktracker.presentation.Dimens

@Composable
fun CustomTextField(
    onValueChange: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    isError: Boolean,
    placeholder: String,
) {

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.EditTextHeight),
        value = text,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
        },
        keyboardOptions = keyboardOptions,
        isError = isError,
        colors = TextFieldDefaults.colors(
            focusedTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
            unfocusedTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
            focusedContainerColor = colorResource(id = R.color.edit_text_background),
            unfocusedContainerColor = colorResource(id = R.color.edit_text_background),
            cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = MaterialTheme.colorScheme.error
        ),
        shape = RoundedCornerShape(Dimens.MediumCardCornerRadius),
        singleLine = true
    )
}
