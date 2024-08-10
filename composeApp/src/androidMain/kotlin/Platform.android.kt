import android.content.Intent
import androidx.core.app.ShareCompat
import androidx.room.RoomDatabase
import com.example.kmpnytimesnews.MyApplication

actual fun databaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    return getDatabaseBuilder(MyApplication.instance.applicationContext)
}

actual fun share(url: String) {
    val shareIntent = ShareCompat.IntentBuilder(MyApplication.instance.applicationContext)
        .setText(url)
        .setType("text/plain")
        .createChooserIntent()
        .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    MyApplication.instance.applicationContext.startActivity(shareIntent)
}
