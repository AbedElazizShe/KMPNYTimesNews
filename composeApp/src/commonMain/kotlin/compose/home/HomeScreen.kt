package compose.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.articles.NewsListScreen
import appdata.network.NYTimesNewsCategory
import kotlinx.coroutines.launch

enum class HomeTabs(
    val labelResId: String
) {
    HOME("Home"),
    WORLD("World"),
    SCIENCE("Science"),
    ARTS("Arts")

}

@Composable
fun HomeScreen(
    tabs: Array<HomeTabs> = HomeTabs.entries.toTypedArray(),
    savedClick: () -> Unit,
    onShareClick: (String) -> Unit,
    onNewsClick: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            HomeTopAppBar(
                savedClick = savedClick
            )
        },
    ) { padding ->
        HomeScreen(
            modifier = Modifier.padding(top = padding.calculateTopPadding()),
            tabs = tabs,
            onNewsClick = onNewsClick,
            onShareClick = onShareClick
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    tabs: Array<HomeTabs>,
    onNewsClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
) {

    Column(modifier) {
        val pagerState = rememberPagerState(pageCount = { tabs.size })
        val currentPage by rememberUpdatedState(pagerState.currentPage)
        val coroutineScope = rememberCoroutineScope()
        val selectedTab = remember {
            mutableIntStateOf(0)
        }
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 32.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            tabs.forEachIndexed { index, homeTab ->
                val label = homeTab.labelResId
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        selectedTab.intValue = index
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(text = label) },
                    unselectedContentColor = MaterialTheme.colors.secondary
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false
        ) { pageIndex ->
            if (pageIndex == currentPage) {
                NewsListScreen(
                    category = NYTimesNewsCategory.entries[pageIndex].category,
                    onNewsClick = onNewsClick,
                    onShareClick = onShareClick
                )

            }
        }
    }
}


@Composable
private fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    savedClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                Text(
                    text = "Headlines",
                    style = MaterialTheme.typography.h5,
                    modifier = modifier
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        actions = {
            IconButton(onClick = savedClick) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
            }
        }
    )

}

