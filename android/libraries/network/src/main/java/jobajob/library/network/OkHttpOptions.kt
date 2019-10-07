package jobajob.library.network

import javax.inject.Inject

class OkHttpOptions @Inject constructor() {
    var connectTimeoutSeconds: Long = 10
    var readTimeoutSeconds: Long = 10
    var writeTimeoutSeconds: Long = 10
    var callTimeoutSeconds: Long = 0
}

