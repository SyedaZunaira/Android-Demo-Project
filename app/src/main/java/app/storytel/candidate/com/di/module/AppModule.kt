package app.storytel.candidate.com.di.module

import app.storytel.candidate.com.data.Repository
import app.storytel.candidate.com.data.rest.ApiClient
import app.storytel.candidate.com.data.rest.ApiService
import app.storytel.candidate.com.utils.AppConstants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class AppModule {

    @Singleton
    @Provides
    fun providesPostsRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun providePlacesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesPlacesApiClient(apiService: ApiService): ApiClient {
        return ApiClient(apiService)
    }

    @Singleton
    @Provides
    fun providesPlacesRepository(apiClient: ApiClient): Repository {
        return Repository(apiClient)
    }
}
