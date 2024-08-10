import androidx.room.RoomDatabase

expect fun databaseBuilder(): RoomDatabase.Builder<AppDatabase>

expect fun share(url: String)
