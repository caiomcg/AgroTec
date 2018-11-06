package com.caiomcg.es.Utils;

import com.caiomcg.es.Models.Ad;
import com.caiomcg.es.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdCreator {
    public static ArrayList<Ad> fetchAds(JSONArray array) {
        ArrayList<Ad> ads = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject jsonObject = array.getJSONObject(i);

                Ad ad = new Ad();

                ad.id = jsonObject.getInt("id");
                ad.user = UserFactory.createUser(jsonObject.getJSONObject("people"));
                ad.title = jsonObject.getString("title");
                ad.description = jsonObject.getString("description");
                ad.registerDate = jsonObject.getString("registerDate");
                ad.urlImage = jsonObject.getString("urlImage");

                ads.add(ad);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return ads;
    }
}
