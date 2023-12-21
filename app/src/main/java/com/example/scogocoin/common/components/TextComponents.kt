package com.example.scogocoin.common.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun HeadingTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    fontStyle: FontStyle = FontStyle.Normal,
    textAlign: TextAlign = TextAlign.Start,
) {

    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = textAlign,
        color = textColor,
        fontStyle = fontStyle,
        fontWeight = FontWeight.Normal,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun TitleTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    fontStyle: FontStyle = FontStyle.Normal,
    textAlign: TextAlign = TextAlign.Start,
) {

    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleMedium,
        textAlign = textAlign,
        color = textColor,
        fontStyle = fontStyle,
        fontWeight = FontWeight.Normal,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun BodyTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    fontStyle: FontStyle = FontStyle.Normal,
    textAlign: TextAlign = TextAlign.Start,
) {

    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = textAlign,
        color = textColor,
        fontStyle = fontStyle,
        fontWeight = FontWeight.Normal,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun LabelTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    fontStyle: FontStyle = FontStyle.Normal,
    textAlign: TextAlign = TextAlign.Start,
) {

    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelMedium,
        textAlign = textAlign,
        color = textColor,
        fontStyle = fontStyle,
        fontWeight = FontWeight.Normal,
        overflow = TextOverflow.Ellipsis
    )
}