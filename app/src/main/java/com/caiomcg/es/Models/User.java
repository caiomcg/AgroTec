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


    JSONObject toJsonObject() throws JSONException {
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
            object.put("ads", arrayOfAds);
        } else {
            object.put("ads", new JSONArray());
        }

        object.put("phone", phone);

        return object;
    }

    @Override
    public String toString() {
        return "Name: " + userName + " id: " + id;
    }
}
