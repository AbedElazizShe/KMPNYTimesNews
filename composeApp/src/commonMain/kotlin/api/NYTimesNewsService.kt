package api

import api.HttpRoutes.BASE_URL
import appdata.network.NYTimesNewsResponse
import com.example.kmpnytimesnews.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

object HttpRoutes {
    const val BASE_URL = "https://api.nytimes.com"
}

val ktorClient = HttpClient()


interface ApiRepository {
    suspend fun getNews(
        category: String,
        apiKey: String = BuildKonfig.NY_TIMES_API_KEY
    ): NYTimesNewsResponse
}

class NYTimesNewsService : ApiRepository {
    override suspend fun getNews(category: String, apiKey: String): NYTimesNewsResponse {
        val response = ktorClient.get("${BASE_URL}/svc/topstories/v2/$category.json?api-key=$apiKey")
        println(response.bodyAsText())
        return Json{ ignoreUnknownKeys = true }.decodeFromString<NYTimesNewsResponse>(response.bodyAsText())
    }

}