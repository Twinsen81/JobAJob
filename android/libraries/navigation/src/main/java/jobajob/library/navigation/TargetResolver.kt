package jobajob.library.navigation

import android.content.Context

interface TargetResolver<in Key: TargetKey> {
    fun getTarget(context: Context, key: Key): Target
}
