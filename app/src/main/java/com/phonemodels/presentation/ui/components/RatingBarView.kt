package com.phonemodels.presentation.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.phonemodels.presentation.ui.theme.Gold
import com.phonemodels.presentation.ui.theme.PhoneModelsAppTheme

const val defaultMaxLength = 5
const val defaultRate = 1

@ExperimentalCoilApi
@Composable
@Preview
fun RatingBarView(rate: Int? = defaultMaxLength, maxLength: Int? = defaultMaxLength) {
    PhoneModelsAppTheme {
        Row {
            for (i in 1..(maxLength ?: defaultMaxLength)) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    tint = (if (i <= rate ?: defaultRate) Gold else Gray),
                    contentDescription = null
                )
            }
        }
    }
}