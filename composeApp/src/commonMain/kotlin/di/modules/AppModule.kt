package di.modules

import AppDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import api.NYTimesNewsService
import appdata.repository.NYTimesNewsRepository
import databaseBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module
import viewmodels.NYTimesNewsListViewModel
import viewmodels.SavedForLaterViewModel

val appModule: Module = module {

    single<AppDatabase> {
        databaseBuilder()
            .fallbackToDestructiveMigrationOnDowngrade(true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
    single { get<AppDatabase>().newsDao() }
    single { NYTimesNewsService() }
    single { NYTimesNewsRepository(get(), get()) }
    factory { NYTimesNewsListViewModel() }
    factory { SavedForLaterViewModel() }
}
