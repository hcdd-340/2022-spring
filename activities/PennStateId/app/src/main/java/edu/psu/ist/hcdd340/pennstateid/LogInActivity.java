package edu.psu.ist.hcdd340.pennstateid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ACTIVITY_LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        findViewById(R.id.button_login).setOnClickListener(this);
        findViewById(R.id.button_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int eventSourceId = view.getId();
        if (eventSourceId == R.id.button_login) {
            handleLogIn();
        } else if (eventSourceId == R.id.button_register) {
            handleRegister();
        }
    }

    private void handleLogIn() {
        // get user name
        EditText editText = findViewById(R.id.editTextUserName);
        String userName = editText.getText().toString();

        editText = findViewById(R.id.editTextPassword);
        String password = editText.getText().toString();

        View view = findViewById(R.id.button_login);
        Snackbar.make(view, "Wrong password", Snackbar.LENGTH_LONG)
                .setTextColor(getColor(R.color.design_default_color_error))
                .show();

        Log.d(TAG, "User name: \"" + userName + "\" and Password: \"" + password + "\"");
    }

    private void handleRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}