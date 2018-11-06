package com.caiomcg.es.Utils;

import com.caiomcg.es.Models.User;

import org.json.JSONException;
import org.json.JSONObject;

public class UserCreator {
    public static User createUser(JSONObject object) throws JSONException {
        User user = new User();

        user.id = object.getInt("id");
        user.userName = object.getString("username");
        user.password = object.getString("password");
        user.firstName = object.getString("firstName");
        user.lastName = object.getString("lastName");
        user.email = object.getString("email");
        user.phone = object.getString("phone");
        user.urlImage = object.getString("urlImage");

       return user;
    }

}
