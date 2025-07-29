package com.necdetzr.home.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.component.CustomTextField
import com.example.ui.component.LinearProgressBar
import com.example.ui.component.Section
import com.necdetzr.home.component.domain.data.Coin
import com.necdetzr.home.ui.components.CoinCard


@Composable
fun HomePage(
    onNavigateToSearch:()->Unit,
    onNavigateToMarket:()->Unit,
    onNavigateToFavorite:()->Unit,
    onNavigateToCoin:(String)->Unit,
    isLoading:Boolean,
    isError:Boolean,
    coins:List<Coin>
){
    Scaffold() { padding->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),

            ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Column(

                ) {
                    Text(
                        stringResource(com.necdetzr.designsystem.R.string.hi_there),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        stringResource(com.necdetzr.designsystem.R.string.which_crypto),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.width(260.dp)
                    )
                }

            }
            Spacer(modifier = Modifier.height(32.dp))
            Section(firstTitle = stringResource(com.necdetzr.designsystem.R.string.search_crypto_title))
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                modifier = Modifier.clickable(
                    onClick = {
                        onNavigateToSearch()
                    }
                ),
                icon = Icons.Default.Search,
                enabled = false,
                value = "",
                placeholder = stringResource(com.necdetzr.designsystem.R.string.search_crypto_placeholder),
                onValueChange = {},

                )
            Spacer(Modifier.height(32.dp))
            Section(firstTitle = stringResource(com.necdetzr.designsystem.R.string.favorite_list))
            Spacer(Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            onNavigateToFavorite()
                        }
                    )
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.surface,
                            shape = MaterialTheme.shapes.medium
                        )

                ) {
                    Text(
                        stringResource(com.necdetzr.designsystem.R.string.star_emoji),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
                    )

                }
                Spacer(Modifier.width(10.dp))
                Column(
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        stringResource(com.necdetzr.designsystem.R.string.my_fav_list),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        stringResource(com.necdetzr.designsystem.R.string.fav_list_subtext),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

            }
            Spacer(Modifier.height(32.dp))
            Section(
                firstTitle = stringResource(com.necdetzr.designsystem.R.string.popular_cryptos),
                lastTitle = stringResource(com.necdetzr.designsystem.R.string.last_24h)
            )
            Box(modifier = Modifier
                .height(280.dp)
                .fillMaxWidth()) {
                if (isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        LinearProgressBar()
                    }
                } else if (isError) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //TODO: ADD ERROR CARD
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.padding(vertical = 12.dp)
                    ) {
                        items(items = coins) { coin ->
                            Spacer(Modifier.height(20.dp))
                            CoinCard(coin, onNavigateToCoin)

                        }
                    }
                }

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    stringResource(com.necdetzr.designsystem.R.string.see_all),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable(
                        onClick = {
                            onNavigateToMarket()


                        }
                    )
                )

            }


        }
    }

}