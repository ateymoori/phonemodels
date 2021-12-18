package com.phonemodels.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.phonemodels.domain.entities.PhoneEntity
import com.phonemodels.domain.entities.SamplePhoneEntity
import com.phonemodels.presentation.ui.theme.PhoneModelsAppTheme

@ExperimentalCoilApi
@Composable
@Preview
fun PhoneDetailsView(phoneEntity: PhoneEntity? = SamplePhoneEntity) {

    PhoneModelsAppTheme {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = rememberImagePainter(phoneEntity?.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .weight(2f),
                contentScale = ContentScale.Fit
            )

            Card(
                shape = RoundedCornerShape(6.dp),
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 3.dp,
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()
                    .height(140.dp)
                    .padding(12.dp)
            ) {
                Column(
                    Modifier.padding(8.dp)
                ) {
                    Text(
                        text = phoneEntity?.name ?: "",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    RatingBarView(phoneEntity?.review)
                    Column(modifier = Modifier.weight(2f)) {
                        Text(
                            modifier = Modifier.padding(top = 12.dp),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append("Availability : ")
                                }
                                append(phoneEntity?.status ?: "")
                            })
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append("Resolution : ")
                                }
                                append(phoneEntity?.size ?: "")
                            })
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append("OS : ")
                                }
                                append(phoneEntity?.os ?: "")
                            })
                    }

                    Text(
                        text = "${phoneEntity?.currency} ${phoneEntity?.price}",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    ButtonView("ADD TO CART")
                }
            }
        }
    }
}