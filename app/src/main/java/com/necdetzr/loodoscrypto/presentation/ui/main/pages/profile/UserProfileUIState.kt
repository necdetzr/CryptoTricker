package com.necdetzr.loodoscrypto.presentation.ui.main.pages.profile

data class UserProfileUIState (
    val userName:String = "Guest User",
    val email:String = "Guest Email",
    val darkMode:Boolean=false,
    val adviceTest:String="Advice Text"
)