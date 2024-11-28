package media.gmo.sdksample

import android.content.Context

class PlayBox {
    companion object {
        fun init(key: String, options: Options?, listener: PlayBoxInitialisationListener) {
            if (options?.getUserId() == "error") {
                listener.onError(Exception("Error"))
                return
            }
            listener.onInitialised()
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

    open class PlayBoxInitialisationListener {
        open fun onInitialised() {}
        open fun onError(error: Throwable) {}
    }
}