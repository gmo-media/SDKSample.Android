package media.gmo.sdksample

import android.content.Context
import android.content.Intent

public class PlayBox {
    companion object {
        var isInitialized: Boolean = false
            private set

        fun init(key: String, options: Options?, listener: PlayBoxInitializationListener) {
            if (options?.getUserId() == "error") {
                isInitialized = false
                listener.onError(Exception("Error"))
                return
            }

            isInitialized = true
            listener.onSuccess()
        }

        fun getCatalogIntent(context: Context): Intent {
            if (!isInitialized) {
                throw NotInitializedException()
            }
            return Intent(context, CatalogActivity::class.java)
        }
    }

    class Options {
        private var userId: String? = null

        fun setUserId(userId: String): Options {
            this.userId = userId
            return this
        }

        fun getUserId(): String? {
            return userId
        }
    }

    open class PlayBoxInitializationListener {
        open fun onSuccess() {}
        open fun onError(error: Throwable) {}
    }
}