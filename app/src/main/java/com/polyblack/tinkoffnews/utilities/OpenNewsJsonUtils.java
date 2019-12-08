package com.polyblack.tinkoffnews.utilities;

import android.content.Context;

import com.polyblack.tinkoffnews.data.TNews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public final class OpenNewsJsonUtils {

    public static List<TNews> getSimpleNewsStringsFromJson(Context context, String newsJsonStr)
            throws JSONException {

        final String N_PAYLOAD = "payload";
        final String N_TITLE = "text";
        final String N_CODE = "result_code";

        List<TNews> parsedNewsData = null;

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

        parsedNewsData = new ArrayList<TNews>();


        for (int i = 0; i < newsArray.length(); i++) {
            TNews tNews = new TNews();
            String description;

            JSONObject eachNews = newsArray.getJSONObject(i);
            description = eachNews.getString(N_TITLE);
            parsedNewsData.add(i, tNews.setter(i,description));
        }

        return parsedNewsData;
    }

}