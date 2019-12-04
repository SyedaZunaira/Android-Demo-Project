package app.storytel.candidate.com.di.component

import android.app.Application
import app.storytel.candidate.com.di.module.AppModule
import app.storytel.candidate.com.di.module.ViewModelModule
import app.storytel.candidate.com.ui.ListActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(listActivity: ListActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}