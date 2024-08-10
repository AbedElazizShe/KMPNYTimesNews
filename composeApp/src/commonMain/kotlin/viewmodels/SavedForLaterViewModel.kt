package viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import appdata.local.entity.NewsEntity
import appdata.repository.NYTimesNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SavedForLaterViewModel(): ViewModel(), KoinComponent {

    private val repository: NYTimesNewsRepository by inject()

    val newsList: Flow<List<NewsEntity>> =
        repository.getSavedNews()

    fun deleteNews(newsEntity: NewsEntity) {
        viewModelScope.launch {
            repository.deleteNews(newsEntity)
        }
    }
}
