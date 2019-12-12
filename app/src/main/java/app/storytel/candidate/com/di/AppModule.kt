package app.storytel.candidate.com.di

import app.storytel.candidate.com.data.Repository
import app.storytel.candidate.com.data.rest.ApiClient
import app.storytel.candidate.com.data.rest.ApiService
import app.storytel.candidate.com.ui.detail.DetailViewModel
import app.storytel.candidate.com.ui.list.ListViewModel
import app.storytel.candidate.com.utils.AppConstants.BASE_URL
import app.storytel.candidate.com.utils.NetworkUtils
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()

private fun retrofitInstance(): Retrofit =
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

private val apiService: ApiService = retrofitInstance().create(ApiService::class.java)

val appModule = module {
    single { apiService }
    single { ApiClient(apiService = get()) }
    single { Repository(apiClient = get()) }
    single { NetworkUtils(androidContext()) }
    viewModel { ListViewModel(networkUtils = get(), repository = get()) }
    viewModel { DetailViewModel(networkUtils = get(), repository = get()) }
}
