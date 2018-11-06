package com.caiomcg.es.Models;

import android.hardware.usb.UsbRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Ad implements Serializable {
    public int id;
    public String urlImage;
    public String title;
    public String description;
    public String registerDate;

    public User user;

    public JSONObject toJsonObject() throws JSONException {
        JSONObject object = new JSONObject();

        object.put("people_id", user.id);
        object.put("title", title);
        object.put("description", description);
        object.put("registerDate", registerDate);

        return object;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", urlImage='" + urlImage + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", registerDate='" + registerDate + '\'' +
                '}';
    }
}
