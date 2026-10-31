package fr.danersharifi.marslens.data.model

// Sol ranges provided by the prof during the évaluation
enum class Mission(
    val apiValue: String,
    val maxSol: Int,
) {
    CURIOSITY(
        apiValue = "msl",
        maxSol = 5058,
    ),

    INSIGHT(
        apiValue = "insight",
        maxSol = 1436,
    ),
}
