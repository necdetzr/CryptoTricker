package com.necdetzr.loodoscrypto.presentation.ui.main.pages.home

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.necdetzr.loodoscrypto.R
import com.necdetzr.loodoscrypto.presentation.theme.Gray
import com.necdetzr.loodoscrypto.presentation.ui.components.CustomTextField
import com.necdetzr.loodoscrypto.presentation.ui.components.CoinSlider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.necdetzr.loodoscrypto.presentation.theme.Blue
import com.necdetzr.loodoscrypto.presentation.ui.components.LinearProgressBar
import com.necdetzr.loodoscrypto.presentation.ui.main.components.CoinCard
import com.necdetzr.loodoscrypto.presentation.ui.main.components.ErrorCard
import com.necdetzr.loodoscrypto.presentation.ui.main.components.Section

@Composable
fun HomePage(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToSearch: ()-> Unit,
    onNavigateToFavorite:()-> Unit,
    onNavigateToMarket:()-> Unit,
    onNavigateToCoin:(String)->Unit,
)
{
    val topCoins by viewModel.topCoins.collectAsState()
    val state by viewModel.uiState.collectAsState()

    val coins = listOf(
        R.drawable.solana,
        R.drawable.bitcoin_svg,
        R.drawable.usdtlogo,
        R.drawable.dogecoin_logo,
        R.drawable.ethereum_logo_2014_svg,
        R.drawable.shiba_coin_logo
    )
    Column(
        modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()),

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(12.dp)
        ) {
            Column(

            ) {
                Text(stringResource(R.string.hi_there), style = MaterialTheme.typography.headlineMedium)
                Text(stringResource(R.string.which_crypto), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium, color = Color.Gray, modifier = Modifier.width(260.dp))
            }
            CoinSlider(coins)


        }
        Spacer(modifier = Modifier.height(32.dp))
        Section(firstTitle = stringResource(R.string.search_crypto_title))
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
            placeholder = stringResource(R.string.search_crypto_placeholder),
            onValueChange = {},

        )
        Spacer(Modifier.height(32.dp))
        Section(firstTitle = stringResource(R.string.favorite_list))
        Spacer(Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().clickable(
                onClick = {
                    onNavigateToFavorite()
                }
            )
        ) {
            Box(
                modifier = Modifier
                    .border(width = 1.dp, color = Color.LightGray, shape = MaterialTheme.shapes.medium)

            ){
                Text(stringResource(R.string.star_emoji), modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp))

            }
            Spacer(Modifier.width(10.dp))
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(stringResource(R.string.my_fav_list), style = MaterialTheme.typography.headlineSmall)
                Spacer(Modifier.height(6.dp))
                Text(stringResource(R.string.fav_list_subtext), style = MaterialTheme.typography.bodyMedium, color = Gray)
            }

        }
        Spacer(Modifier.height(32.dp))
        Section(firstTitle = stringResource(R.string.popular_cryptos), lastTitle = stringResource(R.string.last_24h))
        Box(modifier = Modifier.height(280.dp).fillMaxWidth()){
            if(state.isLoading){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    LinearProgressBar()
                }
            }else if(state.isError){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ErrorCard(

                    )
                }
            }else{
                LazyColumn(
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    items(items = topCoins) {coin->
                        Spacer(Modifier.height(20.dp))
                        CoinCard(coin,onNavigateToCoin)

                    } }
            }

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                stringResource(R.string.see_all),
                style = MaterialTheme.typography.headlineSmall,
                color = Blue,
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