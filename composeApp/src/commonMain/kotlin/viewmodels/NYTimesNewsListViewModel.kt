package viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import appdata.network.NYTimesNewsResult
import appdata.repository.NYTimesNewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NYTimesNewsListViewModel: ViewModel(), KoinComponent {

    private val repository: NYTimesNewsRepository by inject()

    private val _news = MutableStateFlow<List<NYTimesNewsResult>?>(null)
    val news: StateFlow<List<NYTimesNewsResult>?>
        get() = _news

    private val _isLoading = MutableStateFlow(true)
    val isLoading
        get() = _isLoading

    fun refreshData(category: String) {
        _isLoading.value = true
        viewModelScope.launch {
            _news.value = emptyList()
            try {
                _news.value =
                    repository.getNYTimesNewsStream(category).first()

                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                e.printStackTrace()
            }
        }
    }

    fun saveNews(nyTimesNewsResult: NYTimesNewsResult) {
        viewModelScope.launch {
            repository.saveNews(nyTimesNewsResult)
            val data = updateSavedForLaterState(nyTimesNewsResult, true)
            _news.update { data }
        }
    }

    fun deleteNews(nyTimesNewsResult: NYTimesNewsResult) {
        viewModelScope.launch {
            repository.deleteNews(nyTimesNewsResult.title)
            // TODO: Is there a better way?
            val data = updateSavedForLaterState(nyTimesNewsResult, false)
            _news.update { data }
        }
    }

    private fun updateSavedForLaterState(
        nyTimesNewsResult: NYTimesNewsResult,
        savedForLater: Boolean
    ): List<NYTimesNewsResult>? {
        return _news.value?.map {
            if (it.title == nyTimesNewsResult.title) {
                it.copy(savedForLater = savedForLater)
            } else {
                it
            }
        }
    }
}
