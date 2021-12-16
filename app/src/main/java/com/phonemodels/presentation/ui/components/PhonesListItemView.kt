package com.phonemodels.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.phonemodels.domain.entities.PhoneEntity
import com.phonemodels.presentation.ui.theme.PhoneModelsAppTheme

@ExperimentalCoilApi
@Composable
fun PhonesListItemView(phoneEntity: PhoneEntity?) {

    PhoneModelsAppTheme {
        Row(
            Modifier
                .fillMaxWidth()
                .height(250.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(phoneEntity?.image),
                contentDescription = null,
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .clip(CircleShape),

                contentScale = ContentScale.Fit
            )
            Column(
                Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    phoneEntity?.name ?: "",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    phoneEntity?.status ?: "",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}