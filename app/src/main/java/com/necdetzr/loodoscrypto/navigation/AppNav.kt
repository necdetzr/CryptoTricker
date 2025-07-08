package com.necdetzr.loodoscrypto.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.necdetzr.loodoscrypto.data.datastore.DataStoreManager
import com.necdetzr.loodoscrypto.presentation.ui.auth.AuthViewModel
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.LoginPage
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.RegisterPage
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.WelcomePage
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.home.HomePage
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.market.MarketPage
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.profile.ProfilePage
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.search.SearchPage
import kotlinx.coroutines.flow.first
import timber.log.Timber


@Composable
fun AppNav(authViewModel: AuthViewModel, contentPadding: PaddingValues,dataStoreManager: DataStoreManager) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        val isRemembered = dataStoreManager.rememberMe.first()
        val currentUser = FirebaseAuth.getInstance().currentUser

//Recomposition..

        if(isRemembered && currentUser != null){
            Timber.d("Remembered = $isRemembered")
            navController.navigate("main"){
                popUpTo(0)

            }
        }else{
            navController.navigate("auth"){
                popUpTo(0)
            }
        }
    }


    NavHost(
        navController = navController,
        startDestination = "splash",
    ) {

        navigation(startDestination = "welcome", route = "auth") {
            composable("welcome") {
                WelcomePage(navController)
            }
            composable("login") {
                LoginPage(navController, authViewModel,dataStoreManager)
            }
            composable("register") {
                RegisterPage(navController)
            }
        }


        composable("main") {
            MainNav(

                {
                    navController.navigate("auth"){
                        popUpTo(0)
                    }
                }
            )
        }
        composable("splash") {

        }

    }
}

