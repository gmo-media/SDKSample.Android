package media.gmo.sdksample

import android.app.Application
import android.content.Context
import android.content.Intent

class PlayBox {
    companion object {
        fun init(key: String, options: Options?, listener: PlayBoxInitializationListener) {
            if (options?.getUserId() == "error") {
                listener.onError(Exception("Error"))
                return
            }
            listener.onSuccess()
        }

        fun getCatalogIntent(context: Context): Intent {
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