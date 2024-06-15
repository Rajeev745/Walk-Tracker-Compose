package com.example.walktracker.presentation.common

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.walktracker.R
import com.example.walktracker.presentation.Dimens.CornerRadius
import com.example.walktracker.presentation.Dimens.EditTextHeight
import com.example.walktracker.presentation.Dimens.HorizontalPaddingMedium

@Composable
fun CustomTextField(
    onValueChange: (String) -> Unit,
    text: String,
    onClick: () -> Unit,
    keyboardOptions: KeyboardOptions,
    isError: Boolean,
    readOnly: Boolean = false,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(EditTextHeight)
            .clickable { Log.d("Clicked", "Clicked Check") },
        contentAlignment = Alignment.Center,
    ) {

        TextField(
            value = text,
            onValueChange = onValueChange,
            placeholder = { Text(text = "Please enter your name") },
            modifier = Modifier
                .fillMaxSize()
                .clickable {  },
            colors = TextFieldDefaults.colors(
                focusedTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                focusedContainerColor = colorResource(id = R.color.edit_text_background),
                unfocusedContainerColor = colorResource(id = R.color.edit_text_background),
                disabledContainerColor = colorResource(id = R.color.edit_text_background),
                cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = MaterialTheme.colorScheme.error,
            ),
            shape = RoundedCornerShape(CornerRadius),
            keyboardOptions = keyboardOptions,
            isError = isError,
            readOnly = readOnly
        )
    }
}