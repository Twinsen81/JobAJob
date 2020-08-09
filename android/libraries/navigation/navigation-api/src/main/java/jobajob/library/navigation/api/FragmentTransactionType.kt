package jobajob.library.navigation.api

/**
 * Defines the fragment transaction type that was executed by the navigator
 */
enum class FragmentTransactionType {

    /**
     * A fragment is being added to the back stack (push operation)
     */
    ADD,

    /**
     * A fragment is being removed from the back stack (pop operation)
     */
    GO_BACK,

    /**
     * A fragment is being replaced with a new one
     */
    REPLACE
}