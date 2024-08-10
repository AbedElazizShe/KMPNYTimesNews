import androidx.room.Database
import androidx.room.RoomDatabase
import appdata.local.dao.NewsDao
import appdata.local.entity.NewsEntity

interface DB {
    fun clearAllTables() {}
}

@Database(entities = [NewsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(), DB {
    abstract fun newsDao(): NewsDao
    
    override fun clearAllTables() {
        super.clearAllTables()
    }
}
