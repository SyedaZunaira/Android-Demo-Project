package app.storytel.candidate.com.di.module

import app.storytel.candidate.com.data.Repository
import app.storytel.candidate.com.data.rest.ApiClient
import app.storytel.candidate.com.data.rest.ApiService
import app.storytel.candidate.com.utils.AppConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal class AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()
        return Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesApiClient(apiService: ApiService): ApiClient {
        return ApiClient(apiService)
    }

    @Singleton
    @Provides
    fun providesRepository(apiClient: ApiClient): Repository {
        return Repository(apiClient)
    }
}
