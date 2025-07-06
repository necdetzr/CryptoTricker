import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {

    private val crashlytics = FirebaseCrashlytics.getInstance()

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority >= Log.ERROR) {
            crashlytics.log(message)
            if (t != null) {
                crashlytics.recordException(t)
            } else {
                crashlytics.recordException(Throwable(message))
            }
        } else {
            when (priority) {
                Log.VERBOSE -> Timber.v(message)
                Log.DEBUG -> Timber.d(message)
                Log.INFO -> Timber.i(message)
                Log.WARN -> Timber.w(message)
                else -> {}
            }
        }
    }
}
