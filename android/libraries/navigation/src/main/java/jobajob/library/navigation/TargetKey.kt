package jobajob.library.navigation

sealed class TargetKey {
    class Login(val email: String = ""): TargetKey()
    object Favorites : TargetKey()
    object Dashboard : TargetKey()
}