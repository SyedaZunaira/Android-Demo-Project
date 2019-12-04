package app.storytel.candidate.com.base

import android.app.Application
import app.storytel.candidate.com.di.component.AppComponent
import app.storytel.candidate.com.di.component.DaggerAppComponent

class BaseApplication : Application() {

    companion object {
        private lateinit var appComponent: AppComponent
        fun getAppInjector(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
    }

}
