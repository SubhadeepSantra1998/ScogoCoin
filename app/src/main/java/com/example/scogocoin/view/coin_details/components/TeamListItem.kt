package com.example.scogocoin.view.coin_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.scogocoin.common.components.BodyTextComponent
import com.example.scogocoin.common.components.LabelTextComponent
import com.example.scogocoin.data.model.TeamModel

@Composable
fun TeamListItem(
    teamMember: TeamModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        BodyTextComponent(text = teamMember.name)

        Spacer(modifier = Modifier.height(4.dp))
        LabelTextComponent(
            text = teamMember.position,
            fontStyle = FontStyle.Italic
        )
    }
}