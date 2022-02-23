package edu.psu.ist.hcdd340.pennstateid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowProfileDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ACTIVITY_8_DETAILS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile_details);

        findViewById(R.id.button_details_ok).setOnClickListener(this);
        findViewById(R.id.button_details_cancel).setOnClickListener(this);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_SHOW_MESSAGE_KEY);
        boolean inOffice = intent.getBooleanExtra(MainActivity.EXTRA_IN_OFFICE_STATE_KEY, false);

        TextView textView = findViewById(R.id.details_profile_name);
        textView.setText(message);

        CheckBox checkBox = findViewById(R.id.details_in_office_checkbox);
        checkBox.setChecked(inOffice);
        Log.d(TAG, "On Create");
    }

    @Override
    public void onClick(View view) {
        int eventSourceId = view.getId();
        Log.d(TAG, String.format("Clicked on: %s", eventSourceId));

        if (eventSourceId == R.id.button_details_ok) {
            CheckBox checkBox = findViewById(R.id.details_in_office_checkbox);
            boolean inOfficeStatus = checkBox.isChecked();
            Intent returnIntent = new Intent();
            returnIntent.putExtra(MainActivity.EXTRA_RETURN_IN_OFFICE, inOfficeStatus);
            setResult(RESULT_OK, returnIntent);
            finish();
        } else if (eventSourceId == R.id.button_details_cancel) {
            setResult(RESULT_CANCELED);
            finish();
        }
        else
            Log.d(TAG, String.format("Unknown click event source: %s", eventSourceId));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "On Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "On Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "On Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "On Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "On Destroy");
    }
}