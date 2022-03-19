package edu.psu.ist.hcdd340.pennstateid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ACTIVITY_LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        findViewById(R.id.button_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int eventSourceId = view.getId();
        if (eventSourceId == R.id.button_login) {
            handleLogIn();
        }
    }

    private void handleLogIn() {
        // get user name
        EditText editText = findViewById(R.id.editTextUserName);
        String userName = editText.getText().toString();

        editText = findViewById(R.id.editTextPassword);
        String password = editText.getText().toString();

        Log.d(TAG, "User name: \"" + userName + "\" and Password: \"" + password + "\"");
    }
}