package com.caiomcg.es.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User {
    public int id;
    public String userName;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public int[] ads;
    public String phone;


    public JSONObject toJsonObject() throws JSONException {
        JSONObject object = new JSONObject();

        object.put("id", id);
        object.put("userName", userName);
        object.put("firstName", firstName);
        object.put("lastName", lastName);
        object.put("email", email);
        object.put("password", password);

        if (ads != null) {
            JSONArray arrayOfAds = new JSONArray();
            for (int i = 0; i < ads.length; i++) {
                arrayOfAds.put(ads[i]);
            }
            object.put("anuncios", arrayOfAds);
        } else {
            JSONArray arrayOfAds = new JSONArray();
            arrayOfAds.put(0);
            object.put("anuncios", arrayOfAds);
        }

        object.put("phone", phone);

        return object;
    }

    @Override
    public String toString() {
        return "Name: " + userName + " id: " + id;
    }
}
