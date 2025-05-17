package lk.malandev.idetnewsapp.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import kotlin.math.log

class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authentication", "Bearer API_KEY")
            .build()

        Log.d("REQUEST","NEWS REQUEST ${request.method()} :::: ${request.url()}")

        val response = chain.proceed(request)

        Log.d("RESPONSE","NEWS RESPONSE ${response.code()} :::: ${response.body()}")

        return response
    }
}