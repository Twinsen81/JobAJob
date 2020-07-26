package jobajob.library.utils

import java.util.*
import javax.inject.Inject

class Utils @Inject constructor() {
    fun generateRandomUserId() : String =
        UUID.randomUUID().toString().replace("-", "")
}