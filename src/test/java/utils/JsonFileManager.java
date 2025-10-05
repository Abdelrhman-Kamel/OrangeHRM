package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.util.LinkedHashMap;

public class JsonFileManager {

    LinkedHashMap<String, Object> data;

    public JsonFileManager(String filepath) {

        try {
            data = new Gson().fromJson(new FileReader(filepath), new TypeToken<LinkedHashMap<String, Object>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return data.get(key) == null ? "" : data.get(key).toString();
    }

}
