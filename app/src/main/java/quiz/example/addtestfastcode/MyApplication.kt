package quiz.example.addtestfastcode

import android.app.Application

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import quiz.example.addtestfastcode.di.appModule
import quiz.example.addtestfastcode.di.dataModule
import quiz.example.addtestfastcode.di.domainModule
import timber.log.Timber

class MyApplication : Application() {

        override fun onCreate() {
            super.onCreate()

            if (BuildConfig.DEBUG) {
                Timber.plant(Timber.DebugTree())
            }
            startKoin {
                androidContext(this@MyApplication)
                modules(dataModule)
                modules(appModule)
                modules(domainModule)
            }
        }
    }

