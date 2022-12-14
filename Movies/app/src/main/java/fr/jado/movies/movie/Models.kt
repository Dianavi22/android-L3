package fr.jado.movies.movie

const val DEBUG = true
data class Movies(
    val id: Int,
    val name: String,
    val genre: Genre,
    val datesortie: String,
    val coverID: Int,
)

enum class Genre {
    sf, comedy
}








