package com.necdetzr.common.model

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString


data class ToastModel(
    var icon:ImageVector,
    var title:String?,
    var description:String?,
    var toastType: ToastType? = null,
    var keyValue:String? = null,
    var descAnnotatedString: AnnotatedString? = null,
)

enum class ToastType{
    ERROR,
    SUCCESS,
    UNKNOWN

}