package com.phonemodels.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.phonemodels.presentation.ui.theme.Gold
import com.phonemodels.presentation.ui.theme.PhoneModelsAppTheme
import java.util.*


@ExperimentalCoilApi
@Composable
@Preview
fun ButtonView(text: String? = "Save") {
    PhoneModelsAppTheme {
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),

            onClick = {
                // do something here
            },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Gold
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = (text ?: "").uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black
            )
        }
    }
}


