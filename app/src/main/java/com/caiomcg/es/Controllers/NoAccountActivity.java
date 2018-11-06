package com.caiomcg.es.Controllers;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.caiomcg.es.C;
import com.caiomcg.es.Models.User;
import com.caiomcg.es.R;
import com.caiomcg.es.Utils.Requests;
import com.caiomcg.es.Utils.UserFactory;
import com.caiomcg.es.Utils.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.Buffer;

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
                Log.e(TAG, "Validating: " + email.getText() + " - " + Validator.isValidEmailId(email.getText().toString()));

                if (!validateFields()) {
                    new AlertDialog.Builder(NoAccountActivity.this)
                            .setTitle("Cadastro inválido")
                            .setMessage("Por favor, verifique se os campos são válidos")
                            .setPositiveButton("Ok", null)
                            .create()
                            .show();
                } else {
                    //Todo: send to server
                    User user = new User();
                    user.firstName = firstName.getText().toString();
                    user.lastName = lastName.getText().toString();
                    user.userName = userName.getText().toString();
                    user.email = email.getText().toString();
                    user.password = password.getText().toString();
                    user.phone = "8888-8888";
                    user.urlImage = "";
                    JSONObject jsonObject = null;

                    try {
                        jsonObject = user.toJsonObject();
                        Log.e(TAG, "Object: " + jsonObject.toString(4));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Requests.getInstance().asJsonObject(Request.Method.POST, C.userURI().toString(),
                            jsonObject, response -> {
                                Log.e(TAG, "All good");
                                try {
                                    User loggedUser = UserFactory.createUser(response);

                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("User", loggedUser);

                                    Intent toRegions = new Intent(NoAccountActivity.this, RegionsNavigation.class);
                                    toRegions.putExtras(bundle);

                                    startActivity(toRegions);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }, error -> {
                                Log.e(TAG, "An error occured: " + error.toString());
                                error.printStackTrace();
                            });
                    Log.d(TAG, "Sending to server");
                }
                break;
        }
    }

    private boolean validateFields() {
        return (validateEmptyField(firstName) &&
                validateEmptyField(lastName) &&
                validateEmptyField(userName) &&
                validateEmptyField(email) &&
                validateEmptyField(password) &&
                validateEmptyField(repassword)) &&
                validateEmailField(email) &&
                passwordsMatch(password, repassword);

    }

    private boolean validateEmptyField(EditText editText) {
        if (!editText.getText().toString().isEmpty()) {
            editText.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
            return true;
        }
        editText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        return false;
    }

    private boolean validateEmailField(EditText editText) {
        if (Validator.isValidEmailId(editText.getText().toString())) {
            editText.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
            return true;
        }
        editText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        return false;
    }

    private boolean passwordsMatch(EditText first, EditText second) {
        if (first.getText().toString().equals(second.getText().toString())) {
            first.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
            second.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
            return true;
        }
        first.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        second.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        return false;
    }
}
