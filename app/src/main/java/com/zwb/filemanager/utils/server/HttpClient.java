package com.zwb.filemanager.utils.server;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClient {
    private static final String TAG = "HttpClient";

    public static final OkHttpClient client =
            new OkHttpClient.Builder()
                    .connectTimeout(500, TimeUnit.SECONDS)
                    .readTimeout(500, TimeUnit.SECONDS)
                    .build();

    public static String getJobState(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            Log.i(TAG, "getJobState: isSuccessful->");
            return response.body().string();
        } else {
            Log.e(TAG, "getJobState: failure->");
            throw new RuntimeException(response.body().string());
        }
    }

    public static byte[] getFile(String imgUrl) throws IOException {
        File file = new File(imgUrl);
        byte[] byteArray = null;
        try {
            byteArray = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(byteArray);
            fis.close();

            Log.i(TAG, "getFile: " + "字节数组长度: " + byteArray.length);
            return byteArray;
        } catch (IOException e) {
            throw new RuntimeException("字节数组异常->" + e.getMessage());
        }
    }

    public static String sendRequestWithOkHttp(String url, String input_image, String prompt, String pagesize, String pagetype, String style) throws Exception {
        MediaType type = MediaType.parse("application/json;charset=utf-8");
        RequestBody RequestBody2 = RequestBody.create(type, "{\"prompt\":\"" + prompt + "\"," +
                "\"pagesize\":\"" + pagesize + "\"," +
                "\"pagetype\":\"" + pagetype + "\"," +
                "\"style\":\"" + style + "\"," +
                "\"input_image\":\"" + input_image + "\"}");

        Log.i(TAG, "sendRequestWithOkHttp: " + "{\"prompt\":\"" + prompt + "\"," +
                "\"pagesize\":\"" + pagesize + "\"," +
                "\"pagetype\":\"" + pagetype + "\"," +
                "\"style\":\"" + style + "\"," +
                "\"input_image\":\"" + input_image + "\"}");


        Request request = new Request.Builder()
                // 指定访问的服务器地址
                .url(url)
                .post(RequestBody2)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            Log.i(TAG, "getJobState: isSuccessful->");
            return response.body().string();
        } else {
            Log.e(TAG, "getJobState: failure->");
            throw new RuntimeException(response.body().string());
        }

    }
}

