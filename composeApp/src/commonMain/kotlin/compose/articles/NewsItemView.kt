package compose.articles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import appdata.local.entity.NewsEntity
import appdata.network.NYTimesNewsResult
import ui.theme.onSecondaryContainerColor
import ui.theme.secondaryContainerColor

@Composable
fun NewsItemView(
    newsItem: NYTimesNewsResult,
    onClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
    onSaveClick: (NYTimesNewsResult, Boolean) -> Unit
) {
    ItemView(
        url = newsItem.url,
        imageUrl = newsItem.imageUrl,
        category = newsItem.category,
        title = newsItem.title,
        abstract = newsItem.abstract,
        date = newsItem.date,
        savedForLater = newsItem.savedForLater,
        onClick = onClick,
        onShareClick = onShareClick,
        onSaveClick = {
            onSaveClick(newsItem, it)
        }
    )
}

@Composable
fun NewsItemView(
    newsItem: NewsEntity,
    onClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
    onUnSaveClick: (NewsEntity) -> Unit
) {
    ItemView(
        url = newsItem.url,
        imageUrl = newsItem.imageUrl,
        category = newsItem.section,
        title = newsItem.title,
        abstract = newsItem.abstract,
        date = newsItem.displayDate,
        savedForLater = true,
        onClick = onClick,
        onShareClick = onShareClick,
        onSaveClick = {
            onUnSaveClick(newsItem)
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ItemView(
    url: String,
    imageUrl: String,
    category: String,
    title: String,
    abstract: String,
    date: String,
    savedForLater: Boolean,
    onClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
    onSaveClick: (Boolean) -> Unit
) {
    Card(
        onClick =  { onClick(url) },
        backgroundColor = secondaryContainerColor,
        contentColor = onSecondaryContainerColor,
        modifier = Modifier
            .padding(bottom = 8.dp, top = 8.dp)
    ) {

        Column(Modifier.fillMaxWidth()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(185.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = category,
                style = MaterialTheme.typography.overline,
                maxLines = 2,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 4.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
            Text(
                text = abstract,
                style = MaterialTheme.typography.caption,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = date,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
                Row {
                    IconButton(onClick = {
                        if (savedForLater) onSaveClick(false) else onSaveClick(
                            true
                        )
                    }) {
                        Icon(
                            if (savedForLater) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {
                        onShareClick(url)
                    }) {
                        Icon(Icons.Filled.Share, contentDescription = null)
                    }
                }
            }
        }

    }
}
