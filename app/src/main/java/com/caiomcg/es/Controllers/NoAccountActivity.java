package com.caiomcg.es.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.caiomcg.es.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoAccountActivity extends AppCompatActivity {
    public static final int FETCH_IMAGE = 1;

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


        findViewById(R.id.profile_image).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Selecione a imagem de perfil"), FETCH_IMAGE);
        });

        findViewById(R.id.create_user_button).setOnClickListener(v -> {
            if (!password.getText().toString().equals(repassword.getText().toString())) {
                new AlertDialog.Builder(NoAccountActivity.this)
                        .setTitle("Cadastro inválido")
                        .setMessage("As senhas informadas não coincidem")
                        .setPositiveButton("Ok", null)
                        .create()
                        .show();
            } else {
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
                    // send to server

                }
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FETCH_IMAGE && data != null) {
            ((CircleImageView)findViewById(R.id.profile_image)).setImageURI(data.getData());
        }
    }
}
