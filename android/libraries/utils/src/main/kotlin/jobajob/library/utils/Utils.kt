package jobajob.library.utils

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Utils @Inject constructor() {
    fun generateUserId() : String =
        UUID.randomUUID().toString().replace("-", "")
}