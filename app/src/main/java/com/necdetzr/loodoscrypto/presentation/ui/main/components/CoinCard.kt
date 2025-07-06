package com.necdetzr.loodoscrypto.presentation.ui.main.components

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
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.ImageResult
import com.necdetzr.loodoscrypto.domain.model.Coin
import com.necdetzr.loodoscrypto.presentation.theme.Gray
import com.necdetzr.loodoscrypto.presentation.theme.LightGray
import com.necdetzr.loodoscrypto.utils.FormatFunctions

@Composable
fun CoinCard(
    coin: Coin,
    onNavigateToCoin:(String)->Unit
){


        Card(
            modifier = Modifier.fillMaxWidth()
                .clickable(
                    onClick = {
                        onNavigateToCoin(coin.id)
                    }
                ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White //Material Theme doesn't work for White Background Color??
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
                    Text("$${coin.currentPrice}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Spacer(Modifier.weight(1f))
                val formattedChange = FormatFunctions.formatChange(coin.priceChangePercentage24h)
                val color = if (coin.priceChangePercentage24h >= 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                Text(formattedChange, style = MaterialTheme.typography.bodyMedium, color = color)


            }
        }



}