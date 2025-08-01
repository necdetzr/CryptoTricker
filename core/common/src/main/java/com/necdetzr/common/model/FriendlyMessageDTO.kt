package com.necdetzr.common.model

data class FriendlyMessageDTO(
    val title:String?,
    val message:String?,
    val cancelable:Boolean?,
    val buttonPositive:String?,
    val buttonNegative:String?,
    val buttonNeutral:String?,
    val keyValue:String? = null,
    val keyMessageValue:String? = null,
    val positiveButtonClick: (()-> Unit)? = null,
    val negativeButtonClick:(()->Unit)? = null,
    val imageUrl:String? = null
)