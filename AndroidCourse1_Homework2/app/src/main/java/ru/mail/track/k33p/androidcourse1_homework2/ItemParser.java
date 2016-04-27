package ru.mail.track.k33p.androidcourse1_homework2;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemParser {
    public static List<Item> parse(String data) throws JSONException {
        if (data == null) {
            return new ArrayList<>(0);
        }

        JSONObject techJson = new JSONObject(data).getJSONObject("technology");
        List<Item> result = new ArrayList<>();
        Iterator<String> idIterator = techJson.keys();
        while (idIterator.hasNext()) {
            JSONObject techObj = techJson.getJSONObject(idIterator.next());
            Item item = new Item();
            item.id = techObj.getInt("id");
            item.picture = techObj.getString("picture");
            item.title = techObj.getString("title");
            item.info = techObj.optString("info");
            result.add(item);
        }

        return result;
    }
}
