package com.caiomcg.es;

import android.net.Uri;

public class C {
    public static final String BASE_URL = "https://virtserver.swaggerhub.com/caiomcg4/AgroTec/1.0.0";

    public static Uri userURI() {
        return Uri.parse(BASE_URL + "/user/");
    }

    public static Uri userLogin(String username, String password) {
        return Uri.parse(userURI().toString() + "login?username=" + username + "&password=" + password);
    }

    public static Uri userLogout() {
        return Uri.parse(userURI().toString() + "logout");
    }

    public static Uri userNamed(String name) {
        return Uri.parse(userURI().toString() + name);
    }
}
