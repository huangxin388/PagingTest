package com.example.utils;

import okhttp3.OkHttpClient;

public class HttpUtils {
    private static OkHttpClient client = null;
    public static OkHttpClient getClient() {
        if(client == null) {
            client = new OkHttpClient();
        }
        return client;
    }
}
