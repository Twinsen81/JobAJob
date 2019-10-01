package jobajob.library.utils

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Utils @Inject constructor() {
    fun generateRandomUserId() : String =
        UUID.randomUUID().toString().replace("-", "")
}