package com.chetu.createprojectstucture.di.module

import com.chetu.createprojectstucture.base.BaseApplication
import com.chetu.createprojectstucture.data.rest.RepoService
import com.chetu.createprojectstucture.util.CommanWord
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
object ApplicationModule {

    private const val BASE_URL = "https://api.github.com/"

    @JvmStatic
    @Provides
    @Singleton
    fun provideHttpCache(application: BaseApplication): Cache {
        val cacheSize: Int = CommanWord.TenInt * CommanWord.MB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideOkhttpClient(cache: Cache): OkHttpClient {
        val client: OkHttpClient.Builder = OkHttpClient.Builder()
        client.readTimeout(CommanWord.Minute.toLong(), TimeUnit.SECONDS)
        client.connectTimeout(CommanWord.Minute.toLong(), TimeUnit.SECONDS)
        client.writeTimeout(CommanWord.TwentyInt.toLong(), TimeUnit.SECONDS)
        client.cache(cache)
        return client.build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): RepoService {
        return retrofit.create(RepoService::class.java)
    }

}
