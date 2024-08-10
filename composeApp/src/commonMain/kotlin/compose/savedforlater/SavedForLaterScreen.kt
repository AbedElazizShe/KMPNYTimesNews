package compose.savedforlater

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.articles.NewsItemView
import appdata.local.entity.NewsEntity
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import viewmodels.SavedForLaterViewModel

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SavedForLaterScreen(
    viewModel: SavedForLaterViewModel = koinViewModel(),
    onUpClick: () -> Unit,
    onShareClick: (String) -> Unit,
    onNewsClick: (String) -> Unit,
) {

    val news by viewModel.newsList.collectAsState(initial = emptyList())
    SavedNewsList(
        news,
        onUpClick = onUpClick,
        onNewsClick = onNewsClick,
        onShareClick = onShareClick,
        onUnSaveClick = {
            viewModel.deleteNews(it)
        }
    )
}

@Composable
private fun SavedNewsList(
    news: List<NewsEntity>,
    onUpClick: () -> Unit,
    onNewsClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
    onUnSaveClick: (NewsEntity) -> Unit
) {

    Scaffold(
        topBar = {
            SavedForLaterTopAppbar(
                onUpClick = onUpClick
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = padding.calculateTopPadding(), bottom = 24.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(
                    items = news,
                    key = { it.title }
                ) {
                    NewsItemView(
                        newsItem = it,
                        onClick = onNewsClick,
                        onShareClick = onShareClick,
                        onUnSaveClick = onUnSaveClick
                    )
                }

            }
        }
    }

}

@Composable
private fun SavedForLaterTopAppbar(
    modifier: Modifier = Modifier,
    onUpClick: () -> Unit
) {
    TopAppBar (
        title = {

                Text(
                    text = "Saved For Later",
                    style = MaterialTheme.typography.h5,
                    modifier = modifier.fillMaxWidth()
                )

        },
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier
            .statusBarsPadding(),
        navigationIcon = {
            IconButton(onClick = onUpClick) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}
