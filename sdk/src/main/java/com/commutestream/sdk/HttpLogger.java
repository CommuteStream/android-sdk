package com.commutestream.sdk;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp Interceptor for logging HTTP Request/Responses
 */
public class HttpLogger implements Interceptor {

    @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.v("CS_SDK", String.format("Sending request %s %s",
                request.method(), request.url()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.v("CS_SDK", String.format("Received %d response from %s %s in %.1fms%n%s",
                response.code(), response.request().method(), response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
