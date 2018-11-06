package com.caiomcg.es;

import android.net.Uri;

import java.net.URI;

public class C {
    //public static final String BASE_URL = "https://virtserver.swaggerhub.com/caiomcg4/AgroTec/1.0.0";
    public static final String BASE_URL = "http://150.165.138.181:8080";

    public static Uri userURI() {
        return Uri.parse(BASE_URL + "/api/people/");
    }

    public static Uri userLogin(String username, String password) {
        return Uri.parse(userURI().toString() + username + "/" + password);
    }

    public static Uri adsURI() { return Uri.parse(BASE_URL + "/api/ads"); }

    public static Uri adsByRegion(int region) {
        return Uri.parse(adsURI().toString() + "/regiao/" + region);
    }

    public static Uri userWithID(int id) { return Uri.parse(userURI().toString() + "id/" + id); }

    public static Uri userLogout() {
        return Uri.parse(userURI().toString() + "logout");
    }

    public static Uri userNamed(String name) {
        return Uri.parse(userURI().toString() + name);
    }
}
