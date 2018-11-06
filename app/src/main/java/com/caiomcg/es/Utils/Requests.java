package com.caiomcg.es.Utils;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.caiomcg.es.Controllers.AgroTecApplication;

import org.json.JSONArray;
import org.json.JSONObject;

public final class Requests {
    private static final Requests instance = new Requests();
    private static final RequestQueue requestQueue = Volley.newRequestQueue(AgroTecApplication.getContext());

    private final int TIMEOUT_MS = 5000; // 5 seconds

    private Requests() {}

    public static Requests getInstance() {
        return instance;
    }

    public void asJsonObject(int method, String url, @Nullable JSONObject object,
                             Response.Listener<JSONObject> listener,
                             @Nullable Response.ErrorListener errorListener) {
        this.perform(new JsonObjectRequest(method, url, object, listener, errorListener));
    }

    public void asJsonArray(int method, String url, @Nullable JSONArray object,
                             Response.Listener<JSONArray> listener,
                             @Nullable Response.ErrorListener errorListener) {
        this.perform(new JsonArrayRequest(method, url, object, listener, errorListener));
    }

    public void asImage(String url,
                        Response.Listener<Bitmap> listener,
                        int maxWidth,
                        int maxHeight,
                        ImageView.ScaleType scaleType,
                        Bitmap.Config decodeConfig,
                        @Nullable Response.ErrorListener errorListener) {
        this.perform(new ImageRequest(url, listener, maxWidth, maxHeight, scaleType, decodeConfig, errorListener));
    }

    private void perform(Request request) {
        request.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }
}