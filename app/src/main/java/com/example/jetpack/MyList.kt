package com.example.jetpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpack.model.CardsListModel

class MyList {
    @Composable
    fun ListItem(data: CardsListModel) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.Padding.padding_20),
            elevation = CardDefaults.cardElevation(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = data.img),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .width(120.dp)
                        .padding(Dimensions.Padding.padding_10)
                        .clip(RoundedCornerShape(Dimensions.Size.size_16)),
                )
                Column {
                    Text(text = data.name.toString(), fontSize = Dimensions.TextSize.size_18)
                    Text(
                        text = data.price.toString() + "Cом",
                        fontSize = Dimensions.TextSize.size_16
                    )
                }
            }
        }
    }
}