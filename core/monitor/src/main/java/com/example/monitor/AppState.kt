package com.example.monitor
import androidx.compose.runtime.staticCompositionLocalOf
import com.necdetzr.common.model.DialogType
import com.necdetzr.common.model.FriendlyMessageDTO
import com.necdetzr.common.model.ToastModel
sealed interface AppEvent{

    data object UnAuthorized : AppEvent

    data class ShowMessage(
        val dialogType: DialogType? = null,
        val friendlyMessage: FriendlyMessageDTO?,
        val appEventType : AppEventType = AppEventType.MODAL,
        val throwable: Throwable? = null,
        val toastMessage: ToastModel? = null
    ) : AppEvent

    enum class AppEventType{
        NONE,
        MODAL,
        TOAST

    }


}
val LocalAppStateMonitor =
    staticCompositionLocalOf<AppStateMonitor>{error("No AppStateMonitor Provided")}