package compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import compose.home.HomeScreen
import compose.savedforlater.SavedForLaterScreen
import share

@Composable
fun KmpNYTimesNewsApp() {
    val navController = rememberNavController()
    KmpNYTimesNewsNavHost(navController)
}

@Composable
fun KmpNYTimesNewsNavHost(navController: NavHostController) {
    val uriHandler = LocalUriHandler.current

    NavHost(
        navController = navController,
        startDestination = Screen.Home.name
    ) {
        composable(route = Screen.Home.name) {
            HomeScreen(
                savedClick = {
                    navController.navigate(Screen.SavedForLater.name)
                },
                onShareClick = {
                    share(it)
                }
            ) {
                uriHandler.openUri(it)
            }
        }

        composable(route = Screen.SavedForLater.name) {
            SavedForLaterScreen(
                onUpClick = {
                    navController.navigateUp()
                },
                onShareClick = {
                    share(it)
                }
            ) {
                uriHandler.openUri(it)
            }
        }
    }
}

