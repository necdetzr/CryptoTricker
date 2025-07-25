package com.necdetzr.language.data

data class LanguageItem(
    val title:String,
    val checked:Boolean,
    val onClick:()->Unit
)