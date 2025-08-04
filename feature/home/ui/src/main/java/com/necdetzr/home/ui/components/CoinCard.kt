package com.necdetzr.home.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.ui.util.FormatFunctions
import com.necdetzr.home.component.domain.data.Coin


@Composable
fun CoinCard(
    coin: Coin,
    onNavigateToCoin:(String)->Unit
) {


    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable(
                onClick = {
                    onNavigateToCoin(coin.id)
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(coin.image)
                        .crossfade(true)
                        .build()

                ),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(30.dp)
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Text(coin.symbol.uppercase(), style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(2.dp))
                Text(
                    "$${coin.currentPrice}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.weight(1f))
            val formattedChange = FormatFunctions.formatChange(coin.priceChangePercentage24h)
            val color =
                if (coin.priceChangePercentage24h >= 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            Text(formattedChange, style = MaterialTheme.typography.bodyMedium, color = color)


        }
    }
}