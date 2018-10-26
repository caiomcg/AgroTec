package com.caiomcg.es;

import com.caiomcg.es.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserFactory {
    public static User createUser(JSONObject object) throws JSONException {
        User user = new User();
        user.id = object.getInt("id");
        user.userName = object.getString("userName");
        user.firstName = object.getString("firstName");
        user.lastName = object.getString("lastName");
        user.email = object.getString("email");
        user.password = object.getString("password");
        user.phone = object.getString("phone");

        JSONArray ads = object.getJSONArray("anuncios");
        user.ads = new int[ads.length()];

        for (int i = 0; i < ads.length(); i++) {
            user.ads[i] = ads.getInt(i);
        }
       return user;
    }

}
