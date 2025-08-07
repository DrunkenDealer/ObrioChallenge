package io.obrio.challange.di


import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.obrio.challange.BuildConfig
import io.obrio.challange.repository.api.BitcoinRateApiInterface
import io.obrio.challange.repository.retrofit.serializers.BigDecimalSerializer
import io.obrio.challange.repository.retrofit.interceptors.HeadersInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder()
        .add(BigDecimalSerializer)
        .build()

    @Singleton
    @Provides
    fun provideHeadersInterceptor() = HeadersInterceptor()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headersInterceptor: HeadersInterceptor
        ) = OkHttpClient.Builder().also { builder ->
            builder.addInterceptor(headersInterceptor)
            builder.addInterceptor(loggingInterceptor)
        }.build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()

    @Singleton
    @Provides
    fun provideBitcoinRateApiInterface(
        retrofit: Retrofit
    ): BitcoinRateApiInterface = retrofit.create(BitcoinRateApiInterface::class.java)
}