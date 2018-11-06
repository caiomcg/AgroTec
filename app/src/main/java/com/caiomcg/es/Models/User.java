package com.caiomcg.es.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {
    public int id;
    public String userName;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String phone;
    public String urlImage;

    public JSONObject toJsonObject() throws JSONException {
        JSONObject object = new JSONObject();

        object.put("username", userName);
        object.put("password", password);
        object.put("firstName", firstName);
        object.put("lastName", lastName);
        object.put("email", email);
        object.put("phone", phone);

        return object;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", urlImage='" + urlImage + '\'' +
                '}';
    }
}
