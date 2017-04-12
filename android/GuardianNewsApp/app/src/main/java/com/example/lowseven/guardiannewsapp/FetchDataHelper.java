package com.example.lowseven.guardiannewsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FetchDataHelper {

    private static final String ERROR_LOG_TAG = "<<ERROR>>";
    private static final String ERROR_CREATING_URL = "Error making the url";
    private static final String ERROR_CONNECTING_TO_API = "Error connecting to the api";
    private static final String ERROR_EXTRACT_DATA_FROM_JSON_RESPONSE = "Error extracting data from the json response";

    //We don't need to instance this class, we only need fetch data
    private FetchDataHelper(){}

    public static ArrayList<Article> fetchContent(String url) {
        ArrayList<Article> articles = null;

        if(!TextUtils.isEmpty(url)) {
            String jsonResponse = getJsonResponse(createUrl(url));
            articles = new ArrayList<>();

            try {
                JSONArray articlesJsonArray = new JSONObject(jsonResponse)
                        .getJSONObject("response")
                        .getJSONArray("results");

                for(int i=0; i< articlesJsonArray.length(); ++i) {
                    JSONObject articleData = (JSONObject) articlesJsonArray.get(i);

                    Article article = new Article(
                            articleData.getString("webTitle"),
                            articleData.getString("webUrl"),
                            articleData.getString("sectionName"),
                            (articleData.isNull("fields"))? "" : articleData.getJSONObject("fields").getString("thumbnail")
                    );

                    articles.add(article);
                }
            } catch (JSONException e) {
                Log.e(ERROR_LOG_TAG, ERROR_EXTRACT_DATA_FROM_JSON_RESPONSE + ": " + e.getMessage());
            }
        }

        return articles;
    }

    private static URL createUrl(String apiRequest) {
        URL url = null;

        try {
            url = new URL(apiRequest);
        } catch (MalformedURLException e) {
            Log.e(ERROR_LOG_TAG, ERROR_CREATING_URL + ": " + e.getMessage());
        }

        return url;
    }

    private static String getJsonResponse(URL url) {
        String jsonResponse = null;
        HttpURLConnection connection;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10*1000);
            connection.setConnectTimeout(15*1000);
            if (connection.getResponseCode() == 200) {
                jsonResponse = readFromStream(connection.getInputStream());

            } else {
                throw new IOException(Integer.toString(connection.getResponseCode()));
            }
        } catch (IOException e) {
            Log.e(ERROR_LOG_TAG,ERROR_CONNECTING_TO_API  + ": " + e.getMessage());
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(reader);
            String line = buffer.readLine();

            while(line != null) {
                builder.append(line);
                line = buffer.readLine();
            }

        }

        return builder.toString();
    }

}
