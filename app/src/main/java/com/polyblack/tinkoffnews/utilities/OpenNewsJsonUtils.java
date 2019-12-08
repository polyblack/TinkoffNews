package com.polyblack.tinkoffnews.utilities;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public final class OpenNewsJsonUtils {

    public static List<String> getSimpleNewsStringsFromJson(Context context, String newsJsonStr)
            throws JSONException {

        final String N_PAYLOAD = "payload";
        final String N_TITLE = "text";
        final String N_CODE = "result_code";

        List<String> parsedNewsData = null;

        JSONObject newsJson = new JSONObject(newsJsonStr);

        if (newsJson.has(N_CODE)) {
            int errorCode = newsJson.getInt(N_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    return null;
                default:
                    return null;
            }
        }

        JSONArray newsArray = newsJson.getJSONArray(N_PAYLOAD);

        parsedNewsData = new ArrayList<String>();


        for (int i = 0; i < newsArray.length(); i++) {

            String description;

            JSONObject eachNews = newsArray.getJSONObject(i);
            description = eachNews.getString(N_TITLE);

            parsedNewsData.add(i,description);
        }

        return parsedNewsData;
    }

}