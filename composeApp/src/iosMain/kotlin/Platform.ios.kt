import androidx.room.RoomDatabase
import database.getDatabaseBuilder
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController
import platform.UIKit.UIWindow
import platform.UIKit.popoverPresentationController

actual fun databaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    return getDatabaseBuilder()
}

actual fun share(url: String) {

    val activityController = UIActivityViewController(
        activityItems = listOf(url),
        applicationActivities = null
    )

    val window = UIApplication.sharedApplication.windows().first() as UIWindow?
    activityController.popoverPresentationController()?.sourceView =
        window
    window?.rootViewController?.presentViewController(
        activityController as UIViewController,
        animated = true,
        completion = null,
    )
}
