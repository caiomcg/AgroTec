package com.caiomcg.es.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.caiomcg.es.C;
import com.caiomcg.es.R;
import com.caiomcg.es.UserFactory;
import com.caiomcg.es.Utils.Requests;

import org.json.JSONException;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoAccountActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int FETCH_IMAGE = 1;
    public static final String TAG = "NoAccountActivity";

    private EditText firstName;
    private EditText lastName;
    private EditText userName;
    private EditText email;
    private EditText password;
    private EditText repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_account);

        firstName  = findViewById(R.id.no_acc_firstname_edit_text);
        lastName   = findViewById(R.id.no_acc_lastname_edit_text);
        userName   = findViewById(R.id.no_acc_username_edit_text);
        email      = findViewById(R.id.no_acc_email_edit_text);
        password   = findViewById(R.id.no_acc_password_edit_text);
        repassword = findViewById(R.id.no_acc_repassword_edit_text);


        findViewById(R.id.profile_image).setOnClickListener(this);
        findViewById(R.id.create_user_button).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FETCH_IMAGE && data != null) {
            ((CircleImageView)findViewById(R.id.profile_image)).setImageURI(data.getData());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_image:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Selecione a imagem de perfil"), FETCH_IMAGE);
                break;

            case R.id.create_user_button:
                if (firstName.getText().toString().isEmpty() ||
                        lastName.getText().toString().isEmpty() ||
                        userName.getText().toString().isEmpty() ||
                        email.getText().toString().isEmpty() ||
                        password.getText().toString().isEmpty() ||
                        repassword.getText().toString().isEmpty()) {
                    new AlertDialog.Builder(NoAccountActivity.this)
                            .setTitle("Cadastro inválido")
                            .setMessage("Por favor, preencha todos os campos")
                            .setPositiveButton("Ok", null)
                            .create()
                            .show();
                } else {
                    //Todo: send to server
                    Log.d(TAG, "Sending to server");
                }
                break;
        }
    }
}
