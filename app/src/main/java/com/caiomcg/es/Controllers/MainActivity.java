package com.caiomcg.es.Controllers;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.caiomcg.es.C;
import com.caiomcg.es.Models.User;
import com.caiomcg.es.R;
import com.caiomcg.es.UserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainAct";
    private User user;
    private EditText userName;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.username_edit_text);
        password = findViewById(R.id.password_edit_text);

        findViewById(R.id.connect_button).setOnClickListener(v -> {
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(new JsonObjectRequest(Request.Method.GET, C.userLogin(userName.getText().toString(),
                    password.getText().toString()).toString(),
                    null, response -> {
                try {
                    user = UserFactory.createUser(response);
                    Log.d(TAG, "Logged " + user.firstName + " to the system");
                    // transition to logged user screen
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Não foi possível conectar" +
                            ", por favor contate um administrador", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }, error -> {
                Toast.makeText(MainActivity.this, "Não foi possível contactar o" +
                        "servidor, por favor verifique as credenciais", Toast.LENGTH_LONG).show();
                Log.e(TAG, error.toString());
            }));
        });

        findViewById(R.id.no_account_text_view).setOnClickListener(v -> {
            //TODO: Show user creation
            startActivity(new Intent(MainActivity.this, NoAccountActivity.class));
        });
    }
}
