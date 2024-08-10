package appdata.repository

import api.NYTimesNewsService
import appdata.local.dao.NewsDao
import appdata.local.entity.NewsEntity
import appdata.network.NYTimesNewsResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NYTimesNewsRepository (
    private val service: NYTimesNewsService,
    private val newsDao: NewsDao
) {
    fun getNYTimesNewsStream(category: String): Flow<List<NYTimesNewsResult>> = flow {
        newsDao.getNewsTitles().collect { titles ->
            val results = service.getNews(category).data
            results.forEach {
                if (titles.contains(it.title)) {
                    it.savedForLater = true
                }
            }
            val sorted = results.sortedByDescending { it.updatedDate }
            emit(sorted)
        }

    }

    fun getSavedNews(): Flow<List<NewsEntity>> = flow {
        newsDao.getNews().collect { news ->
            val sorted = news.sortedByDescending { it.updatedDate }
            emit(sorted)
        }

    }

    suspend fun saveNews(news: NYTimesNewsResult) {
        newsDao.insertNews(news.toEntity())
    }

    suspend fun deleteNews(title: String) {
        newsDao.deleteNews(title)
    }

    suspend fun deleteNews(newsEntity: NewsEntity) {
        newsDao.deleteNews(newsEntity)
    }
}

fun NYTimesNewsResult.toEntity(): NewsEntity {
    return NewsEntity(
        this.section,
        this.title,
        this.abstract,
        this.url,
        this.byLine,
        this.updatedDate,
        this.date,
        this.imageUrl,
    )
}
