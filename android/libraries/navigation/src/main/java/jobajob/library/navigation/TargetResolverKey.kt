package jobajob.library.navigation

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class TargetResolverKey(val value: KClass<out TargetKey>)