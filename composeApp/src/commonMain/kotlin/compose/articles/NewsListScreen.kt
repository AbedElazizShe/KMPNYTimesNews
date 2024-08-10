package compose.articles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import viewmodels.NYTimesNewsListViewModel

@OptIn(KoinExperimentalAPI::class)
@Composable
fun NewsListScreen(
    modifier: Modifier = Modifier,
    viewModel: NYTimesNewsListViewModel = koinViewModel(),
    category: String,
    onNewsClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
) {


    val isLoading = viewModel.isLoading.collectAsState()

    LaunchedEffect(category) {
        viewModel.refreshData(category)
    }

    val news by viewModel.news.collectAsState(initial = emptyList())

    if (isLoading.value) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    } else {
        NewsListView(
            news = news,
            onNewsClick = onNewsClick,
            onShareClick = onShareClick
        ) { item, isSave ->
            if (isSave) {
                viewModel.saveNews(item)
            } else {
                viewModel.deleteNews(item)
            }
        }
    }
}
