package compose.articles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import appdata.network.NYTimesNewsResult

@Composable
fun NewsListView(
    news: List<NYTimesNewsResult>?,
    onNewsClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
    onSaveClick: (NYTimesNewsResult, Boolean) -> Unit
) {

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(
                items = news ?: emptyList(),
                key = { it.title }
            ) {
                NewsItemView(
                    newsItem = it,
                    onClick = onNewsClick,
                    onShareClick = onShareClick,
                    onSaveClick = onSaveClick
                )
            }

        }
    }
}
