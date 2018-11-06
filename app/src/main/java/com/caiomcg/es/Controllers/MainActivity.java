package com.caiomcg.es.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.caiomcg.es.C;
import com.caiomcg.es.Models.User;
import com.caiomcg.es.R;
import com.caiomcg.es.Utils.UserCreator;
import com.caiomcg.es.Utils.Requests;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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

        findViewById(R.id.connect_button).setOnClickListener(this);
//        findViewById(R.id.connect_button).setOnLongClickListener(v -> {
//            startActivity(Share.sendText("Testando 123"));
//            return false;
//        });
        findViewById(R.id.no_account_text_view).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connect_button:
                Requests.getInstance().asJsonObject(Request.Method.GET, C.userLogin(userName.getText().toString(),
                        password.getText().toString()).toString(),null,
                response -> {
                    try {
                        user = UserCreator.createUser(response);
                        Log.d(TAG, "Logged " + user.firstName + " to the system");

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("User", user);

                        Log.e(TAG, user.toString());

                        Intent intent = new Intent(MainActivity.this, RegionsNavigation.class);
                        intent.putExtras(bundle);

                        startActivity(intent);
                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, "Não foi possível conectar" +
                                ", por favor contate um administrador", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                },
                error -> {
                    if (error.networkResponse != null) {
                        Log.e(TAG, "Status code: " + error.networkResponse.statusCode);
                        Toast.makeText(MainActivity.this, "Não foi possível contactar o " +
                                "servidor, por favor verifique as credenciais", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Por favor, verifique os dados inseridos", Toast.LENGTH_LONG).show();
                    }

                    Log.e(TAG, error.toString());
                });
                break;

            case R.id.no_account_text_view:
                startActivity(new Intent(MainActivity.this, NoAccountActivity.class));
                break;
        }
    }
}
