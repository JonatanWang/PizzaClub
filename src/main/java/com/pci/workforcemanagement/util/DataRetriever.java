package com.pci.workforcemanagement.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class DataRetriever {

    public JsonArray getJsonData(String url) {

        try {
            var urlConnection = new URL(url).openConnection();
            urlConnection.connect();
            var parsedJSON = new JsonParser().parse(new InputStreamReader((InputStream) urlConnection.getContent()));
            var fetchedJSON = parsedJSON.getAsJsonObject();
            var scheduleResult = fetchedJSON.getAsJsonObject("ScheduleResult");
            var schedulesJsonArray = scheduleResult.getAsJsonArray("Schedules");

            return schedulesJsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
