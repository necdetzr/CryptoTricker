package com.necdetzr.loodoscrypto.presentation.ui.main.pages.subpages.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import com.necdetzr.loodoscrypto.presentation.ui.main.components.CoinCard
import com.necdetzr.loodoscrypto.presentation.ui.main.components.DetailedCoinCard

@Composable
fun FavoritePage(navController: NavHostController,favoriteViewModel: FavoriteViewModel = hiltViewModel()){
    val coins by favoriteViewModel.favorites.collectAsState()
    val userId by favoriteViewModel.userId.collectAsState()

    LaunchedEffect(Unit) {
        favoriteViewModel.loadFavorites(userId.toString())
    }

    Column(
        modifier = Modifier.padding(12.dp),

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(
                onClick = {
                    navController.popBackStack()
                }

            ) {
                Icon(Icons.AutoMirrored.Default.ArrowBack,"back")
            }
            Spacer(Modifier.width(10.dp))
            Text("Favorite Coins", style = MaterialTheme.typography.headlineSmall)
        }
        Spacer(Modifier.height(20.dp))
        Box(
            modifier = Modifier.fillMaxWidth().height(400.dp)
        ) {
            LazyColumn(

            ) {
                items(coins.size) {
                    DetailedCoinCard(coin = coins[it], navController = navController)
                    Spacer(Modifier.height(16.dp))
                }
            }
        }



    }
}