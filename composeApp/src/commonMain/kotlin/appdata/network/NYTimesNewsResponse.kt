package appdata.network

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NYTimesNewsResponse(
    @SerialName("results") val results: List<NYTimesNewsResult>,
    @SerialName("num_results") val numResults: Int
) {
    val data: List<NYTimesNewsResult>
        get() {
            // exclude results that have empty data which the api returns sometimes.
            return results.filter { it.multimedia != null && it.url != "null" }
        }
}

@Serializable
data class NYTimesNewsResult(
    @SerialName("section") val section: String,
    @SerialName("title") val title: String,
    @SerialName("abstract") val abstract: String,
    @SerialName("url") val url: String,
    @SerialName("byline") val byLine: String,
    @SerialName("updated_date") val updatedDate: String,
    @SerialName("multimedia") val multimedia: List<Multimedia>?,
    var savedForLater: Boolean = false
) {
    val date: String
        get() {
            val formattedDate: Instant = Instant.parse(updatedDate)

            val now = Clock.System.now()
            val duration = now - formattedDate

            val seconds = duration.inWholeSeconds
            val minutes = seconds / 60
            val hours = minutes / 60
            val days = hours / 24

            return when {
                seconds < 60 -> "just now"
                minutes < 60 -> "$minutes minute${if (minutes > 1) "s" else ""} ago"
                hours < 24 -> "$hours hour${
                    if (hours > 1) "s" else
                        ""
                } ago"

                else -> "$days day${if (days > 1) "s" else ""} ago"
            }
        }

    val imageUrl: String
        get() {
            return multimedia?.first { it.format == "threeByTwoSmallAt2X" }?.url ?: ""
        }
    val category: String
        get() {
            return section.uppercase()
        }
}

@Serializable
data class Multimedia(
    @SerialName("url") val url: String,
    @SerialName("format") val format: String
)

enum class NYTimesNewsCategory(
    val category: String
) {
    HOME("home"),
    WORLD("world"),
    SCIENCE("science"),
    ARTS("arts"),
}
