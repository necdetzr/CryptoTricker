package com.example.favorite.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.favorite.component.CoinCard
import com.example.ui.component.CryptoAppBar
import com.necdetzr.designsystem.R
import com.necdetzr.home.component.domain.data.Coin


@Composable
fun FavoritePage(
    onNavigateToBack: () -> Unit,
    coins: List<Coin>,
    onNavigateToCoin: (String) -> Unit,

    ) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CryptoAppBar(
                isLeadingButton = true,
                title = stringResource(R.string.favorite_list),
                onClick = onNavigateToBack
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp),

            ) {
            Spacer(Modifier.height(56.dp))
            Text(stringResource(R.string.my_fav_list), style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(6.dp))
            Text("${coins.size} adet favori", style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                LazyColumn {

                    items(coins) { coin ->

                        CoinCard(coin = coin, onNavigateToCoin = onNavigateToCoin)
                        Spacer(Modifier.height(16.dp))
                    }
                }
            }


        }
    }

}