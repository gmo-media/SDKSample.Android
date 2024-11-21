package media.gmo.sdksample

class PlayBox {
    companion object {
        fun init(key: String, options: Options?): Boolean {
            return true
        }
    }

    class Options {
        private var userId: String? = null

        fun setUserId(userId: String): Options {
            this.userId = userId
            return this
        }
    }
}