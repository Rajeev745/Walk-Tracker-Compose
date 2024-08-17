package com.example.walktracker.presentation.input_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.walktracker.R
import com.example.walktracker.presentation.Dimens
import com.example.walktracker.presentation.Dimens.EditTextHeight
import com.example.walktracker.presentation.Dimens.ZeroDp

@Composable
fun GenderField(
    label: String,
    value: String,
    onClickListener: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.paddingMedium)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
        )

        Spacer(modifier = Modifier.height(Dimens.SpaceHeightSmall))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(EditTextHeight)
                .background(color = colorResource(id = R.color.edit_text_background), shape = RoundedCornerShape(Dimens.MediumCardCornerRadius))
                .border(
                    width = ZeroDp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(Dimens.MediumCardCornerRadius)
                )
                .clickable { onClickListener() },
            verticalAlignment = Alignment.CenterVertically, // Center vertically
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = value,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = Dimens.paddingMedium),
                style = MaterialTheme.typography.bodyMedium,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
        }

        Spacer(modifier = Modifier.height(Dimens.SpaceHeightExtraLarge))
    }
}