import androidx.compose.runtime.Composable
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import compose.KmpNYTimesNewsApp
import compose.getAsyncImageLoader
import di.modules.appModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import ui.theme.KmpNYTimesNewsTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App() {

    KoinApplication(
        application = {
            modules(appModule)
        }
    ) {
        KmpNYTimesNewsTheme {
            setSingletonImageLoaderFactory { context ->
                getAsyncImageLoader(context)
            }
            KmpNYTimesNewsApp()
        }
    }


}